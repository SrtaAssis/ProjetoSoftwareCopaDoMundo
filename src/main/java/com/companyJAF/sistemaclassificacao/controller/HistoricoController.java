package com.companyJAF.sistemaclassificacao.controller;

import com.companyJAF.sistemaclassificacao.enums.TipoPartida;
import com.companyJAF.sistemaclassificacao.model.Historico;
import com.companyJAF.sistemaclassificacao.model.Partida;
import com.companyJAF.sistemaclassificacao.service.HistoricoService;
import com.companyJAF.sistemaclassificacao.service.PartidaService;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@Data
@Builder
@RestController
@RequestMapping(value = "historico")
public class HistoricoController {

    @Autowired
    HistoricoService historicoService;

    @ResponseBody
    @PostMapping(value = "/documentar-partida", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity documentarPartida(List<Historico> historicos) throws Exception {
        historicoService.documentarPartida(historicos);
        return ResponseEntity.ok().body(true);
    }

}
