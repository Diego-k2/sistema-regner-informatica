package br.com.regnerinformatica.CRUDinicial.controller;

import br.com.regnerinformatica.CRUDinicial.model.dto.FuncionarioDto;
import br.com.regnerinformatica.CRUDinicial.model.entity.FuncionarioModel;
import br.com.regnerinformatica.CRUDinicial.model.service.FuncionarioService;
import br.com.regnerinformatica.CRUDinicial.util.Verificador;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    final Verificador verificador;
    final FuncionarioService funcionarioService;
    public FuncionarioController(Verificador verificador, FuncionarioService funcionarioService) {
        this.verificador = verificador;
        this.funcionarioService = funcionarioService;
    }


    @PostMapping
    public ResponseEntity<Object> salvaFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto, BindingResult bindingResult) throws ParseException {

        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getAllErrors());
        }

        if(!verificador.verificaCpf(funcionarioDto.getCpf()) || funcionarioService.existsByCpf(funcionarioDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF inválido ou em uso");
        }

        if(funcionarioService.existsByEmail(funcionarioDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ja cadastrado");
        }

        FuncionarioModel funcionarioModel = funcionarioDto.parseToFuncionarioModel();


        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.save(funcionarioModel));
    }




}
