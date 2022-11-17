package br.com.regnerinformatica.CRUDinicial.model.dto;

import br.com.regnerinformatica.CRUDinicial.model.entity.ProcessadorModel;

import javax.validation.constraints.NotBlank;

public class ProcessadorDto extends PecasDtoSuperClass{

    @NotBlank(message = "FREQUENCIA NÃO PODE SER NULO")
    private String frequencia;

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public ProcessadorModel parseToProcessadorModel(){
        ProcessadorModel processadorModel = new ProcessadorModel();

        processadorModel.setModelo(super.getModelo());
        processadorModel.setDescricao(super.getDescricao());
        processadorModel.setSoquete(super.getSoquete());
        processadorModel.setGeracao(super.getGeracao());
        processadorModel.setFabricante(super.getFabricante());
        processadorModel.setFrequencia(this.frequencia);

        try {
            processadorModel.setEstoqueAtual(Integer.parseInt(super.getEstoqueAtual()));
            processadorModel.setEstoqueMaximo(Integer.parseInt(super.getEstoqueMaximo()));
        } catch (Exception e) {
            processadorModel.setEstoqueAtual(-1);
            processadorModel.setEstoqueMaximo(-1);
        }

        processadorModel.setIsAtivo("1");

        return processadorModel;
    }

}
