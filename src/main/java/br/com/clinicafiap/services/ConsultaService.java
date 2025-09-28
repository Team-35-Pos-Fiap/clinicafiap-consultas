package br.com.clinicafiap.services;

import java.util.List;
import java.util.UUID;

import br.com.clinicafiap.grpc.UsuarioGrpcClient;
import org.springframework.stereotype.Service;

import br.com.clinicafiap.entities.domain.Consulta;
import br.com.clinicafiap.entities.dto.DadosConsultaDtoRequest;
import br.com.clinicafiap.entities.dto.DadosConsultaDtoResponse;
import br.com.clinicafiap.mappers.ConsultaMapper;
import br.com.clinicafiap.repositories.interfaces.IConsultaRepository;
import br.com.clinicafiap.services.interfaces.IConsultaService;
import br.com.clinicafiap.services.interfaces.IUsuarioService;

@Service
public class ConsultaService implements IConsultaService {

	private IConsultaRepository consultaRepository;
	private IUsuarioService usuarioService;
	private UsuarioGrpcClient usuarioGrpcClient;


	public ConsultaService(IConsultaRepository consultaRepository, IUsuarioService usuarioService, UsuarioGrpcClient usuarioGrpcClient) {
		this.consultaRepository = consultaRepository;
		this.usuarioService = usuarioService;
		this.usuarioGrpcClient = usuarioGrpcClient;
	}
	
	@Override
	public void agendar(DadosConsultaDtoRequest dados) {
		var validacao = usuarioGrpcClient.validaUsuariosParaAgendamento(
				dados.idMedico(), dados.idPaciente(), dados.idUsuarioCriacao()
		);

		if (!validacao.getErrosList().isEmpty()) {
			throw new IllegalArgumentException(validacao.getErrosList().toString());
		}

		Consulta consulta = ConsultaMapper.toConsulta(dados);
		
		consultaRepository.salvar(ConsultaMapper.toConsultaDb(consulta));
	}

	@Override
	public List<DadosConsultaDtoResponse> buscarConsultasPorMedico(UUID idMedico) {
		List<Consulta> consultas = ConsultaMapper.toListConsultas(consultaRepository.buscarConsultasPorMedico(idMedico));
		
		return toListDadosConsultaDtoResponse(consultas);
	}

	@Override
	public List<DadosConsultaDtoResponse> buscarConsultasPorPaciente(UUID idPaciente) {
		 List<Consulta> consultas = ConsultaMapper.toListConsultas(consultaRepository.buscarConsultasPorPaciente(idPaciente));
		
		return toListDadosConsultaDtoResponse(consultas);
	}
	
	private List<DadosConsultaDtoResponse> toListDadosConsultaDtoResponse(List<Consulta> consultas) {
		return consultas.stream().map(c -> ConsultaMapper.toDadosConsultaDtoResponse(c, usuarioService.buscarPorId(c.getIdMedico()), 
																						usuarioService.buscarPorId(c.getIdPaciente()), 
																						usuarioService.buscarPorId(c.getIdUsuarioCriacao()))).toList();
	}
}