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
    

}
