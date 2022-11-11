package com.companyJAF.sistemaclassificacao.model;

import com.companyJAF.sistemaclassificacao.enums.TipoPartida;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Partida {
    @Id
    private String _id;
    private LocalDateTime dataPartida;
    private List<String> fkIdTimes;
    private Boolean taEncerrada;
    private TipoPartida tipoPartida;
}
