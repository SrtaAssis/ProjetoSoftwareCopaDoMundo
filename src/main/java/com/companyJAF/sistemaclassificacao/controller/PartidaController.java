package com.companyJAF.sistemaclassificacao.controller;

import com.companyJAF.sistemaclassificacao.enums.TipoPartida;
import com.companyJAF.sistemaclassificacao.model.Partida;
import com.companyJAF.sistemaclassificacao.service.PartidaService;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Data
@Builder
@RestController
@RequestMapping(value = "partida")
public class PartidaController {

    @Autowired
    PartidaService partidaService;

    @ResponseBody
    @PostMapping(value = "/gerar-partida", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Partida>> gerarPartida(TipoPartida tipoPartida) throws Exception {
        return ResponseEntity.ok().body(partidaService.gerarPartidas(tipoPartida));
    }

}
