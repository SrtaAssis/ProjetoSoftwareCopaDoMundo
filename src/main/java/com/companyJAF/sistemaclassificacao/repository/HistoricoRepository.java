package com.companyJAF.sistemaclassificacao.repository;

import com.companyJAF.sistemaclassificacao.model.Historico;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoricoRepository extends MongoRepository<Historico, String> {

}