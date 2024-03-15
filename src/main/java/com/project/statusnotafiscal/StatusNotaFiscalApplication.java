package com.project.statusnotafiscal;

import com.project.statusnotafiscal.models.NotaFiscal;
import com.project.statusnotafiscal.repositories.NotasFiscaisRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
@EnableScheduling
public class StatusNotaFiscalApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatusNotaFiscalApplication.class, args);
	}


}
