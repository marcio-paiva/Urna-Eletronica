package entities;

public class Validacoes {

    private boolean cargosCriados = false; 
    private boolean candidatosCriados = false; 
    private boolean votoValido = false; 
    private boolean numeroValido = true;
    private boolean finalizarVotacao = false;

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
    public boolean getCargosCriados(){
        return this.cargosCriados;
    }

    public void setVotoValido(boolean votoValido){
        this.votoValido = votoValido;
    }
    public boolean getVotoValido(){
        return this.votoValido;
    }

    public void setNumeroValido(boolean numeroValido) {
        this.numeroValido = numeroValido;
    }
    public boolean getNumeroValido() {
        return numeroValido;
    }

    public void setFinalizarVotacao(boolean finalizarVotacao) {
        this.finalizarVotacao = finalizarVotacao;
    }
    public boolean getFinalizarVotacao() {
        return finalizarVotacao;
    }  
}