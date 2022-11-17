package br.com.regnerinformatica.CRUDinicial.model.service;

import br.com.regnerinformatica.CRUDinicial.model.entity.PlacaVideoModel;
import br.com.regnerinformatica.CRUDinicial.model.repository.PlacaVideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlacaVideoService {

    final PlacaVideoRepository placaVideoRepository;
    public PlacaVideoService(PlacaVideoRepository placaVideoRepository) {
        this.placaVideoRepository = placaVideoRepository;
    }

    @Transactional
    public PlacaVideoModel savePlacaVideo(PlacaVideoModel placaVideoModel){
        return placaVideoRepository.save(placaVideoModel);
    }

    @Transactional
    public boolean existsByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante){
        return placaVideoRepository.existsByModeloAndFabricanteAndIsAtivo(modelo.toUpperCase(), fabricante.toUpperCase(), "1");
    }

    @Transactional
    public boolean existsByModeloAndFabricante(String modelo, String fabricante){
        return placaVideoRepository.existsByModeloAndFabricante(modelo.toUpperCase(),fabricante.toUpperCase());
    }

    @Transactional
    public List<PlacaVideoModel> findAllByIsAtivo(){
        return placaVideoRepository.findAllByIsAtivo("1");
    }

    @Transactional
    public List<PlacaVideoModel> findAllByModeloAndIsAtivo(String modelo){
        return placaVideoRepository.findAllByModeloAndIsAtivo(modelo.toUpperCase(), "1");
    }

    @Transactional
    public List<PlacaVideoModel> findAllByFabricanteAndIsAtivo(String modelo){
        return placaVideoRepository.findAllByFabricanteAndIsAtivo(modelo.toUpperCase(), "1");
    }

    @Transactional
    public Optional<PlacaVideoModel> findByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante){
        return placaVideoRepository.findByModeloAndFabricanteAndIsAtivo(modelo.toUpperCase(), fabricante.toUpperCase(), "1");
    }

    @Transactional
    public void deletePlaca(PlacaVideoModel placaVideoModel){
         placaVideoRepository.delete(placaVideoModel);
    }

    @Transactional
    public Optional<PlacaVideoModel> findByModeloAndFabricante(String modelo, String fabricante){
        return placaVideoRepository.findByModeloAndFabricante(modelo.toUpperCase(), fabricante.toUpperCase());
    }

}