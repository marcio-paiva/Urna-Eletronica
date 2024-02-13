package entities;

public class Candidato extends Identificacao{

    private int numero; //número para votar
    private int votos;

    public Candidato(String nome, int numero){
        super(nome);
        setNumero(numero);
        this.votos = 0;
        System.out.println("Candidato registrado com sucesso.\n");
    }

    public void setNome(String nome){
        super.setNome(nome);
    }
    public String getNome(){
        return super.getNome();
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
