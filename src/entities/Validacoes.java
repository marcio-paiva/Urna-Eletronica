package entities;

public class Validacoes {

    private boolean cargosCriados = false; 
    private boolean candidatosCriados = false; 
    private boolean votoValido = false; 
    private boolean numeroValido = true;
    private boolean finalizarVotacao = false;
    private boolean entradaValida = false;
    private boolean opcaoValida = false;
    private boolean confirmacaoValida = false;

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

    public void setEntradaValida(boolean entradaValida) {
        this.entradaValida = entradaValida;
    }
    public boolean getEntradaValida() {
        return entradaValida;
    } 

    public void setOpcaoValida(boolean opcaoValida) {
        this.opcaoValida = opcaoValida;
    }
    public boolean getOpcaoValida() {
        return opcaoValida;
    } 

    public void setConfirmacaoValida(boolean confirmacaoValida) {
        this.confirmacaoValida = confirmacaoValida;
    }
    public boolean getConfirmacaoValida() {
        return confirmacaoValida;
    } 
}