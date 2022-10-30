package com.companyJAF.sistemaclassificacao.repository;

import com.companyJAF.sistemaclassificacao.model.Partida;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PartidaRepository extends MongoRepository<Partida, String> {

}