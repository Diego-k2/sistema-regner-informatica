package br.com.regnerinformatica.CRUDinicial.model.repository;

import br.com.regnerinformatica.CRUDinicial.model.entity.MemoriaRamModel;
import br.com.regnerinformatica.CRUDinicial.model.entity.MemoriaRamModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemoriaRamRepository extends JpaRepository<MemoriaRamModel, UUID> {
    boolean existsByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante, String isAtivo);

    @Cacheable("findAllProcessadorIsAtivo")
    List<MemoriaRamModel> findAllByIsAtivo(String isAtivo);

    @Cacheable("findAllMemoriaRamModeloIsAtivo")
    List<MemoriaRamModel> findAllByModeloAndIsAtivo(String modelo, String isAtivo);

    @Cacheable("findAllProcessadorFabricanteIsAtivo")
    List<MemoriaRamModel> findAllByFabricanteAndIsAtivo(String fabricante, String isAtivo);

    Optional<MemoriaRamModel> findByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante, String isAtivo);

    Optional<MemoriaRamModel> findByModeloAndFabricante(String modelo, String fabricante);
}
