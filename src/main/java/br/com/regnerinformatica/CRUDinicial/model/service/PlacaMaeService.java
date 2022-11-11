package br.com.regnerinformatica.CRUDinicial.model.service;

import br.com.regnerinformatica.CRUDinicial.model.entity.FuncionarioModel;
import br.com.regnerinformatica.CRUDinicial.model.entity.PlacaMaeModel;
import br.com.regnerinformatica.CRUDinicial.model.repository.PlacaMaeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlacaMaeService {

    final PlacaMaeRepository placaMaeRepository;
    public PlacaMaeService (PlacaMaeRepository placaMaeRepository) {
        this.placaMaeRepository = placaMaeRepository;
    }

    @Transactional
    public PlacaMaeModel salvaPlacaMae(PlacaMaeModel placaMaeModel) {
        return placaMaeRepository.save(placaMaeModel);
    }

    @Transactional
    public boolean existsByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante){
        return placaMaeRepository.existsByModeloAndFabricanteAndIsAtivo(modelo, fabricante, "1");
    }

    @Transactional
    public boolean existsByModeloAndFabricante(String modelo, String fabricante){
        return placaMaeRepository.existsByModeloAndFabricante(modelo, fabricante);
    }


    @Transactional
    public List<PlacaMaeModel> findAllByIsAtivo(){
        return placaMaeRepository.findAllByIsAtivo("1");
    }

    @Transactional
    public Optional<PlacaMaeModel> findByModeloAndFabricante(String modelo, String fabricante){
        return placaMaeRepository.findByModeloAndFabricanteAndIsAtivo(modelo, fabricante, "1");
    }

    @Transactional
    public List<PlacaMaeModel> findAllByFabricante(String fabricante){
        return placaMaeRepository.findAllByFabricanteAndIsAtivo(fabricante, "1");
    }

    @Transactional
    public List<PlacaMaeModel> findAllByModelo(String modelo){
        return placaMaeRepository.findAllByModeloAndIsAtivo(modelo, "1");
    }

    @Transactional
    public void deletaPlaca(PlacaMaeModel placaMaeModel){
         placaMaeRepository.delete(placaMaeModel);
    }

    @Transactional
    public Optional<PlacaMaeModel> findByFabricanteAndModelo(String fabricante, String modelo){
        return placaMaeRepository.findByModeloAndFabricante(modelo, fabricante);
    }



}
