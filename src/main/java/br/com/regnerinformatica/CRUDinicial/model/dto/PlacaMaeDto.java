package br.com.regnerinformatica.CRUDinicial.model.dto;

import br.com.regnerinformatica.CRUDinicial.model.entity.PlacaMaeModel;

import java.util.Locale;


public class PlacaMaeDto extends PecasDtoSuperClass {


    public PlacaMaeModel parseToPlacaMae() {
        PlacaMaeModel placaMaeModel = new PlacaMaeModel();
        placaMaeModel.setModelo(super.getModelo().toUpperCase());
        placaMaeModel.setDescricao(super.getDescricao());
        placaMaeModel.setSoquete(super.getSoquete().toUpperCase());
        placaMaeModel.setGeracao(super.getGeracao());
        placaMaeModel.setFabricante(super.getFabricante().toUpperCase());
        placaMaeModel.setEstoqueAtual(Integer.parseInt(super.getEstoqueAtual()));
        placaMaeModel.setEstoqueMaximo(Integer.parseInt(super.getEstoqueMaximo()));
        placaMaeModel.setIsAtivo("1");
        return placaMaeModel;
    }

}
