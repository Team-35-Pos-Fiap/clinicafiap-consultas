package br.com.clinicafiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClinicaFiapConsultasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaFiapConsultasApplication.class, args);
	}
}