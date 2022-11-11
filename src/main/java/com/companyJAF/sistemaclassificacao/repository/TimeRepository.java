package com.companyJAF.sistemaclassificacao.repository;

import com.companyJAF.sistemaclassificacao.model.Time;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimeRepository extends MongoRepository<Time, String> {
    List<Time> findAllByTaEliminadoIn(Boolean taEliminado);
    List<Time> findAllByGrupoInAndTaEliminadoIn(String grupo,Boolean taEliminado);
    List<Time> findAllByGrupoInAndTaEliminadoInOrderByPontuacaoDescQtdGolsDescCartoesVermelhoAscCartoesAmareloAsc(String grupo,Boolean taEliminado);
    List<Time> findAllByTaEliminadoInOrderByQtdGolsDescCartoesVermelhoAscCartoesAmareloAsc(Boolean taEliminado);
    List<Time> findAllByTaEliminadoInOrderByOrdemAsc(Boolean taEliminado);
    List<Time> findAllByTaSemiFinalIn(Boolean taSemiFinal);
    List<Time> findAllByTaEliminadoInAndGrupoInOrderByOrdemAsc(Boolean taEliminado, String grupo);
}