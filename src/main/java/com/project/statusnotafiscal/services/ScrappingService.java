package com.project.statusnotafiscal.services;

import com.project.statusnotafiscal.dto.ScrapedNFtoNotaFiscalDTO;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ScrappingService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private NotaFiscalService notaFiscalService;

    final String url = "https://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx";

    final int delay = 300000; //mudar para 5 minutos quando enviar

    @Scheduled(fixedDelay = delay)
    void scrap() throws IOException {
        retriveData();
    }

    void retriveData() throws IOException {
        log.info("Retriving data... {}", System.currentTimeMillis());
        try {
            Document doc = Jsoup.connect(url).get();

            Element table = doc.getElementsByClass("tabelaListagemDados").get(0); //select the first table.

            String ultimaVerificao = getUltimaVerificacao(table);

            List<Object> tableCells = scrapRows(table);

            List<ScrapedNFtoNotaFiscalDTO> scrapedNFtoNotaFiscalDTOS = tableCells.stream()
                    .map(cell -> createScrapedNFtoNotaFiscalDTO((List<Object>) cell, ultimaVerificao))
                    .collect(Collectors.toList());

            notaFiscalService.saveAll(scrapedNFtoNotaFiscalDTOS);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private ScrapedNFtoNotaFiscalDTO createScrapedNFtoNotaFiscalDTO(List<Object> tableCell, String ultimaVerificaoNode) {

        Map<String, String> map = new HashMap<>();
//        Set<String> setOfKeys = Objects.requireNonNull(mongoTemplate.getCollection("notaFiscal").find().first()).keySet();
        List<String> listOfKeys =  List.of("autorizador", "autorizacao", "retornoAutorizacao", "inutilizacao", "consultaProtocolo", "statusServico", "tempoMedio", "consultaCadastro", "recepcaoEvento");

        for (int i = 0; i < listOfKeys.size() ; i++) {
            String key = listOfKeys.get(i);
            Object cell = tableCell.get(i);
            Element element = (Element) cell;
            String text = element.html();
            map.put(key, text);
        }

        map.put("ultimaVerificacao", ultimaVerificaoNode);

        return new ScrapedNFtoNotaFiscalDTO(map);
    }

    private String trataUltimaVerificacao(Node ultimaVerificaoNode){
        return ultimaVerificaoNode.toString().replace("- Última Verificação: ", "");
    }

    private String getUltimaVerificacao(Element table ){
        Node ultimaVerificaoNode = table.getElementsByTag("caption").get(0)
                .getElementsByTag("span").get(0).childNodes().get(0);
        return  trataUltimaVerificacao(ultimaVerificaoNode);
    }

    private List<Object> scrapRows(Element table){
        List<Element> tableRows = table.getElementsByTag("tbody").get(0).getElementsByTag("tr");
        tableRows.remove(0); //remove the first row, which is the header of the table

        List<Object> tableCells = new ArrayList<>();

        for (Element row : tableRows) {
            tableCells.add(row.getElementsByTag("td"));
        }

        return tableCells;
    }

}
