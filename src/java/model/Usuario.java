package model;

public class Usuario {

    private Integer cod;
    private String nome;
    private String matricula;
    private String senha;
    private Boolean funcionario;
    private Integer maxEmprestimo;
    private Boolean ativo;

    public Usuario() {
        this.maxEmprestimo = 5;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Boolean funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getMaxEmprestimo() {
        return maxEmprestimo;
    }

    public void setMaxEmprestimo(Integer maxEmprestimo) {
        this.maxEmprestimo = maxEmprestimo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    
}
