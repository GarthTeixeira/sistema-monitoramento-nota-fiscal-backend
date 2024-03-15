package com.project.statusnotafiscal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.statusnotafiscal.StatusEnum;
import com.project.statusnotafiscal.dto.ScrapedNFtoNotaFiscalDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Document
public class NotaFiscal {
    @Id
    private String id;
    private String autorizador;
    private StatusEnum autorizacao;
    private StatusEnum retornoAutorizacao;
    private StatusEnum inutilizacao;
    private StatusEnum consultaProtocolo;
    private StatusEnum statusServico;
    private String tempoMedio;
    private StatusEnum consultaCadastro;
    private StatusEnum recepcaoEvento;
    private String ultimaVerificacao;
    private LocalDateTime created;
    private LocalDateTime updated;

    public NotaFiscal (String autorizador,
                  StatusEnum autorizacao,
                  StatusEnum retornoAutorizacao,
                  StatusEnum inutilizacao,
                  StatusEnum consultaProtocolo,
                  StatusEnum statusServico,
                  String tempoMedio,
                  StatusEnum consultaCadastro,
                  StatusEnum recepcaoEvento,
                  String ultimaVerificacao,
                  LocalDateTime created,
                  LocalDateTime updated){

         this.autorizador = autorizador;
         this.autorizacao = autorizacao;
         this.retornoAutorizacao = retornoAutorizacao;
         this.inutilizacao = inutilizacao;
         this.consultaProtocolo = consultaProtocolo;
         this.statusServico = statusServico;
         this.tempoMedio = tempoMedio;
         this.consultaCadastro = consultaCadastro;
         this.recepcaoEvento = recepcaoEvento;
         this.ultimaVerificacao = ultimaVerificacao;
         this.created = created;
         this.updated = updated;
    }

}
