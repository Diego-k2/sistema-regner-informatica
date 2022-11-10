package br.com.regnerinformatica.CRUDinicial.model.service;

import br.com.regnerinformatica.CRUDinicial.model.entity.FuncionarioModel;
import br.com.regnerinformatica.CRUDinicial.model.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class FuncionarioService {

    final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public boolean existsByCpf(String cpf) {
        return funcionarioRepository.existsByCpf(cpf);
    }

    @Transactional
    public boolean existsByEmail(String email) {
        return funcionarioRepository.existsByEmail(email);
    }

    @Transactional
    public FuncionarioModel save(FuncionarioModel funcionarioModel) {
        return funcionarioRepository.save(funcionarioModel);
    }

    @Transactional
    public List<FuncionarioModel> todosFuncionarios() {
        return funcionarioRepository.findAllByIsAtivo("1");
    }

    @Transactional
    public Optional<FuncionarioModel> findByEmail(String email){
        return funcionarioRepository.findByEmailAndIsAtivo(email, "1");
    }

    @Transactional
    public Optional<FuncionarioModel> findByCpf(String cpf){
        return funcionarioRepository.findByCpfAndIsAtivo(cpf, "1");
    }

    @Transactional
    public Optional<FuncionarioModel> findByCpfAll(String cpf){
        return funcionarioRepository.findByCpf(cpf);
    }



    @Transactional
    public void deleteFuncionario(FuncionarioModel funcionarioModel){
        funcionarioRepository.delete(funcionarioModel);
    }


}
