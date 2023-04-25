package br.com.regnerinformatica.CRUDinicial.controller;

import br.com.regnerinformatica.CRUDinicial.model.dto.PlacaVideoDto;
import br.com.regnerinformatica.CRUDinicial.model.entity.PlacaVideoModel;
import br.com.regnerinformatica.CRUDinicial.model.service.PlacaVideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/pecas/placadevideo")
public class PlacaVideoController {

    final PlacaVideoService placaVideoService;
    public PlacaVideoController(PlacaVideoService placaVideoService) {
        this.placaVideoService = placaVideoService;
    }

    @PostMapping
    public ResponseEntity<Object> salvaPlacaDeVideo(@RequestBody @Valid PlacaVideoDto placaVideoDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("TEMOS ALGUM ERRO DE PREENCHIMENTO!");
        }

        if(placaVideoService.existsByModeloAndFabricanteAndIsAtivo(placaVideoDto.getModelo(), placaVideoDto.getFabricante())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("MODELO DESTA FABRICANTE JA EXISTE!");
        }

        PlacaVideoModel placaVideoModel = placaVideoDto.parseToPlacaVideo();

        return ResponseEntity.status(HttpStatus.CREATED).body(placaVideoService.savePlacaVideo(placaVideoModel));

    }

    @GetMapping("/todos")
    public ResponseEntity<Object> todasPlacas(){
        return !placaVideoService.findAllByIsAtivo().isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(placaVideoService.findAllByIsAtivo()) :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhuma placa em nosso sistema!");
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<Object> placaPorModelo(@PathVariable String modelo) {
        return !placaVideoService.findAllByModeloAndIsAtivo(modelo).isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(placaVideoService.findAllByModeloAndIsAtivo(modelo)) :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhuma placa em nosso sistema!");
    }

    @GetMapping("/fabricante/{fabricante}")
    public ResponseEntity<Object> placaPorFabricante(@PathVariable String fabricante){
        return !placaVideoService.findAllByFabricanteAndIsAtivo(fabricante).isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(placaVideoService.findAllByFabricanteAndIsAtivo(fabricante)):
                ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhuma placa em nosso sistema!");
    }

    @GetMapping("/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> placaPorModeloEFabricante(@PathVariable String modelo, @PathVariable String fabricante){
        return !placaVideoService.findByModeloAndFabricanteAndIsAtivo(modelo, fabricante).isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(placaVideoService.findByModeloAndFabricanteAndIsAtivo(modelo, fabricante)) :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhuma placa em nosso sistema!");
    }

    @PutMapping("/atualiza/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> atualizaPlaca(@PathVariable String modelo, @PathVariable String fabricante,
                                                @RequestBody @Valid PlacaVideoDto placaVideoDto){
        if(!placaVideoService.existsByModeloAndFabricanteAndIsAtivo(modelo, fabricante)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhuma placa em nosso sistema!");
        }

        PlacaVideoModel placaVideoModel = placaVideoService.findByModeloAndFabricanteAndIsAtivo(modelo, fabricante).get();
        placaVideoModel.setModelo(placaVideoDto.getModelo());
        placaVideoModel.setDescricao(placaVideoDto.getDescricao());
        placaVideoModel.setSoquete(placaVideoDto.getSoquete());
        placaVideoModel.setGeracao(placaVideoDto.getGeracao());
        placaVideoModel.setFabricante(placaVideoDto.getFabricante());
        placaVideoModel.setEstoqueAtual(Integer.parseInt(placaVideoDto.getEstoqueAtual()));
        placaVideoModel.setEstoqueMaximo(Integer.parseInt(placaVideoDto.getEstoqueMaximo()));

        return ResponseEntity.status(HttpStatus.OK).body(placaVideoService.savePlacaVideo(placaVideoModel));
    }

    @DeleteMapping("/desativa/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> desativaPlaca(@PathVariable String modelo, @PathVariable String fabricante){

        if(!placaVideoService.existsByModeloAndFabricanteAndIsAtivo(modelo,fabricante)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhuma placa em nosso sistema!");
        }

        PlacaVideoModel placaVideoModel = placaVideoService.findByModeloAndFabricanteAndIsAtivo(modelo, fabricante).get();

        placaVideoModel.setIsAtivo("0");

        placaVideoService.savePlacaVideo(placaVideoModel);

        return ResponseEntity.status(HttpStatus.OK).body("Placa desativada de nosso sistema!");
    }

    @DeleteMapping("/delete/modelo/{modelo}/fabricante/{fabricante}")
    public ResponseEntity<Object> deletePlaca(@PathVariable String modelo, @PathVariable String fabricante){
        if(!placaVideoService.existsByModeloAndFabricante(modelo,fabricante)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe nenhuma placa em nosso sistema!");
        }

        PlacaVideoModel placaVideoModel = placaVideoService.findByModeloAndFabricante(modelo, fabricante).get();

        placaVideoService.deletePlaca(placaVideoModel);

        return ResponseEntity.status(HttpStatus.OK).body("Placa excluida de nosso sistema!");
    }

}
