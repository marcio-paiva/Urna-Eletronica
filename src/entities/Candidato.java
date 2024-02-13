package entities;

public class Candidato {

    private String nome;
    private int numero; //número para votar
    private int votos;

    public Candidato(String nome, int numero){
        setNome(nome);
        setNumero(numero);
        this.votos = 0;
        System.out.println("Candidato registrado com sucesso.\n");
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }
    public int getNumero(){
        return this.numero;
    }

    //método set não será criado para proteger a integridade do código
    public int getVotos(){
        return this.votos;
    }
    public void somaVoto(){
        this.votos++; //soma apenas 1 voto a cada chamada
    }

}
