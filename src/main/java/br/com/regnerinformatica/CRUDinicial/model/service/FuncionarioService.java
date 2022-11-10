package br.com.regnerinformatica.CRUDinicial.model.service;

import br.com.regnerinformatica.CRUDinicial.model.entity.FuncionarioModel;
import br.com.regnerinformatica.CRUDinicial.model.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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


}
