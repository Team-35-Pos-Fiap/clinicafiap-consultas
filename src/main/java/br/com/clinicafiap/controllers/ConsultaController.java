package br.com.clinicafiap.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clinicafiap.entities.dto.DadosConsultaDtoRequest;
import br.com.clinicafiap.entities.dto.DadosConsultaDtoResponse;
import br.com.clinicafiap.services.interfaces.IConsultaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	private IConsultaService consultaService;

	private IConsultaService emailService;

	public ConsultaController(IConsultaService consultaService) {
		this.consultaService = consultaService;
	}
	
	@PostMapping
	public ResponseEntity<Void> agendarConsulta(@Valid @RequestBody DadosConsultaDtoRequest dados) {
		consultaService.agendar(dados);

		emailService.sendMessageNotificacao(email);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/periodo")
	public ResponseEntity<List<DadosConsultaDtoResponse>> buscarConsultasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
		return null;
	}
	
	@GetMapping("/medicos/{id}")
	public ResponseEntity<List<DadosConsultaDtoResponse>> buscarConsultasPorMedico(@PathVariable @Valid @NotNull  UUID idMedico) {
		return ResponseEntity.status(HttpStatus.OK).body(consultaService.buscarConsultasPorMedico(idMedico));
	}
	
	@GetMapping("pacientes/{id}")
	public ResponseEntity<List<DadosConsultaDtoResponse>> buscarConsultasPorPaciente(@PathVariable @Valid @NotNull UUID idPaciente) {
		return ResponseEntity.status(HttpStatus.OK).body(consultaService.buscarConsultasPorPaciente(idPaciente));
	}
}