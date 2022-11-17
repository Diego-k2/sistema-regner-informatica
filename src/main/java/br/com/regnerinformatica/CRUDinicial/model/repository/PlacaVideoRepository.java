package br.com.regnerinformatica.CRUDinicial.model.repository;

import br.com.regnerinformatica.CRUDinicial.model.entity.PlacaVideoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlacaVideoRepository extends JpaRepository<PlacaVideoModel, UUID> {

    boolean existsByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante, String isAtivo);

    List<PlacaVideoModel> findAllByIsAtivo(String isAtivo);

    List<PlacaVideoModel> findAllByModeloAndIsAtivo(String modelo, String isAtivo);

}
