package com.companyJAF.sistemaclassificacao.service;

import com.companyJAF.sistemaclassificacao.dto.Cartoes;
import com.companyJAF.sistemaclassificacao.enums.TipoPartida;
import com.companyJAF.sistemaclassificacao.model.Historico;
import com.companyJAF.sistemaclassificacao.model.Partida;
import com.companyJAF.sistemaclassificacao.model.Time;
import com.companyJAF.sistemaclassificacao.repository.HistoricoRepository;
import com.companyJAF.sistemaclassificacao.repository.PartidaRepository;
import com.companyJAF.sistemaclassificacao.repository.TimeRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
@Data
@Slf4j
public class teste {


    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private PartidaService partidaService;
    @Autowired
    private TimeService timeService;
    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private HistoricoService HistoricoService;
    public void geradorTimes() {
        List<String> nomesTimes = new ArrayList<>();
        nomesTimes.add("Qatar");
        nomesTimes.add("Equador");
        nomesTimes.add("Senegal");
        nomesTimes.add("Holanda");
        nomesTimes.add("Inglaterra");
        nomesTimes.add("Irã");
        nomesTimes.add("EUA");
        nomesTimes.add("País de Gales");
        nomesTimes.add("Argentina");
        nomesTimes.add("Arábia Saudita");
        nomesTimes.add("México");
        nomesTimes.add("polônia");
        nomesTimes.add("França");
        nomesTimes.add("Dinamarca");
        nomesTimes.add("Tunísia");
        nomesTimes.add("Austrália");
        nomesTimes.add("Espanha");
        nomesTimes.add("Alemanha");
        nomesTimes.add("Japão");
        nomesTimes.add("Costa Rica");
        nomesTimes.add("Bélgica");
        nomesTimes.add("Canadá");
        nomesTimes.add("Marrocos");
        nomesTimes.add("Croácia");
        nomesTimes.add("Brasil");
        nomesTimes.add("Sérvia");
        nomesTimes.add("Suíça");
        nomesTimes.add("Camarões");
        nomesTimes.add("Portugal");
        nomesTimes.add("Gana");
        nomesTimes.add("Uruguai");
        nomesTimes.add("Córeia do Sul");

        nomesTimes.forEach(n->{
            timeService.gravarTime(n);
        });

    }
    public void gerarPartidaClassificatorias(TipoPartida tipoPartida) {
        partidaService.gerarPartidas(tipoPartida);
    }
    public void testeDocumentarPartida() {
        Random random = new Random();
        List<Partida> partidas = partidaService.partidasNaoEncerradas();
        partidas.forEach(p->{
            Historico historico = Historico.builder().build();
            historico.setFk_idPartida(p.get_id());
            historico.setFk_idTime(p.getFkIdTimes().get(0));
            historico.setCartoes(Cartoes.builder().build());
            historico.getCartoes().setAmarelo(random.nextInt(3));
            historico.getCartoes().setVermelho(random.nextInt(3));
            historico.setQtdGols(random.nextInt(3)+4);

            Historico historico2 = Historico.builder().build();
            historico2.setFk_idPartida(p.get_id());
            historico2.setFk_idTime(p.getFkIdTimes().get(1));
            historico2.setCartoes(Cartoes.builder().build());
            historico2.getCartoes().setAmarelo(random.nextInt(3));
            historico2.getCartoes().setVermelho(random.nextInt(3));
            historico2.setQtdGols(random.nextInt(3));

            List<Historico> historicos = new ArrayList<>();
            historicos.add(historico);
            historicos.add(historico2);
            HistoricoService.documentarPartida(historicos);
        });

    }



}