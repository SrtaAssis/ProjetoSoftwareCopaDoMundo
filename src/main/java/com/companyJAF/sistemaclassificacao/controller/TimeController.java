package com.companyJAF.sistemaclassificacao.controller;

import com.companyJAF.sistemaclassificacao.model.Time;
import com.companyJAF.sistemaclassificacao.service.TimeService;
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
@RequestMapping(value = "time")
public class TimeController {

    @Autowired
    TimeService timeService;

    @ResponseBody
    @PostMapping(value = "/gravar-time", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Time> criarTime(String nome) throws Exception {
        return ResponseEntity.ok().body(timeService.gravarTime(nome));
    }
}