package br.com.regnerinformatica.CRUDinicial.model.dto;

import br.com.regnerinformatica.CRUDinicial.model.entity.FuncionarioModel;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FuncionarioDto {

    @NotBlank(message = "Nome não pode ser nulo")
    private String nome;

    @NotBlank(message = "Sobrenome não pode ser nulo")
    private String sobrenome;

    @NotBlank(message = "Data de nascimento não pode ser nulo")
    private String dtNascimento;

    @NotBlank(message = "CPF não pode ser nulo")
    private String cpf;

    @NotBlank(message = "RG não pode ser nulo")
    private String rg;

    @NotBlank(message = "Setor não pode ser nulo")
    private String setor;

    @NotBlank(message = "Email não pode ser nulo")
    private String email;

    @NotBlank(message = "Senha não pode ser nulo")
    private String senha;


    public FuncionarioModel parseToFuncionarioModel() throws ParseException {
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        funcionarioModel.setNome(this.nome);
        funcionarioModel.setSobrenome(this.sobrenome);
        funcionarioModel.setDtNascimento(new SimpleDateFormat("yyyy/MM/dd").parse(this.dtNascimento));
        funcionarioModel.setCpf(this.cpf.replaceAll("[^0-9]+", ""));
        funcionarioModel.setRg(this.rg);
        funcionarioModel.setSetor(this.setor);
        funcionarioModel.setIsAtivo("1");
        funcionarioModel.setEmail(this.email);
        funcionarioModel.setSenha(this.senha);
        return funcionarioModel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
