package com.project.statusnotafiscal.dto;

import com.project.statusnotafiscal.StatusEnum;
import com.project.statusnotafiscal.exeptions.InvalidKeyOfData;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class ScrapedNFtoNotaFiscalDTO extends Object{
    private String autorizador;
    private String autorizacao;
    private String retornoAutorizacao;
    private String inutilizacao;
    private String consultaProtocolo;
    private String statusServico;
    private String tempoMedio;
    private String consultaCadastro;
    private String recepcaoEvento;
    private String ultimaVerificacao;

    public ScrapedNFtoNotaFiscalDTO(Map<String,String> mapOfScrapedData){
        for (Map.Entry<String, String> entry : mapOfScrapedData.entrySet()) {
            String chave = entry.getKey();
            String valor = entry.getValue();
            try {
                switch (chave) {
                    case "autorizador":
                        this.setAutorizador(valor);
                        break;
                    case "autorizacao":
                        this.setAutorizacao(this.getStatusString(valor));
                        break;
                    case "retornoAutorizacao":
                        this.setRetornoAutorizacao(this.getStatusString(valor));
                        break;
                    case "inutilizacao":
                        this.setInutilizacao(this.getStatusString(valor));
                        break;
                    case "consultaProtocolo":
                        this.setConsultaProtocolo(this.getStatusString(valor));
                        break;
                    case "statusServico":
                        this.setStatusServico(this.getStatusString(valor));
                        break;
                    case "tempoMedio":
                        this.setTempoMedio(valor);
                        break;
                    case "consultaCadastro":
                        this.setConsultaCadastro(this.getStatusString(valor));
                        break;
                    case "recepcaoEvento":
                        this.setRecepcaoEvento(this.getStatusString(valor));
                        break;
                    case "ultimaVerificacao":
                        this.setUltimaVerificacao(valor);
                        break;
                    default:
                        throw new InvalidKeyOfData(chave);
            }
            } catch (InvalidKeyOfData e) {
                e.printStackTrace();
            }

        }
    }

    private String getStatusString(String valor){
        if(valor.contains("verde")){
            return "VERDE";
        }else if(valor.contains("amarela")){
            return "AMARELO";
        }else if(valor.contains("vermelho")){
            return "VERMELHO";
        }else if (valor.contains("span")){
            return "-";
        }else {
            return "ERRO";
        }
    }




}
