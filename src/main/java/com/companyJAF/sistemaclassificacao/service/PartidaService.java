package com.companyJAF.sistemaclassificacao.service;

import com.companyJAF.sistemaclassificacao.enums.TipoPartida;
import com.companyJAF.sistemaclassificacao.model.Historico;
import com.companyJAF.sistemaclassificacao.model.Partida;
import com.companyJAF.sistemaclassificacao.model.Time;
import com.companyJAF.sistemaclassificacao.repository.HistoricoRepository;
import com.companyJAF.sistemaclassificacao.repository.PartidaRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Data
@Slf4j
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;
    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private TimeService timeService;

    public List<Partida> gerarPartidas(TipoPartida tipoPartida){

        switch (tipoPartida){
            case classificatorias:
                return gerarPartidasClassificatorias();
            case oitavas:
                calcularVencedor();
                gerarPartidaOitavas("A","B",7,1);
                gerarPartidaOitavas("B","C",14,3);
                gerarPartidaOitavas("C","D",21,5);
                gerarPartidaOitavas("D","E",28,7);
                gerarPartidaOitavas("E","F",35,9);
                gerarPartidaOitavas("F","G",42,11);
                gerarPartidaOitavas("G","H",49,13);
                gerarPartidaOitavas("H","A",56,15);
                return partidaRepository.findAllByDataPartidaAfterAndTaEncerradaIn(LocalDateTime.now(),false);
            case finais:
                System.out.println(timeService.getTimeSemiFinal().size());
                if(timeService.getTimeSemiFinal().size() == 2){
                    gerarPartidaSemiFinal();
                }
                return gerarPartidasFinais();
            default:
                return null;
        }
    }

    public  List<Partida> gerarPartidasClassificatorias(){
        List<Partida> todasPartidas = new ArrayList<Partida>();
        List<String> grupos = new ArrayList<String>();
        grupos.add("A"); grupos.add("B"); grupos.add("C"); grupos.add("D");
        grupos.add("E"); grupos.add("F"); grupos.add("G"); grupos.add("H");
        timeService.separarGrupos();
        grupos.forEach(g->{
            List<String> timesGrupo = timeService.pegarTimesPorGrupo(g).stream().map(t->t.get_id()).collect(Collectors.toList());
            //times que ja entraram em todas as suas possibilidades de partida
            List<String> timesQueJaForam = new ArrayList<String>();
            LocalDateTime dataPartida = LocalDateTime.now();
            timesGrupo.forEach(t->{
                timesQueJaForam.add(t);
                timesGrupo.forEach(t2->{
                    // se na lista de times que ja foram não conter o time ainda então se cria uma partida
                    if(!timesQueJaForam.contains(t2)){
                        //as partidas vão acontecer a cada 7 dias
                        dataPartida.plusDays(7);

                        List<String> idtimes = new ArrayList<>();
                        idtimes.add(t); idtimes.add(t2);

                        Partida partida = construirPartida(idtimes,dataPartida,TipoPartida.classificatorias);
                        todasPartidas.add(partida);
                    }
                });
            });
        });
        return todasPartidas;
    }
    public void gerarPartidaOitavas(String grupo1, String grupo2, int quantDias, int numeroOrdem){
        //pega o primeiro time de um grupo e o segundo do outro grupo
        List<String> time1 = timeService.getTimesPorGrupoOrdem(grupo1,0,numeroOrdem).stream().map(t->t.get_id()).collect(Collectors.toList());
        List<String> time2 = timeService.getTimesPorGrupoOrdem(grupo2,1,++numeroOrdem).stream().map(t->t.get_id()).collect(Collectors.toList());
        List<String> idtimes = new ArrayList<>();

        idtimes.add(time1.get(0));
        idtimes.add(time2.get(1));
        construirPartida(idtimes,LocalDateTime.now().plusDays(quantDias),TipoPartida.oitavas);
        }

    public List<Partida> gerarPartidasFinais(){
        List<Partida> todasPartidas = new ArrayList<Partida>();
        //pegar times que nao foram eliminados e ordenados por melhor desempenho
        List<String> times = timeService.getTimesByOrdem().stream().map(t->t.get_id()).collect(Collectors.toList());
        int i=0;
        LocalDateTime dataPartida = LocalDateTime.now();
        for( i=0; i<times.size(); i=i+2){

            dataPartida.plusDays(7);
            List<String> idtimes = new ArrayList<>();
            idtimes.add(times.get(i)); idtimes.add(times.get(i+1));

            Partida partida = construirPartida(idtimes,dataPartida,TipoPartida.finais);
            todasPartidas.add(partida);
        }

        return todasPartidas;
    }


    public Partida construirPartida(List<String> idtimes, LocalDateTime dataPartida, TipoPartida tipoPartida){
        Partida partida = Partida.builder().build();
        partida.setFkIdTimes(idtimes);
        partida.setDataPartida(dataPartida);
        partida.setTaEncerrada(false);
        partida.setTipoPartida(tipoPartida);
        partidaRepository.save(partida);
        return partida;
    }

    public Partida gerarPartidaSemiFinal(){
        List<String> idtimes = timeService.getTimeSemiFinal().stream().map(t->t.get_id()).collect(Collectors.toList());
        return construirPartida(idtimes,LocalDateTime.now().plusDays(3),TipoPartida.semiFinal);
    }

    public List<Partida> partidasNaoEncerradas(){
        List<Partida> partidasEncerradas = partidaRepository.findAllByTaEncerradaIn(false);
        return partidasEncerradas;
    }
    public List<Partida> partidasEncerradas(){
        List<Partida> partidasEncerradas = partidaRepository.findAllByTaEncerradaIn(true);
        return partidasEncerradas;
    }

    public void calcularVencedor(){
        List<String> grupos = new ArrayList<String>();
        grupos.add("A"); grupos.add("B"); grupos.add("C"); grupos.add("D");
        grupos.add("E"); grupos.add("F"); grupos.add("G"); grupos.add("H");
        grupos.forEach(g->{
            //ordena em quem tem maior pontuacao, maior quant gols e menos quant de cartoes
            timeService.ordenarTimesPorGrupo(g);
        });
    }

}