package com.companyJAF.sistemaclassificacao.repository;

import com.companyJAF.sistemaclassificacao.model.Time;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimeRepository extends MongoRepository<Time, String> {
    List<Time> findAllByTaEliminadoIn(Boolean taEliminado);
}