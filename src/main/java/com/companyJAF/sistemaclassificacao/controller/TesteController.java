package com.companyJAF.sistemaclassificacao.controller;

import com.companyJAF.sistemaclassificacao.enums.TipoPartida;
import com.companyJAF.sistemaclassificacao.service.teste;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@Slf4j
@Data
@Builder
@RestController
@RequestMapping(value = "teste")
public class TesteController {

    @Autowired
    private teste testeaq;

    @GetMapping("/geradorTimes")
    public void geradorTimes(){
        System.out.println("geradorTimes");
        testeaq.geradorTimes();
    }

    @GetMapping("/gerarPartidaClassificatorias")
    public void gerarPartidaClassificatorias(){
        System.out.println("gerarPartidaClassificatorias");
        testeaq.gerarPartidaClassificatorias(TipoPartida.classificatorias);
    }

    @GetMapping("/testeDocumentarPartida")
    public void testeDocumentarPartida(){
        System.out.println("testeDocumentarPartida");
        testeaq.testeDocumentarPartida();
    }

    @GetMapping("/gerarPartidaOitavas")
    public void gerarPartidaOitavas(){
        System.out.println("gerarPartidaOitavas");
        testeaq.gerarPartidaClassificatorias(TipoPartida.oitavas);
    }

    @GetMapping("/gerarPartidaFinais")
    public void gerarPartidaFinais(){
        System.out.println("gerarPartidaFinais");
        testeaq.gerarPartidaClassificatorias(TipoPartida.finais);
    }

}