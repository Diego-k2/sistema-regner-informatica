package br.com.regnerinformatica.CRUDinicial.model.dto;

import br.com.regnerinformatica.CRUDinicial.model.entity.MemoriaRamModel;

import javax.validation.constraints.NotBlank;

public class MemoriaRamDto extends PecasDtoSuperClass{

    @NotBlank(message = "FREQUENCIA NÃO PODE SER NULO")
    private String frequencia;

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public MemoriaRamModel parseToMemoriaRamModel(){
        MemoriaRamModel memoriaRamModel = new MemoriaRamModel();

        memoriaRamModel.setModelo(super.getModelo());
        memoriaRamModel.setDescricao(super.getDescricao());
        memoriaRamModel.setSoquete(super.getSoquete());
        memoriaRamModel.setGeracao(super.getGeracao());
        memoriaRamModel.setFabricante(super.getFabricante());
        memoriaRamModel.setFrequencia(this.frequencia);

        try {
            memoriaRamModel.setEstoqueAtual(Integer.parseInt(super.getEstoqueAtual()));
            memoriaRamModel.setEstoqueMaximo(Integer.parseInt(super.getEstoqueMaximo()));
        } catch (Exception e) {
            memoriaRamModel.setEstoqueAtual(-1);
            memoriaRamModel.setEstoqueMaximo(-1);
        }

        memoriaRamModel.setIsAtivo("1");

        return memoriaRamModel;
    }


}
