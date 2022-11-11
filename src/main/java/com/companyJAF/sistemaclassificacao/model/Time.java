package com.companyJAF.sistemaclassificacao.model;

import com.companyJAF.sistemaclassificacao.dto.Cartoes;
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
    private int pontuacao;
    private String nome;
    private int qtdGols;
    private Cartoes cartoes;
    private String grupo;
    private Boolean taEliminado;
    private Boolean taSemiFinal;
    private int ordem;
    private int colocacao;
}
