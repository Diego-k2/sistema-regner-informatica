package br.com.regnerinformatica.CRUDinicial.controller;

import br.com.regnerinformatica.CRUDinicial.model.dto.PlacaMaeDto;
import br.com.regnerinformatica.CRUDinicial.model.entity.PlacaMaeModel;
import br.com.regnerinformatica.CRUDinicial.model.service.PlacaMaeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pecas/placamae")
public class PlacaMaeController {

    final PlacaMaeService placaMaeService;

    public PlacaMaeController(PlacaMaeService placaMaeService) {
        this.placaMaeService = placaMaeService;
    }


    @PostMapping
    public ResponseEntity<Object> salvaPlacaMae(@RequestBody @Valid PlacaMaeDto placaMaeDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("TEMOS ALGUM ERRO DE PREENCHIMENTO!");
        }

        if(placaMaeService.existsByModeloAndFabricanteAndIsAtivo(placaMaeDto.getModelo(), placaMaeDto.getFabricante())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("MODELO DESTA FABRICANTE JA EXISTE!");
        }

            PlacaMaeModel placaMaeModel = placaMaeDto.parseToPlacaMae();
            return ResponseEntity.status(HttpStatus.CREATED).body(placaMaeService.salvaPlacaMae(placaMaeModel));
    }

    @GetMapping("/todas")
    public ResponseEntity<Object> todasPlacas(){
        return !placaMaeService.findAllByIsAtivo().isEmpty()?
                ResponseEntity.status(HttpStatus.OK).body(placaMaeService.findAllByIsAtivo()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe nenhuma placa em nosso sistema!");
    }

    @GetMapping("/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> buscaModeloFabricante(@PathVariable String modelo, @PathVariable String fabricante){
        return !placaMaeService.findByModeloAndFabricante(modelo, fabricante).isEmpty()?
                ResponseEntity.status(HttpStatus.OK).body(placaMaeService.findAllByIsAtivo()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe nenhuma placa em nosso sistema!");
    }

    @GetMapping("/fabricante/{fabricante}")
    public ResponseEntity<Object> buscaPorFabricante(@PathVariable String fabricante){
        return !placaMaeService.findAllByFabricante(fabricante.toUpperCase()).isEmpty()?
                ResponseEntity.status(HttpStatus.OK).body(placaMaeService.findAllByFabricante(fabricante)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe nenhuma placa em nosso sistema!");
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<Object> buscaPorModelo(@PathVariable String modelo){
        return !placaMaeService.findAllByModelo(modelo.toUpperCase()).isEmpty()?
                ResponseEntity.status(HttpStatus.OK).body(placaMaeService.findAllByModelo(modelo)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe nenhuma placa em nosso sistema!");
    }

    @PutMapping("/atualiza/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> atualizaPlacaMae(@PathVariable String modelo, @PathVariable String fabricante,
                                                   @RequestBody @Valid PlacaMaeDto placaMaeDto){
        if(!placaMaeService.existsByModeloAndFabricanteAndIsAtivo(modelo, fabricante)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Placa mãe não existe em nossa base de dados");
        }

        PlacaMaeModel placaMaeModel = placaMaeService.findByModeloAndFabricante(modelo, fabricante).get();
        placaMaeModel.setModelo(placaMaeDto.getModelo());
        placaMaeModel.setDescricao(placaMaeDto.getDescricao());
        placaMaeModel.setSoquete(placaMaeDto.getSoquete());
        placaMaeModel.setGeracao(placaMaeDto.getGeracao());
        placaMaeModel.setEstoqueAtual(Integer.parseInt(placaMaeDto.getEstoqueAtual()));
        placaMaeModel.setEstoqueMaximo(Integer.parseInt(placaMaeDto.getEstoqueMaximo()));

        return ResponseEntity.status(HttpStatus.OK).body(placaMaeService.salvaPlacaMae(placaMaeModel));
    }



    @DeleteMapping("/desativa/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> desativaPlacaMae(@PathVariable String modelo, @PathVariable String fabricante){


        if(!placaMaeService.existsByModeloAndFabricanteAndIsAtivo(modelo.toUpperCase(),fabricante.toUpperCase())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Placa mãe não consta em nossa base de dados");
        }

        PlacaMaeModel placaMaeModel = placaMaeService.findByModeloAndFabricante(modelo, fabricante).get();
        placaMaeModel.setIsAtivo("0");
        placaMaeService.salvaPlacaMae(placaMaeModel);

        return ResponseEntity.status(HttpStatus.OK).body("Esta placa foi desativada de nosso sistema");

    }

    @DeleteMapping("/deleta/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> deletaPlacaMae(@PathVariable String modelo, @PathVariable String fabricante) {

        if(!placaMaeService.existsByModeloAndFabricante(modelo.toUpperCase(),fabricante.toUpperCase())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Placa mãe não consta em nossa base de dados");
        }

        PlacaMaeModel placaMaeModel = placaMaeService.findByFabricanteAndModelo(fabricante, modelo).get();

        placaMaeService.deletaPlaca(placaMaeModel);
        return ResponseEntity.status(HttpStatus.OK).body("Esta placa foi excluida de nosso sistema");
    }

}
