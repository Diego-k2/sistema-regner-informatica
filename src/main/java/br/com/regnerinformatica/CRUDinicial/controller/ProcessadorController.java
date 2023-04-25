package br.com.regnerinformatica.CRUDinicial.controller;

import br.com.regnerinformatica.CRUDinicial.model.dto.ProcessadorDto;
import br.com.regnerinformatica.CRUDinicial.model.entity.ProcessadorModel;
import br.com.regnerinformatica.CRUDinicial.model.service.ProcessadorService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
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

    @PutMapping("/atualiza/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> atualizaProcessador(@PathVariable String modelo, @PathVariable String fabricante,
                                                      @RequestBody ProcessadorDto processadorDto) {

        if(!processadorService.existsByModeloAndFabricanteAndIsAtivo(modelo, fabricante)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Processador não consta em nossa base de dados");
        }

        ProcessadorModel processadorModel = processadorService.findByModeloAndFabricanteAndIsAtivo(modelo, fabricante).get();

        processadorModel.setModelo(processadorDto.getModelo());
        processadorModel.setDescricao(processadorDto.getDescricao());
        processadorModel.setSoquete(processadorDto.getSoquete());
        processadorModel.setGeracao(processadorDto.getGeracao());
        processadorModel.setFabricante(processadorDto.getFabricante());
        try{
            processadorModel.setEstoqueAtual(Integer.parseInt(processadorDto.getEstoqueAtual()));
            processadorModel.setEstoqueMaximo(Integer.parseInt(processadorDto.getEstoqueMaximo()));
        } catch(Exception e) {
            processadorModel.setEstoqueAtual(-1);
            processadorModel.setEstoqueMaximo(-1);
        }
        processadorModel.setFrequencia(processadorDto.getFrequencia());

        return ResponseEntity.status(HttpStatus.OK).body(processadorService.saveProcessador(processadorModel));
    }

    @DeleteMapping("/desativa/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> deativaProcessador(@PathVariable String modelo, @PathVariable String fabricante){

        if(!processadorService.existsByModeloAndFabricanteAndIsAtivo(modelo, fabricante)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Processador não consta em nossa base de dados");
        }

        ProcessadorModel processadorModel = processadorService.findByModeloAndFabricanteAndIsAtivo(modelo, fabricante).get();

        processadorModel.setIsAtivo("0");

        processadorService.saveProcessador(processadorModel);

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Processador desativado de nosso sistema");
    }

    @DeleteMapping("/delete/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> deletaProcessador(@PathVariable String modelo, @PathVariable String fabricante){

        if(!processadorService.existsByModeloAndFabricanteAndIsAtivo(modelo, fabricante)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Processador não consta em nossa base de dados");
        }

        ProcessadorModel processadorModel = processadorService.findByModeloAndFabricante(modelo, fabricante).get();
        processadorService.deleteProcessador(processadorModel);

        return ResponseEntity.status(HttpStatus.OK).body("Processador deletado de nossa base de dados");
    }
}
