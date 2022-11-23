package br.com.regnerinformatica.CRUDinicial.model.repository;

import br.com.regnerinformatica.CRUDinicial.model.entity.FuncionarioModel;
import br.com.regnerinformatica.CRUDinicial.model.entity.PlacaMaeModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlacaMaeRepository extends JpaRepository<PlacaMaeModel, UUID> {

    boolean existsByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante, String isAtivo);

    boolean existsByModeloAndFabricante(String modelo, String fabricante);

    @Cacheable("findAllPlacaMaeIsAtivo")
    List<PlacaMaeModel> findAllByIsAtivo(String isAtivo);

    @Cacheable("findAllPlacaMaeFabricanteIsAtivo")
    List<PlacaMaeModel> findAllByFabricanteAndIsAtivo(String fabricante, String isAtivo);

    @Cacheable("findAllPlacaMaeModeloIsAtivo")
    List<PlacaMaeModel> findAllByModeloAndIsAtivo(String modelo, String isAtivo);

    Optional<PlacaMaeModel> findByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante, String isAtivo);

    Optional<PlacaMaeModel> findByModeloAndFabricante(String modelo, String fabricante);
}
