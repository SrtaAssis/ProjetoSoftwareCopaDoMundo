package com.companyJAF.sistemaclassificacao;

import com.companyJAF.sistemaclassificacao.repository.TimeRepository;
import com.companyJAF.sistemaclassificacao.service.teste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SistemaClassificacaoApplication {
	@Autowired
	private teste testeaq;

	public static void main(String[] args) {

		SpringApplication.run(SistemaClassificacaoApplication.class, args);
	}
	@GetMapping("/testando")
	public void testando(){
		System.out.println("AQUI");
		testeaq.testeTime();
	}

}
