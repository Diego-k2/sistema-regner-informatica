package br.com.regnerinformatica.CRUDinicial.model.dto;

import br.com.regnerinformatica.CRUDinicial.model.entity.PlacaVideoModel;

public class PlacaVideoDto extends PecasDtoSuperClass{


    public PlacaVideoModel parseToPlacaVideo(){
        PlacaVideoModel placaVideoModel = new PlacaVideoModel();

        placaVideoModel.setModelo(super.getModelo());
        placaVideoModel.setDescricao(super.getDescricao());
        placaVideoModel.setSoquete(super.getSoquete());
        placaVideoModel.setGeracao(super.getGeracao());
        placaVideoModel.setFabricante(super.getFabricante());

        try {
            placaVideoModel.setEstoqueAtual(Integer.parseInt(super.getEstoqueAtual()));
            placaVideoModel.setEstoqueMaximo(Integer.parseInt(super.getEstoqueMaximo()));
        } catch (Exception e) {
            placaVideoModel.setEstoqueAtual(-1);
            placaVideoModel.setEstoqueMaximo(-1);
        }
        placaVideoModel.setIsAtivo("1");
        return placaVideoModel;
    }


}
