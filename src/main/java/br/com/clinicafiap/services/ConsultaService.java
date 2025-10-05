package br.com.clinicafiap.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import br.com.clinicafiap.entities.dto.ErroUsuarioDto;
import br.com.clinicafiap.grpc.exceptions.ValidacaoUsuariosException;
import br.com.clinicafiap.services.interfaces.INotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.clinicafiap.entities.db.ConsultaDb;
import br.com.clinicafiap.entities.domain.Consulta;
import br.com.clinicafiap.entities.dto.DadosConsultaDtoRequest;
import br.com.clinicafiap.entities.dto.DadosConsultaDtoResponse;
import br.com.clinicafiap.entities.dto.UsuarioDto;
import br.com.clinicafiap.exceptions.ConsultaExistenteException;
import br.com.clinicafiap.exceptions.DataConsultaInvalidaException;
import br.com.clinicafiap.mappers.ConsultaMapper;
import br.com.clinicafiap.repositories.interfaces.IConsultaRepository;
import br.com.clinicafiap.services.interfaces.IConsultaService;
import br.com.clinicafiap.services.interfaces.IUsuarioService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsultaService implements IConsultaService {

	private IConsultaRepository consultaRepository;
	private IUsuarioService usuarioService;

	@Autowired
	private INotificacaoService notificacaoService;

	public ConsultaService(IConsultaRepository consultaRepository, IUsuarioService usuarioService) {
		this.consultaRepository = consultaRepository;
		this.usuarioService = usuarioService;
	}
	
	@Override
	public void agendar(DadosConsultaDtoRequest dados) {
		validaUsuariosParaAgendamento(dados);

		salvar(toConsulta(dados));

		enviarNotificacao(toConsulta(dados));
	}

	@Override
	public List<DadosConsultaDtoResponse> buscarConsultasPorMedico(UUID idMedico) {
		return toListDadosConsultaDtoResponse(toListConsulta(consultaRepository.buscarConsultasPorMedico(idMedico)));
	}

	@Override
	public List<DadosConsultaDtoResponse> buscarConsultasPorPaciente(UUID idPaciente) {
		return toListDadosConsultaDtoResponse(toListConsulta(consultaRepository.buscarConsultasPorPaciente(idPaciente)));
	}

	@Override
	public void atualizarDataConsulta(UUID idConsulta, LocalDateTime data) {
		Consulta consulta = buscarConsultaPorId(idConsulta);

		trataValidacoes(consulta, data);

		consulta.atualizarData(data);

		salvar(consulta);

		enviarNotificacao(consulta);
	}

	@Override
	public void cancelarConsulta(UUID idConsulta, UUID idUsuarioCancelamento) {
		Consulta consulta = buscarConsultaPorId(idConsulta);

		UsuarioDto usuario = buscarUsuarioPorId(idUsuarioCancelamento);

		consulta.cancelarConsulta(usuario.id());

		salvar(consulta);

		enviarNotificacao(consulta);
	}

	@Override
	public DadosConsultaDtoResponse buscarPorId(UUID idConsulta) {
		return toDadosConsultaDtoResponse(buscarConsultaPorId(idConsulta));
	}

	@Scheduled(fixedRate = 60000)
	public void enviarNotificacoesConsultasFuturas() {
		log.info("Início do método para recuperar as consultas futuras. Data: {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

		enviarNotificacoes(buscarConsultasFuturas());

		log.info("Fim do processo para recuperar as consultas futuras. Data: {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
	}

	private List<Consulta> buscarConsultasFuturas() {
		return toListConsulta(consultaRepository.buscarConsultasFuturas());
	}

	private void enviarNotificacoes(List<Consulta> consultas) {
		consultas.stream().forEach(c -> enviarNotificacao(c));
	}

	private void enviarNotificacao(Consulta consulta) {
		log.info("Dados da consulta: {}", consulta);

		UsuarioDto usuario = buscarUsuarioPorId(consulta.getIdPaciente());

		notificacaoService.sendMessageNotificacao(consulta, usuario);
	}

	private UsuarioDto buscarUsuarioPorId(UUID idUsuario) {
		return usuarioService.buscarPorId(idUsuario);
	}

	private void trataValidacoes(Consulta consulta, LocalDateTime data) {
		validaDataConsulta(consulta.getDataConsulta(), data);
		validaConsultaExistenteMedico(consulta.getIdMedico(), data);
		validaConsultaExistentePaciente(consulta.getIdPaciente(), data);
	}

	private void validaConsultaExistentePaciente(UUID idPaciente, LocalDateTime data) {
		if(consultaRepository.verificaConsultaPorUsuario(idPaciente, data, false)) {
			throw new ConsultaExistenteException("A consulta não pode ser alterada pois o médico já possui uma consulta no mesmo horário.");
		}
	}

	private void validaConsultaExistenteMedico(UUID idMedico, LocalDateTime data) {
		if(consultaRepository.verificaConsultaPorUsuario(idMedico, data, true)) {
			throw new ConsultaExistenteException("A consulta não pode ser alterada pois o médico já possui uma consulta no mesmo horário.");
		}
	}

	private void validaDataConsulta(LocalDateTime dataConsulta, LocalDateTime novaData) {
		if(dataConsulta.isEqual(novaData)) {
			throw new DataConsultaInvalidaException("A nova data da consulta não pode ser igual a data atual consulta.");
		}
	}

	private void validaUsuariosParaAgendamento(DadosConsultaDtoRequest dados) {
		var validacao = usuarioService.validaUsuariosParaAgendamento(
				dados.idMedico(), dados.idPaciente(), dados.idUsuarioCriacao()
		);

		if (!validacao.getErrosList().isEmpty()) {
			List<ErroUsuarioDto> erros = validacao.getErrosList().stream()
					.map(e -> new ErroUsuarioDto(e.getCampo(), e.getCodigo(), e.getMensagem()))
					.toList();

			throw new ValidacaoUsuariosException(erros);
		}
	}

	private List<DadosConsultaDtoResponse> toListDadosConsultaDtoResponse(List<Consulta> consultas) {
		return consultas.stream().map(c -> ConsultaMapper.toDadosConsultaDtoResponse(c, usuarioService.buscarPorId(c.getIdMedico()), 
																						usuarioService.buscarPorId(c.getIdPaciente()), 
																						usuarioService.buscarPorId(c.getIdUsuarioCriacao()))).toList();
	}

	private DadosConsultaDtoResponse toDadosConsultaDtoResponse(Consulta consulta) {
		return ConsultaMapper.toDadosConsultaDtoResponse(consulta,
														 usuarioService.buscarPorId(consulta.getIdMedico()),
														 usuarioService.buscarPorId(consulta.getIdPaciente()),
														 usuarioService.buscarPorId(consulta.getIdUsuarioCriacao()));
	}

	private void salvar(Consulta consulta) {
		consultaRepository.salvar(ConsultaMapper.toConsultaDb(consulta));
	}

	private Consulta toConsulta(DadosConsultaDtoRequest dados) {
		return ConsultaMapper.toConsulta(dados);
	}

	private List<Consulta> toListConsulta(List<ConsultaDb> consultas) {
		return ConsultaMapper.toListConsultas(consultas);
	}

	private Consulta buscarConsultaPorId(UUID idConsulta) {
		return ConsultaMapper.toConsulta(consultaRepository.buscarPorId(idConsulta));
	}
}