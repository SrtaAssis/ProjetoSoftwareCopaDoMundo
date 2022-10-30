package com.companyJAF.sistemaclassificacao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Partida {
    @Id
    private String _id;
    private Date data_partida;
    private List<String> fk_idTimes;
}
