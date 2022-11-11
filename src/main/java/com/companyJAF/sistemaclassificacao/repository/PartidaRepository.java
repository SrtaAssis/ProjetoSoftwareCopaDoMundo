package com.companyJAF.sistemaclassificacao.repository;

import com.companyJAF.sistemaclassificacao.enums.TipoPartida;
import com.companyJAF.sistemaclassificacao.model.Partida;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PartidaRepository extends MongoRepository<Partida, String> {
    List<Partida> findAllByDataPartidaAfter(LocalDateTime dataAtual);
    List<Partida> findAllByDataPartidaBefore(LocalDateTime dataAtual);
    List<Partida> findAllByDataPartidaAfterAndTaEncerradaIn(LocalDateTime dataAtual,Boolean taEncerrada);
    List<Partida> findAllByTaEncerradaIn(Boolean taEncerrada);
    List<Partida> findAllByTipoPartidaIn(TipoPartida tipoPartida);


}