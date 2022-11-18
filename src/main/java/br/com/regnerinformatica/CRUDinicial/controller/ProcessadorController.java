package br.com.regnerinformatica.CRUDinicial.controller;

import br.com.regnerinformatica.CRUDinicial.model.dto.ProcessadorDto;
import br.com.regnerinformatica.CRUDinicial.model.entity.ProcessadorModel;
import br.com.regnerinformatica.CRUDinicial.model.service.ProcessadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/todos")
    public ResponseEntity<Object> buscaTodosProcessadores(){
        return !processadorService.findAllByIsAtivo().isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(processadorService.findAllByIsAtivo()) :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhum processador em nosso sistema!");
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<Object> buscaPorModelo(@PathVariable String modelo) {
        return !processadorService.findAllByModeloAndIsAtivo(modelo).isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(processadorService.findAllByModeloAndIsAtivo(modelo)) :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhum processador em nosso sistema!");
    }

    @GetMapping("/fabricante/{fabricante}")
    public ResponseEntity<Object> buscaPorFabricante(@PathVariable String fabricante){
        return !processadorService.findAllByFabricanteAndIsAtivo(fabricante).isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(processadorService.findAllByFabricanteAndIsAtivo(fabricante)):
                ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhum processador em nosso sistema!");
    }

    @GetMapping("/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> buscaPorModelEFabricante(@PathVariable String modelo, @PathVariable String fabricante){
        return !processadorService.findByModeloAndFabricanteAndIsAtivo(modelo,fabricante).isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(processadorService.findByModeloAndFabricanteAndIsAtivo(modelo, fabricante)):
                ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhum processador em nosso sistema!");
    }


}
