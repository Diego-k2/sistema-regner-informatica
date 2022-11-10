package br.com.regnerinformatica.CRUDinicial.model.repository;

import br.com.regnerinformatica.CRUDinicial.model.entity.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, UUID> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Optional<FuncionarioModel> findByEmailAndIsAtivo(String email, String isAtivo);

    Optional<FuncionarioModel> findByCpfAndIsAtivo(String cpf, String isAtivo);

    List<FuncionarioModel> findAllByIsAtivo(String isAtivo);

    Optional<FuncionarioModel> findByCpf(String cpf);

}
