package controller;

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
import java.text.SimpleDateFormat;

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

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(bindingResult.getAllErrors());
        }

        if (!verificador.verificaCpf(funcionarioDto.getCpf()) || funcionarioService.existsByCpf(funcionarioDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF inválido ou em uso");
        }

        if (funcionarioService.existsByEmail(funcionarioDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ja cadastrado");
        }

        FuncionarioModel funcionarioModel = funcionarioDto.parseToFuncionarioModel();


        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.save(funcionarioModel));
    }

    @GetMapping("/todos")
    public ResponseEntity<Object> todosFuncionario() {

        return !funcionarioService.todosFuncionarios().isEmpty()
                ? ResponseEntity.status(HttpStatus.OK).body(funcionarioService.todosFuncionarios())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não temos usuarios cadastrados");
    }

    @GetMapping("busca/email/{email}")
    public ResponseEntity<Object> funcionarioPorEmail(@PathVariable String email) {
        return !funcionarioService.findByEmail(email).isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findByEmail(email)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não temos usuario cadastrado com esse email!");
    }

    @GetMapping("busca/cpf/{cpf}")
    public ResponseEntity<Object> funcionarioPorCpf(@PathVariable String cpf) {
        return !funcionarioService.findByCpf(cpf).isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findByCpf(cpf)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não temos usuario cadastrado com esse CPF!");
    }

    @PutMapping("/atualiza/cpf/{cpf}")
    public ResponseEntity<Object> atualizaFuncionario(@PathVariable String cpf, @RequestBody @Valid FuncionarioDto funcionarioDto) throws ParseException {

        if(!funcionarioService.existsByCpf(cpf)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não consta em nossa base de dados");
        }

        FuncionarioModel funcionarioModel = funcionarioService.findByCpf(cpf).get();

        funcionarioModel.setNome(funcionarioDto.getNome());
        funcionarioModel.setSobrenome(funcionarioDto.getSobrenome());
        funcionarioModel.setDtNascimento(new SimpleDateFormat("yyyy/MM/dd").parse(funcionarioDto.getDtNascimento()));
        funcionarioModel.setCpf(funcionarioDto.getCpf().replaceAll("[^0-9]+", ""));
        funcionarioModel.setRg(funcionarioDto.getRg());
        funcionarioModel.setSetor(funcionarioDto.getSetor());
        funcionarioModel.setEmail(funcionarioDto.getEmail());
        funcionarioModel.setSenha(funcionarioDto.getSenha());

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.save(funcionarioModel));
    }

    @DeleteMapping("/desativa/cpf/{cpf}")
    public ResponseEntity<Object> desativaConta(@PathVariable String cpf) {
        if(!funcionarioService.existsByCpf(cpf)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não consta em nossa base de dados");
        }

        FuncionarioModel funcionarioModel = funcionarioService.findByCpf(cpf).get();

        funcionarioModel.setIsAtivo("0");
        funcionarioService.save(funcionarioModel);

        return ResponseEntity.status(HttpStatus.OK).body("Conta desativada com sucesso");
    }

    @DeleteMapping("/deleta/cpf/{cpf}")
    public ResponseEntity<Object> deletaConta(@PathVariable String cpf) {
        if(!funcionarioService.existsByCpf(cpf)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não consta em nossa base de dados");
        }
        FuncionarioModel funcionarioModel = funcionarioService.findByCpfAll(cpf).get();

        funcionarioService.deleteFuncionario(funcionarioModel);
        return ResponseEntity.status(HttpStatus.OK).body("Funcionario excluido");
    }

}
