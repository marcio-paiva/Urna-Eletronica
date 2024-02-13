package entities;
import java.util.*;

public class Validacoes {

    private boolean cargosCriados = false; //validacao
    private boolean candidatosCriados = false; //validacao
    private boolean votoValido = false; //validacao

    public Validacoes(){}

    public void setCandidatosCriados(boolean candidatosCriados){
        this.candidatosCriados = candidatosCriados;
    }
    public boolean getCandidatosCriados(){
        return this.candidatosCriados;
    }

    public void setCargosCriados(boolean cargosCriados){
        this.cargosCriados = cargosCriados;
    }
    //método set não será criado para proteger a integridade do código
    public boolean getCargosCriados(){
        return this.cargosCriados;
    }

    public void setVotoValido(boolean votoValido){
        this.votoValido = votoValido;
    }
    public boolean getVotoValido(){
        return this.votoValido;
    }

    public final int QTDCARGOS = 2;
    public final int QTDCANDIDATOS = 2;
    Scanner sc = new Scanner(System.in);
    Cargo cargos[] = new Cargo[QTDCARGOS];
    Candidato candidatos[] = new Candidato[QTDCANDIDATOS];
    


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
                votoValido = true;
            }else{
                System.out.println("ERRO - CARGO INVALIDO.");
                votoValido = false;
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

    public void voto(int numero){
        for(int i=0; i<QTDCARGOS; i++){
            if(numero == candidatos[i].getNumero()){
                candidatos[i].somaVoto();
            }else{
                System.out.println("ERRO - CANDIDATO INEXISTENTE .");
            }
        } 
    }

    public void imprimeCandidatos(){
        for(int i=0; i<QTDCARGOS; i++){
            System.out.println(cargos[i].getNomeCargo());
            Candidato[] nomeCandidatosPorCargo = cargos[i].getCandidados();
            for(int j=0; j<nomeCandidatosPorCargo.length; j++){
                System.out.print(nomeCandidatosPorCargo[j]);
            }
        }
    }
}
