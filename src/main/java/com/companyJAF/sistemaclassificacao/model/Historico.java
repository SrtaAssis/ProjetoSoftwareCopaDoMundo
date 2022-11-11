package com.companyJAF.sistemaclassificacao.model;

import com.companyJAF.sistemaclassificacao.dto.Cartoes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Historico {
    @Id
    private String _id;
    private int qtdGols;
    private Cartoes cartoes;
    private String fk_idTime;
    private String fk_idPartida;
}
