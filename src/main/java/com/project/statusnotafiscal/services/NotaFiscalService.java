package com.project.statusnotafiscal.services;


import com.project.statusnotafiscal.StatusEnum;
import com.project.statusnotafiscal.dto.ScrapedNFtoNotaFiscalDTO;
import com.project.statusnotafiscal.models.NotaFiscal;
import com.project.statusnotafiscal.repositories.NotasFiscaisRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class NotaFiscalService {

    private final NotasFiscaisRepository notasFiscaisRepository;

    private final MongoTemplate mongoTemplate;
    public List<NotaFiscal> findByEstado(String estado) {
        Optional<List<NotaFiscal>> notasFicaisOptional = notasFiscaisRepository.findNotaFiscalByAutorizador(estado);
        return notasFicaisOptional.orElseGet(ArrayList::new);
    }

    public List<NotaFiscal> findAll(){
        return notasFiscaisRepository.findAll();
    }


    public List<NotaFiscal> findByData(String data) {
        String regex = ".*" + data + ".*";
        Optional<List<NotaFiscal>> notasFicaisOptional = notasFiscaisRepository.findNotaFiscalByUltimaVerificacaoRegex(regex);
        return notasFicaisOptional.orElseGet(ArrayList::new);
    }

    public Object findMoreUnavailabeOcurrency() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("inutilizacao").in("AMARELO", "VERMELHO")),
                Aggregation.group("autorizador").count().as("total")
        );

        AggregationResults<Object> results = mongoTemplate.aggregate(
                aggregation, "notaFiscal", Object.class);

        results.forEach(System.out::println);

        return results.getMappedResults();
    }

    public List<Object> findMostUpdated() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("autorizador").max("created").as("dataMaxima")
        );

        AggregationResults<Object> results = mongoTemplate.aggregate(
                aggregation, "notaFiscal", Object.class);

        results.forEach(System.out::println);

        return results.getMappedResults();
    }

    public void saveAll(List<ScrapedNFtoNotaFiscalDTO> dtos){
        List<NotaFiscal> notasFiscais = getCollect(dtos);

        LocalDateTime now = LocalDateTime.now();

        notasFiscais.forEach(nf -> {
            nf.setCreated(now);
            nf.setUpdated(now);
        });

        notasFiscaisRepository.saveAll(notasFiscais);

        System.out.println(notasFiscais);
    }

    private static List<NotaFiscal> getCollect(List<ScrapedNFtoNotaFiscalDTO> dtos) {
        return dtos.stream().map(NotaFiscalService::dtoToEntity).collect(Collectors.toList());
    }

    private static  NotaFiscal dtoToEntity (ScrapedNFtoNotaFiscalDTO dto){
        String autorizador = dto.getAutorizador();
        StatusEnum autorizacao = StatusEnum.passStatusString(dto.getAutorizacao());
        StatusEnum retornoAutorizacao = StatusEnum.passStatusString(dto.getRetornoAutorizacao());
        StatusEnum inutilizacao = StatusEnum.passStatusString(dto.getInutilizacao());
        StatusEnum consultaProtocolo = StatusEnum.passStatusString(dto.getConsultaProtocolo());
        StatusEnum statusServico = StatusEnum.passStatusString(dto.getStatusServico());
        String tempoMedio =dto.getTempoMedio();
        StatusEnum consultaCadastro = StatusEnum.passStatusString(dto.getConsultaCadastro());
        StatusEnum recepcaoEvento = StatusEnum.passStatusString(dto.getRecepcaoEvento());
        String ultimaVerificacao = dto.getUltimaVerificacao();

        return new NotaFiscal(autorizador,
                autorizacao,
                retornoAutorizacao,
                inutilizacao,
                consultaProtocolo,
                statusServico,
                tempoMedio,
                consultaCadastro,
                recepcaoEvento,
                ultimaVerificacao,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }


}
