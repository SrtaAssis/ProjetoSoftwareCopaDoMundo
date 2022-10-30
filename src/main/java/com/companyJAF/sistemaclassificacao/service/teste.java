package com.companyJAF.sistemaclassificacao.service;

import com.companyJAF.sistemaclassificacao.model.Time;
import com.companyJAF.sistemaclassificacao.repository.TimeRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Data
@Slf4j
public class teste {


    @Autowired
    private TimeRepository timeRepository;

    public void testeTime() {

        Time time = Time.builder().build();
        time.setGrupo("A");
        time.setNome("teste");
        time.setPontuacao(321);
        time.setQtdCartoes(3);
        time.setTaEliminado(false);
        time.setColocacao(1);
        time.setQtdGols(54);
        System.out.println(time);
        timeRepository.save(time);
    }


}