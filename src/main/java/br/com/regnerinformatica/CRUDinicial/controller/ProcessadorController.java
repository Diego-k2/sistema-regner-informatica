package br.com.regnerinformatica.CRUDinicial.controller;

import br.com.regnerinformatica.CRUDinicial.model.dto.ProcessadorDto;
import br.com.regnerinformatica.CRUDinicial.model.entity.ProcessadorModel;
import br.com.regnerinformatica.CRUDinicial.model.service.ProcessadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pecas/processador")
public class ProcessadorController {

    final ProcessadorService processadorService;
    public ProcessadorController(ProcessadorService processadorService) {
        this.processadorService = processadorService;
    }


    @PostMapping
    public ResponseEntity<Object> novoProcessador(@RequestBody @Valid ProcessadorDto processadorDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("TEMOS ALGUM ERRO DE PREENCHIMENTO!");
        }

        if(processadorService.existsByModeloAndFabricanteAndIsAtivo(processadorDto.getModelo(),
                processadorDto.getFabricante())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("MODELO DESTA FABRICANTE JA EXISTE!");
        }

        ProcessadorModel processadorModel = processadorDto.parseToProcessadorModel();
        return ResponseEntity.status(HttpStatus.CREATED).body(processadorService.saveProcessador(processadorModel));
    }

}
