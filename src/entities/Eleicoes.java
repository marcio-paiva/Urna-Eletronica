package entities;
import java.util.Scanner;

public class Eleicoes {

    private boolean cargosCriados = false; //validacao
    private boolean candidatosCriados = false; //validacao
    private boolean votoValido = false; //validacao

    public Eleicoes(){}

    //método set não será criado para proteger a integridade do código
    public boolean getCandidatosCriados(){
        return this.candidatosCriados;
    }

    //método set não será criado para proteger a integridade do código
    public boolean getVotoValido(){
        return this.votoValido;
    }

    public final int QTDCARGOS = 3;
    public final int QTDCANDIDATOS = 5;
    Scanner sc = new Scanner(System.in);
    Cargo cargos[] = new Cargo[QTDCARGOS];
    Candidato candidatos[] = new Candidato[QTDCANDIDATOS];
    

    public void adicionarCargo(){
        for(int i=0; i<QTDCARGOS; i++){
            System.out.println("Informe o nome do cargo " + (i+1) +": ");
            cargos[i] = new Cargo(sc.nextLine());
        }
        cargosCriados = true;  
    }

    public void adicionarCandidato(){
        if(cargosCriados){ //validando se os cargos foram criados anteriormente
            System.out.println("\nPreencha as informacoes dos candidatos ao cargo de " + cargos[0].getNomeCargo() + ".");
            for(int i=0; i<QTDCANDIDATOS; i++){
                candidatos[i] = new Candidato(registraNomeCandidato(i), registraNumeroCandidato(i), cargos[0]);   
            }
            System.out.println("\nPreencha as informacoes dos candidatos ao cargo de " + cargos[1].getNomeCargo() + ".");
            for(int i=0; i<QTDCANDIDATOS; i++){
                candidatos[i] = new Candidato(registraNomeCandidato(i), registraNumeroCandidato(i), cargos[1]);
            }
            System.out.println("\nPreencha as informacoes dos candidatos ao cargo de " + cargos[2].getNomeCargo() + ".");
            for(int i=0; i<QTDCANDIDATOS; i++){
                candidatos[i] = new Candidato(registraNomeCandidato(i), registraNumeroCandidato(i), cargos[2]);
            }
            candidatosCriados = true; //validando a criacao dos candidatos
        }else{
            System.out.println("E necessario criar os cargos antes de registrar os candidatos.");
        }
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
     
    private String registraNomeCandidato(int i){
        System.out.println("Informe o nome do candidato " + (i+1) + ": ");
        return sc.nextLine();
    }

    public void buscaNomeCandidato(int numero){
        for(int i=0; i<QTDCANDIDATOS; i++){
            if(candidatos[i].getNumero() == numero){
                System.out.println(candidatos[i].getNome());
                votoValido = true;
            }else{
                System.out.println("Esse numero nao corresponde a nenhum candidato.");
                votoValido = false;
            }
        }
    }

    public String buscaNomeCargo(int i){
        return cargos[i].getNomeCargo();
    }

    public void setVotosBranco(String nomeCargo){
        for(int i=0; i<QTDCARGOS; i++){
            if(nomeCargo.equals(cargos[i].getNomeCargo())){
                cargos[i].setVotosBranco();
            }else{
                System.out.println("ERRO - CARGO INVALIDO.");
            }
        }   
    }
    // public int getVotosBranco(String nomeCargo){
    //     for(int i=0; i<QTDCARGOS; i++){
    //         if(nomeCargo.equals(cargos[i].getNomeCargo())){
    //             return cargos[i].getVotosBranco();
    //         }else{
    //             System.out.println("ERRO - CARGO INVALIDO.");
    //             return 1;
    //         }
    //     }
    // }

}
