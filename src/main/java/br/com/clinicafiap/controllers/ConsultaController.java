package br.com.clinicafiap.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clinicafiap.entities.dto.DadosConsultaDtoRequest;
import br.com.clinicafiap.entities.dto.DadosConsultaDtoResponse;
import br.com.clinicafiap.entities.dto.DataConsultaDto;
import br.com.clinicafiap.entities.dto.UsuarioCancelamentoDto;
import br.com.clinicafiap.services.interfaces.IConsultaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/consultas")
@Slf4j
public class ConsultaController {

	private IConsultaService consultaService;

	public ConsultaController(IConsultaService consultaService) {
		this.consultaService = consultaService;
	}
	
	@PostMapping
	public ResponseEntity<Void> agendarConsulta(@Valid @RequestBody DadosConsultaDtoRequest dados) {
		log.info("agendarConsulta - Dados da consulta: {}", dados);
		
		consultaService.agendar(dados);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/{id-consulta}")
	public ResponseEntity<DadosConsultaDtoResponse> buscarConsultaPorId(@PathVariable(name = "id-consulta") @NotNull @Valid UUID idConsulta) {
		log.info("buscarConsultaPorId - Id: {}", idConsulta);
		
		return ResponseEntity.status(HttpStatus.OK).body(consultaService.buscarPorId(idConsulta));
	}
	
	@GetMapping("/periodo")
	public ResponseEntity<List<DadosConsultaDtoResponse>> buscarConsultasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
		return null;
	}
	
	@GetMapping("/medicos/{id-medico}")
	public ResponseEntity<List<DadosConsultaDtoResponse>> buscarConsultasPorMedico(@PathVariable(name = "id-medico") @Valid @NotNull UUID idMedico) {
		return ResponseEntity.status(HttpStatus.OK).body(consultaService.buscarConsultasPorMedico(idMedico));
	}
	
	@GetMapping("pacientes/{id-paciente}")
	public ResponseEntity<List<DadosConsultaDtoResponse>> buscarConsultasPorPaciente(@PathVariable(name = "id-paciente") @Valid @NotNull UUID idPaciente) {
		return ResponseEntity.status(HttpStatus.OK).body(consultaService.buscarConsultasPorPaciente(idPaciente));
	}
	
	@PatchMapping("/{id-consulta}")
	public ResponseEntity<Void> atualizarDataConsulta(@PathVariable(name = "id-consulta") @NotNull @Valid UUID idConsulta, @RequestBody DataConsultaDto dados) {
		log.info("atualizarDataConsulta - Id da consulta: {}, Nova data da consulta: {}", idConsulta, dados.dataConsulta());

		consultaService.atualizarDataConsulta(idConsulta, dados.dataConsulta());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PatchMapping("/{id-consulta}/cancelamento")
	public ResponseEntity<Void> cancelarConsulta(@PathVariable(name = "id-consulta") @NotNull @Valid UUID idConsulta, @RequestBody UsuarioCancelamentoDto dados) {
		log.info("cancelarConsulta - Id da consulta: {}, Id do usuário de cancelamento: {}", idConsulta, dados.idUsuarioCancelamento());

		consultaService.cancelarConsulta(idConsulta, dados.idUsuarioCancelamento());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}