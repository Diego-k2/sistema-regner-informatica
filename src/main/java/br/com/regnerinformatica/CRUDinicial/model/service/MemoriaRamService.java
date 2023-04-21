package br.com.regnerinformatica.CRUDinicial.model.service;

import br.com.regnerinformatica.CRUDinicial.model.entity.MemoriaRamModel;
import br.com.regnerinformatica.CRUDinicial.model.entity.ProcessadorModel;
import br.com.regnerinformatica.CRUDinicial.model.repository.MemoriaRamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MemoriaRamService {

    final MemoriaRamRepository memoriaRamRepository;
    public MemoriaRamService(MemoriaRamRepository memoriaRamRepository){
        this.memoriaRamRepository = memoriaRamRepository;
    }

    @Transactional
    public MemoriaRamModel saveProcessador(MemoriaRamModel memoriaRamModel){
        return memoriaRamRepository.save(memoriaRamModel);
    }

    @Transactional
    public boolean existsByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante){
        return memoriaRamRepository.existsByModeloAndFabricanteAndIsAtivo(modelo.toUpperCase(),fabricante.toUpperCase(), "1");
    }

    @Transactional
    public List<MemoriaRamModel> findAllByIsAtivo(){
        return memoriaRamRepository.findAllByIsAtivo("1");
    }

    @Transactional
    public List<MemoriaRamModel> findAllByModeloAndIsAtivo(String modelo){
        return memoriaRamRepository.findAllByModeloAndIsAtivo(modelo.toUpperCase(), "1");
    }

    @Transactional
    public List<MemoriaRamModel> findAllByFabricanteAndIsAtivo(String fabricante){
        return memoriaRamRepository.findAllByFabricanteAndIsAtivo(fabricante.toUpperCase(), "1");
    }

    @Transactional
    public Optional<MemoriaRamModel> findByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante){
        return memoriaRamRepository.findByModeloAndFabricanteAndIsAtivo(modelo.toUpperCase(), fabricante.toUpperCase(), "1");
    }

    @Transactional
    public void deleteProcessador(MemoriaRamModel memoriaRamModel){
        memoriaRamRepository.delete(memoriaRamModel);
    }

    @Transactional
    public Optional<MemoriaRamModel> findByModeloAndFabricante(String modelo, String fabricante){
        return memoriaRamRepository.findByModeloAndFabricante(modelo, fabricante);
    }



}
