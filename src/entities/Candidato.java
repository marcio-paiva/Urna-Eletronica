package entities;
import java.util.Scanner;

public class Candidato {

    private String nome;
    private int numero; //número para votar
    private Cargo cargo;
    private int votos=0;

    public Candidato(String nome, int numero, Cargo cargo){
        setNome(nome);
        setNumero(numero);
        setCargo(cargo);
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

    public void setCargo(Cargo cargo){
        this.cargo = cargo;
    }
    public Cargo getCargo(){
        return this.cargo;
    }

    //método set não será criado para proteger a integridade do código
    public int getVotos(){
        return this.votos;
    }
    public void somaVoto(){
        this.votos++;
    }

    Scanner sc = new Scanner(System.in);
    private String registraNomeCandidato(int i){
        System.out.println("Informe o nome do candidato " + (i+1) + ": ");
        return sc.nextLine();
    }
    public int registraNumeroCandidato(int i){
        int numeroCandidato;
        do{
            System.out.println("Informe o numero do candidato " + (i+1) + " (O numero deve estar entre 1 e 998): ");
            numeroCandidato = sc.nextInt();
            sc.nextLine(); //limpando o buffer do teclado
        }while(numeroCandidato <= 0 || numeroCandidato >= 999);
        return numeroCandidato;
    }
}
