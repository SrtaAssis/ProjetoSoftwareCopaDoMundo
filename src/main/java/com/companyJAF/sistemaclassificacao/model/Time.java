package com.companyJAF.sistemaclassificacao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Time {
    @Id
    private String _id;
    private double pontuacao;
    private String nome;
    private int qtdGols;
    private int qtdCartoes;
    private String grupo;
    private Boolean taEliminado;
    private int colocacao;
}
