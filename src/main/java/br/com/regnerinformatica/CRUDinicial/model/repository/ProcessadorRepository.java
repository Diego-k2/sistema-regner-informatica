package br.com.regnerinformatica.CRUDinicial.model.repository;

import br.com.regnerinformatica.CRUDinicial.model.entity.ProcessadorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProcessadorRepository extends JpaRepository<ProcessadorModel, UUID> {
    boolean existsByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante, String isAtivo);

    List<ProcessadorModel> findAllByIsAtivo(String isAtivo);

    List<ProcessadorModel> findAllByModeloAndIsAtivo(String modelo, String isAtivo);

    List<ProcessadorModel> findAllByFabricanteAndIsAtivo(String fabricante, String isAtivo);

    Optional<ProcessadorModel> findByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante, String isAtivo);

}
