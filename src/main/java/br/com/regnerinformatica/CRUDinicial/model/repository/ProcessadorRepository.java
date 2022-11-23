package br.com.regnerinformatica.CRUDinicial.model.repository;

import br.com.regnerinformatica.CRUDinicial.model.entity.ProcessadorModel;
import org.apache.tomcat.jni.Proc;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProcessadorRepository extends JpaRepository<ProcessadorModel, UUID> {
    boolean existsByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante, String isAtivo);

    @Cacheable("findAllProcessadorIsAtivo")
    List<ProcessadorModel> findAllByIsAtivo(String isAtivo);

    @Cacheable("findAllProcessadorModeloIsAtivo")
    List<ProcessadorModel> findAllByModeloAndIsAtivo(String modelo, String isAtivo);

    @Cacheable("findAllProcessadorFabricanteIsAtivo")
    List<ProcessadorModel> findAllByFabricanteAndIsAtivo(String fabricante, String isAtivo);

    Optional<ProcessadorModel> findByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante, String isAtivo);

    Optional<ProcessadorModel> findByModeloAndFabricante(String modelo, String fabricante);

}
