package com.companyJAF.sistemaclassificacao.service;

import com.companyJAF.sistemaclassificacao.exception.ExceptionEncontrado;
import com.companyJAF.sistemaclassificacao.model.Time;
import com.companyJAF.sistemaclassificacao.repository.TimeRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
@Data
@Slf4j
public class TimeService {


    @Autowired
    private TimeRepository timeRepository;

    public Time gravarTime(Time time){

    if(timeRepository.findAll().size() == 32){
        throw new ExceptionEncontrado("Chegou ao numero limite de times");
    }
        time.setTaEliminado(false);
        return timeRepository.save(time);
    }

    public List<Time> getTimesDisponiveis(){
        //pega apenas times que não foram eliminados
        List<Time> timesDisponiveis = timeRepository.findAllByTaEliminadoIn(false);

        return timesDisponiveis;
    }

    public void separarGrupos(){
        List<Time> times = timeRepository.findAllByTaEliminadoIn(false);
        //embaralha a lista de times
        Collections.shuffle(times);
        int index = 0;
        times.stream().forEach((t)->{
            if(index<5){
                t.setGrupo("A");
            }
            else if(index >5 && index<10){
                t.setGrupo("B");
            }
            else if(index >10 && index<15){
                t.setGrupo("C");
            }
            else if(index >15 && index<20){
                t.setGrupo("D");
            }
            else if(index >20 && index<27){
                t.setGrupo("E");
            }
            else if(index >27 && index<32){
                t.setGrupo("F");
            }
        }
        );
    }


}