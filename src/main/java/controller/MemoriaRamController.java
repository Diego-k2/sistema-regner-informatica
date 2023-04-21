package controller;

import br.com.regnerinformatica.CRUDinicial.model.dto.MemoriaRamDto;
import br.com.regnerinformatica.CRUDinicial.model.dto.ProcessadorDto;
import br.com.regnerinformatica.CRUDinicial.model.entity.MemoriaRamModel;
import br.com.regnerinformatica.CRUDinicial.model.service.MemoriaRamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pecas/memoria-ram")
public class MemoriaRamController {

    final MemoriaRamService memoriaRamService;
    public MemoriaRamController(MemoriaRamService memoriaRamService){
        this.memoriaRamService = memoriaRamService;
    }

    @PostMapping
    public ResponseEntity<Object> novoProcessador(@RequestBody @Valid MemoriaRamDto memoriaRamDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("TEMOS ALGUM ERRO DE PREENCHIMENTO!");
        }

        if(memoriaRamService.existsByModeloAndFabricanteAndIsAtivo(memoriaRamDto.getModelo(),
                memoriaRamDto.getFabricante())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("MODELO DESTA FABRICANTE JA EXISTE!");
        }

        MemoriaRamModel memoriaRamModel = memoriaRamDto.parseToMemoriaRamModel();
        return ResponseEntity.status(HttpStatus.CREATED).body(memoriaRamService.saveProcessador(memoriaRamModel));
    }

    @GetMapping("/todos")
    public ResponseEntity<Object> buscaTodasMemorias(){
        return !memoriaRamService.findAllByIsAtivo().isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(memoriaRamService.findAllByIsAtivo()) :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhum processador em nosso sistema!");
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<Object> buscaPorModelo(@PathVariable String modelo) {
        return !memoriaRamService.findAllByModeloAndIsAtivo(modelo).isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(memoriaRamService.findAllByModeloAndIsAtivo(modelo)) :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhum processador em nosso sistema!");
    }

    @GetMapping("/fabricante/{fabricante}")
    public ResponseEntity<Object> buscaPorFabricante(@PathVariable String fabricante){
        return !memoriaRamService.findAllByFabricanteAndIsAtivo(fabricante).isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(memoriaRamService.findAllByFabricanteAndIsAtivo(fabricante)):
                ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhum processador em nosso sistema!");
    }

    @GetMapping("/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> buscaPorModelEFabricante(@PathVariable String modelo, @PathVariable String fabricante){
        return !memoriaRamService.findByModeloAndFabricanteAndIsAtivo(modelo,fabricante).isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(memoriaRamService.findByModeloAndFabricanteAndIsAtivo(modelo, fabricante)):
                ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhum processador em nosso sistema!");
    }

    @PutMapping("/atualiza/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> atualizaProcessador(@PathVariable String modelo, @PathVariable String fabricante,
                                                      @RequestBody ProcessadorDto processadorDto) {

        if(!memoriaRamService.existsByModeloAndFabricanteAndIsAtivo(modelo, fabricante)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Processador não consta em nossa base de dados");
        }

        MemoriaRamModel memoriaRamModel = memoriaRamService.findByModeloAndFabricanteAndIsAtivo(modelo, fabricante).get();

        memoriaRamModel.setModelo(processadorDto.getModelo());
        memoriaRamModel.setDescricao(processadorDto.getDescricao());
        memoriaRamModel.setSoquete(processadorDto.getSoquete());
        memoriaRamModel.setGeracao(processadorDto.getGeracao());
        memoriaRamModel.setFabricante(processadorDto.getFabricante());
        try{
            memoriaRamModel.setEstoqueAtual(Integer.parseInt(processadorDto.getEstoqueAtual()));
            memoriaRamModel.setEstoqueMaximo(Integer.parseInt(processadorDto.getEstoqueMaximo()));
        } catch(Exception e) {
            memoriaRamModel.setEstoqueAtual(-1);
            memoriaRamModel.setEstoqueMaximo(-1);
        }
        memoriaRamModel.setFrequencia(processadorDto.getFrequencia());

        return ResponseEntity.status(HttpStatus.OK).body(memoriaRamService.saveProcessador(memoriaRamModel));
    }

    @DeleteMapping("/desativa/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> deativaProcessador(@PathVariable String modelo, @PathVariable String fabricante){

        if(!memoriaRamService.existsByModeloAndFabricanteAndIsAtivo(modelo, fabricante)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Processador não consta em nossa base de dados");
        }

        MemoriaRamModel memoriaRamModel = memoriaRamService.findByModeloAndFabricanteAndIsAtivo(modelo, fabricante).get();

        memoriaRamModel.setIsAtivo("0");

        memoriaRamService.saveProcessador(memoriaRamModel);

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Processador desativado de nosso sistema");
    }

    @DeleteMapping("/delete/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> deletaProcessador(@PathVariable String modelo, @PathVariable String fabricante){

        if(!memoriaRamService.existsByModeloAndFabricanteAndIsAtivo(modelo, fabricante)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Processador não consta em nossa base de dados");
        }

        MemoriaRamModel memoriaRamModel = memoriaRamService.findByModeloAndFabricante(modelo, fabricante).get();
        memoriaRamService.deleteProcessador(memoriaRamModel);

        return ResponseEntity.status(HttpStatus.OK).body("Processador deletado de nossa base de dados");
    }

}
