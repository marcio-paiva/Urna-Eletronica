package entities;

public class Validacoes {

    private boolean cargosCriados = false; //validacao
    private boolean candidatosCriados = false; //validacao
    private boolean votoValido = false; //validacao
    private boolean numeroValido = true;

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
}