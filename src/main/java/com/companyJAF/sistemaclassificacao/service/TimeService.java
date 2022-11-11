package com.companyJAF.sistemaclassificacao.service;

import com.companyJAF.sistemaclassificacao.dto.Cartoes;
import com.companyJAF.sistemaclassificacao.enums.TipoPartida;
import com.companyJAF.sistemaclassificacao.exception.ExceptionEncontrado;
import com.companyJAF.sistemaclassificacao.model.Historico;
import com.companyJAF.sistemaclassificacao.model.Time;
import com.companyJAF.sistemaclassificacao.repository.PartidaRepository;
import com.companyJAF.sistemaclassificacao.repository.TimeRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Data
@Slf4j
public class TimeService {


    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private PartidaRepository partidaRepository;

    public Time gravarTime(String nome){

    if(timeRepository.findAll().size() == 32){
        throw new ExceptionEncontrado("Chegou ao numero limite de times");
    }
        Time time = Time.builder().build();
        time.setNome(nome);
        time.setCartoes(Cartoes.builder().build());
        time.setTaEliminado(false);
        return timeRepository.save(time);
    }

    public List<Time> getTimesDisponiveis(){
        //pega apenas times que não foram eliminados
        List<Time> timesDisponiveis = timeRepository.findAllByTaEliminadoIn(false);

        return timesDisponiveis;
    }

    public List<Time> getTimesEliminados(){
        //pega apenas times que foram eliminados
        List<Time> timesDisponiveis = timeRepository.findAllByTaEliminadoIn(true);

        return timesDisponiveis;
    }

    public void separarGrupos(){
        List<Time> times = getTimesDisponiveis();
        //embaralha a lista de times
        Collections.shuffle(times);

        for(int index=0;index<times.size();index++){

            if(index<4){
                times.get(index).setGrupo("A");
            }
            else if(index >= 4 && index<8){

                times.get(index).setGrupo("B");
            }
            else if(index >=8 && index<12){
                times.get(index).setGrupo("C");
            }
            else if(index >=12 && index<16){
                times.get(index).setGrupo("D");
            }
            else if(index >=16 && index<20){
                times.get(index).setGrupo("E");
            }
            else if(index >=20 && index<24){
                times.get(index).setGrupo("F");
            }
            else if(index >=24 && index<28){
                times.get(index).setGrupo("G");
            }
            else if(index >=28 && index<32){
                times.get(index).setGrupo("H");
            }
        }
        timeRepository.saveAll(times);

    }

    public List<Time> pegarTimesPorGrupo(String grupo){
        List<Time> timesPorGrupo = timeRepository.findAllByGrupoInAndTaEliminadoIn(grupo,false);
        return timesPorGrupo;
    }

    public List<Time> getTimesPorGrupoOrdem(String grupo,int index, int ordem){
        List<Time> times = timeRepository.findAllByTaEliminadoInAndGrupoInOrderByOrdemAsc(false,grupo);
        times.get(index).setOrdem(ordem);
        return times;
    }

    public List<Time> pegarTimePorGrupo(String grupo){

        List<Time> timesPorGrupo = timeRepository.findAllByGrupoInAndTaEliminadoInOrderByPontuacaoDescQtdGolsDescCartoesVermelhoAscCartoesAmareloAsc(grupo,false);

        System.out.println(timesPorGrupo.size());
       if(timesPorGrupo.size() == 0){ return null; }

        timesPorGrupo.get(0).setOrdem(1);
        timesPorGrupo.get(1).setOrdem(2);
        timesPorGrupo.get(2).setTaEliminado(true);
        timesPorGrupo.get(3).setTaEliminado(true);
        timeRepository.saveAll(timesPorGrupo);

        return timesPorGrupo;
    }
    public void ordenarTimesPorGrupo(String grupo){

        List<Time> timesPorGrupo = timeRepository.findAllByGrupoInAndTaEliminadoInOrderByPontuacaoDescQtdGolsDescCartoesVermelhoAscCartoesAmareloAsc(grupo,false);

        timesPorGrupo.get(0).setOrdem(1);
        timesPorGrupo.get(1).setOrdem(2);
        timesPorGrupo.get(2).setTaEliminado(true);
        timesPorGrupo.get(3).setTaEliminado(true);
        timeRepository.saveAll(timesPorGrupo);
    }

    public List<Time> getTimesByOrdem() {
        return timeRepository.findAllByTaEliminadoInOrderByOrdemAsc(false);
    }

    public List<Time> getTimeSemiFinal() {
        return timeRepository.findAllByTaSemiFinalIn(true);
    }


    public void calcularPontuacaoTime(String timeId,Boolean empate){
        Time time = timeRepository.findById(timeId).get();
        int pontuacao = empate? time.getPontuacao()+1 : time.getPontuacao()+3;
        time.setPontuacao(pontuacao);
        timeRepository.save(time);

    }

    public void calcularParametrosTime(String timeId, int gols, Cartoes cartoes){
        Time time = timeRepository.findById(timeId).get();
        int qtdgols =  time.getQtdGols() + gols;

        int qtdCartoesVermelho =  time.getCartoes().getVermelho() + cartoes.getVermelho();
        int qtdCartoesAmarelo =  time.getCartoes().getAmarelo() + cartoes.getAmarelo();
        Cartoes qtsCartoes = Cartoes.builder().build();
        qtsCartoes.setVermelho(qtdCartoesVermelho);
        qtsCartoes.setAmarelo(qtdCartoesAmarelo);

        time.setQtdGols(qtdgols);
        time.setCartoes(qtsCartoes);
        timeRepository.save(time);

    }

    public void setTimePerdedor(String timeId){
        Time time = timeRepository.findById(timeId).get();
        time.setTaEliminado(true);
        timeRepository.save(time);
    }
    public void setTimePerdedorFinal(String timeId){
        Time time = timeRepository.findById(timeId).get();
        List<Time> timesDisponiveis = getTimesDisponiveis();

        if(timesDisponiveis.size() == 4 || timesDisponiveis.size() == 3){
            time.setTaSemiFinal(true);
        }
        else if(timesDisponiveis.size() == 2 && !time.getTaEliminado()){
            time.setColocacao(2);
        }
        time.setTaEliminado(true);
        timeRepository.save(time);
    }
    public void setTimeVencedorSemi(String timeId){
        Time time = timeRepository.findById(timeId).get();
        time.setColocacao(3);
        time.setTaEliminado(true);
        timeRepository.save(time);
    }

    public void setTimeVencedor(String timeId){
        Time time = timeRepository.findById(timeId).get();
        time.setColocacao(1);
        timeRepository.save(time);
    }

}