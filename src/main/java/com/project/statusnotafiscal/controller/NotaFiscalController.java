package com.project.statusnotafiscal.controller;

import com.project.statusnotafiscal.models.NotaFiscal;
import com.project.statusnotafiscal.services.NotaFiscalService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("api/v1/notas-fiscais")
@AllArgsConstructor
public class NotaFiscalController {

    @Autowired
    private final NotaFiscalService notaFiscalService;

    @GetMapping
    public List<NotaFiscal> findAll(){
        return notaFiscalService.findAll();
    }

    @GetMapping("/mostUpdated")
    public List<Object> findMostUpdated(){
        return notaFiscalService.findMostUpdated();
    }
    @GetMapping("/estado/{estado}")
    public List<NotaFiscal> findByEstado(@PathVariable("estado") String estado, @RequestParam boolean lastOne){
        System.out.println(lastOne);
        List<NotaFiscal> notasFiscais= notaFiscalService.findByEstado(estado);
        if(lastOne){
            Comparator<NotaFiscal> comparator = Comparator.comparing(NotaFiscal::getCreated);
            return notasFiscais.stream().sorted(comparator.reversed()).limit(1)
                    .collect(Collectors.toList());
        }
        return notasFiscais; //testado
    }

    @GetMapping("/data")
    public List<NotaFiscal> findByData(@RequestParam String value){
        return notaFiscalService.findByData(value);
    }  //testado

    @GetMapping("/unavailable")
    public Object findMoreUnavailabeOcurrency() {
        return notaFiscalService.findMoreUnavailabeOcurrency();
    } //testado


}
