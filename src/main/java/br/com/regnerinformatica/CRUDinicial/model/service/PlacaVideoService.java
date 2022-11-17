package br.com.regnerinformatica.CRUDinicial.model.service;

import br.com.regnerinformatica.CRUDinicial.model.entity.PlacaVideoModel;
import br.com.regnerinformatica.CRUDinicial.model.repository.PlacaVideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<PlacaVideoModel> findAllByIsAtivo(){
        return placaVideoRepository.findAllByIsAtivo("1");
    }

    @Transactional
    public List<PlacaVideoModel> findAllByModeloAndIsAtivo(String modelo){
        return placaVideoRepository.findAllByModeloAndIsAtivo(modelo.toUpperCase(), "1");
    }




}