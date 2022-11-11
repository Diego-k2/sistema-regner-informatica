package br.com.regnerinformatica.CRUDinicial.model.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PecaSuperClass {

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String soquete;

    @Column(nullable = false)
    private String geracao;

    @Column(nullable = false)
    private String fabricante;

    @Column(nullable = false)
    private int estoqueAtual;

    @Column(nullable = false)
    private int estoqueMaximo;

    @Column(nullable = false)
    private String isAtivo;


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

    public String getGeraca() {
        return geracao;
    }

    public void setGeracao(String geraca) {
        this.geracao = geraca;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public int getEstoqueMaximo() {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(int estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }

    public String getIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(String isAtivo) {
        this.isAtivo = isAtivo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
}
