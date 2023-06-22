package br.com.tech4me.clinica.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.clinica.Model.DadosProcedimentos;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentosController {

    List<DadosProcedimentos> procedimentos = new ArrayList<>();

    @GetMapping
    private List<DadosProcedimentos> obterListaProcedimentos(){
        return procedimentos;
    }

    @PostMapping
    private String postarProcedimentos(@RequestBody DadosProcedimentos procedimentosDados){
        procedimentos.add(procedimentosDados);
        return String.format("Procedimento realizado: %s", procedimentosDados);
    }


    
}
