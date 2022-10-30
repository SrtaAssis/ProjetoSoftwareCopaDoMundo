package com.companyJAF.sistemaclassificacao.service;

import com.companyJAF.sistemaclassificacao.repository.PartidaRepository;
import com.companyJAF.sistemaclassificacao.repository.TimeRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Data
@Slf4j
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

}