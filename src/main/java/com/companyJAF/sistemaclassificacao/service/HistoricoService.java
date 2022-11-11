package com.companyJAF.sistemaclassificacao.service;

import com.companyJAF.sistemaclassificacao.enums.TipoPartida;
import com.companyJAF.sistemaclassificacao.model.Historico;
import com.companyJAF.sistemaclassificacao.model.Partida;
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
public class HistoricoService {

    @Autowired
    private PartidaRepository partidaRepository;
    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private TimeService timeService;


    public void documentarPartida(List<Historico> historicos){
        //encontrar partida pra encerrar
        Partida partidaDocumentada = partidaRepository.findById(historicos.get(0).getFk_idPartida()).get();
        partidaDocumentada.setTaEncerrada(true);
        partidaRepository.save(partidaDocumentada);
        //setar em times os gols e cartoes
        historicos.forEach(h->timeService.calcularParametrosTime(h.getFk_idTime(),h.getQtdGols(),h.getCartoes()));
        // variavel boolean empate
        boolean taEmpatado = historicos.get(0).getQtdGols() == historicos.get(1).getQtdGols();
        //id vencedor
        String vencedorId = !taEmpatado? getVencedor(historicos) : null;

        if (vencedorId != null) {
            calcularPontuacaoTime(vencedorId, null);
            setTimePerdedorFinais(historicos.stream().filter(h->h.getFk_idTime() != vencedorId).collect(Collectors.toList()).get(0).getFk_idTime());
            // se for a ultima partida vai setar a colocacao do vencedor;
            if(timeService.getTimesDisponiveis().size() == 1){
                timeService.setTimeVencedor(vencedorId);
            }
            if(partidaDocumentada.getTipoPartida() == TipoPartida.semiFinal){
                timeService.setTimeVencedorSemi(vencedorId);
            }

        } else {
            calcularPontuacaoTime(historicos.get(0).getFk_idTime(), historicos.get(1).getFk_idTime());
        }


        historicoRepository.saveAll(historicos);
    }
    private String getVencedor(List<Historico> historicos){
        if(historicos.get(0).getQtdGols() > historicos.get(1).getQtdGols()){
            return historicos.get(0).getFk_idTime();
        }
        return historicos.get(1).getFk_idTime();
    }
    private void calcularPontuacaoTime(String timeid1, String timeid2){

        if(timeid2 != null){
            //calcular pontuacao se deu empate
            timeService.calcularPontuacaoTime(timeid2, true);
            timeService.calcularPontuacaoTime(timeid1,true);
        }
        else{
            timeService.calcularPontuacaoTime(timeid1, false);
        }
    }

    private void setTimePerdedorFinais(String timeId){
        //setar taEliminado=true para o time que perdeu
        int quantTimes = timeService.getTimesDisponiveis().size();
        if( quantTimes <= 4){
            //se sobrar 4 times quer dizer que vamos ter que montar uma semi final
            timeService.setTimePerdedorFinal(timeId);
        }else if(quantTimes <=16){
            timeService.setTimePerdedor(timeId);
        }

    }


}