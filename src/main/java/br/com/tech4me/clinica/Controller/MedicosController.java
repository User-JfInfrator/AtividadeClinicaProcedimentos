package br.com.tech4me.clinica.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.clinica.Model.DadosMedico;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    List<DadosMedico> medicos = new ArrayList<>();

    @GetMapping
    private List<DadosMedico> obterListaMedicos() {
        return medicos;
    }

    @GetMapping("/{nome}")
    private DadosMedico requisicaoGetComParametro(@PathVariable String nome) {
        DadosMedico dadosMedico = medicos.stream()
        .filter(p->p.getNome().equalsIgnoreCase(nome))
        .findFirst().orElse(null);
        return dadosMedico;
    }

    @PostMapping
    private ResponseEntity<String> requisicaoPost(@RequestBody DadosMedico dadosMedico) {
        medicos.add(dadosMedico);
        String mensagem = String.format("Nome: %s | Especialidade: %s | Valor Consulta: %.2f", 
        dadosMedico.getNome(), dadosMedico.getEspecialidade(), dadosMedico.getValorConsulta());
        return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{nome}")
    private String removerMedico(@PathVariable String nome){
        boolean removeu = medicos.removeIf(p->p.getNome().equalsIgnoreCase(nome));
        if(removeu){
           return "Médico removido com sucesso!";
        }
        else{
            return "Médico não localizado!";
        }
    }

    @PutMapping("/{nome}")
    private DadosMedico atualizarMedico(@PathVariable String nome, @RequestBody DadosMedico medicoAtualizado){
        DadosMedico novoMedico = medicos.stream()
        .filter(p->p.getNome().equalsIgnoreCase(nome))
        .findFirst().orElse(null);

        if(novoMedico != null){
            novoMedico.setNome(medicoAtualizado.getNome());
            novoMedico.setEspecialidade(medicoAtualizado.getEspecialidade());
            novoMedico.setValorConsulta(medicoAtualizado.getValorConsulta());
        };

        return novoMedico;

    }
    
}
