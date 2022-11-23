package br.com.regnerinformatica.CRUDinicial.model.repository;

import br.com.regnerinformatica.CRUDinicial.model.entity.PlacaVideoModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlacaVideoRepository extends JpaRepository<PlacaVideoModel, UUID> {

    boolean existsByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante, String isAtivo);

    boolean existsByModeloAndFabricante(String modelo, String fabricante);

    @Cacheable("findAllPlacaVideoIsAtivo")
    List<PlacaVideoModel> findAllByIsAtivo(String isAtivo);

    @Cacheable("findAllPlacaVideoModeloIsAtivo")
    List<PlacaVideoModel> findAllByModeloAndIsAtivo(String modelo, String isAtivo);

    @Cacheable("findAllPlacaVideoFabricanteIsAtivo")
    List<PlacaVideoModel> findAllByFabricanteAndIsAtivo(String modelo, String isAtivo);

    Optional<PlacaVideoModel> findByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante, String isAtivo);

    Optional<PlacaVideoModel> findByModeloAndFabricante(String modelo, String fabricante);
}
