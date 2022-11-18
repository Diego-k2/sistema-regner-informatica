package br.com.regnerinformatica.CRUDinicial.model.service;

import br.com.regnerinformatica.CRUDinicial.model.entity.ProcessadorModel;
import br.com.regnerinformatica.CRUDinicial.model.repository.ProcessadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessadorService {

    final ProcessadorRepository processadorRepository;
    public ProcessadorService(ProcessadorRepository processadorRepository){
        this.processadorRepository = processadorRepository;
    }

    @Transactional
    public ProcessadorModel saveProcessador(ProcessadorModel processadorModel){
        return processadorRepository.save(processadorModel);
    }

    @Transactional
    public boolean existsByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante){
        return processadorRepository.existsByModeloAndFabricanteAndIsAtivo(modelo.toUpperCase(),fabricante.toUpperCase(), "1");
    }

    @Transactional
    public List<ProcessadorModel> findAllByIsAtivo(){
        return processadorRepository.findAllByIsAtivo("1");
    }

    @Transactional
    public List<ProcessadorModel> findAllByModeloAndIsAtivo(String modelo){
        return processadorRepository.findAllByModeloAndIsAtivo(modelo.toUpperCase(), "1");
    }

    @Transactional
    public List<ProcessadorModel> findAllByFabricanteAndIsAtivo(String fabricante){
        return processadorRepository.findAllByFabricanteAndIsAtivo(fabricante.toUpperCase(), "1");
    }

    @Transactional
    public Optional<ProcessadorModel> findByModeloAndFabricanteAndIsAtivo(String modelo, String fabricante){
        return processadorRepository.findByModeloAndFabricanteAndIsAtivo(modelo.toUpperCase(), fabricante.toUpperCase(), "1");
    }





}
