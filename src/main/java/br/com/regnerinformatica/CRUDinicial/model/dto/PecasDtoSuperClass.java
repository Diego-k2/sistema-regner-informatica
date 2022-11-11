package br.com.regnerinformatica.CRUDinicial.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PecasDtoSuperClass {
    @NotBlank(message = "MODELO NÃO PODE SER NULO")
    private String modelo;

    @NotBlank(message = "DESCRIÇÃO NÃO PODE SER NULO")
    private String descricao;

    @NotBlank(message = "SOQUETE NÃO PODE SER NULO")
    private String soquete;

    @NotBlank(message = "GERAÇÃO NÃO PODE SER NULO")
    private String geracao;

    @NotBlank(message = "FABRICANTE NÃO PODE SER NULO")
    private String fabricante;

    @NotNull(message = "ESTOQUE ATUAL NÃO PODE SER NULO")
    private String estoqueAtual;

    @NotNull(message = "ESTOQUE MÁXIMO NÃO PODE SER NULO")
    private String estoqueMaximo;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSoquete() {
        return soquete;
    }

    public void setSoquete(String soquete) {
        this.soquete = soquete;
    }

    public String getGeracao() {
        return geracao;
    }

    public void setGeracao(String geracao) {
        this.geracao = geracao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(String estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public String getEstoqueMaximo() {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(String estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }
}
