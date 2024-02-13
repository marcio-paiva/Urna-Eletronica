package entities;

public abstract class Identificacao {
    private String nome;

    public Identificacao(String nome){
        setNome(nome);
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
}
