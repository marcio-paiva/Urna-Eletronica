package entities;

public class Cargo extends Identificacao{
    
    private int votosBranco;
    private int votosNulo;

    private Candidato[] candidatos;

    public Cargo(String nomeCargo){
        super(nomeCargo);
        this.votosBranco = 0;
        System.out.println("Cargo criado com sucesso.\n");
    }

    public void setNomeCargo(String nomeCargo){
        super.setNome(nomeCargo);
    }
    public String getNomeCargo(){
        return super.getNome();
    }

    public void setVotosBranco(){
        this.votosBranco++;
    }
    public int getVotosBranco(){
        return this.votosBranco;
    }

    public void setCandidato(Candidato[] candidato){
        this.candidatos = candidato;
    }
    public Candidato[] getCandidados(){
        return this.candidatos;
    }

    public void setVotosNulo() {
        this.votosNulo++;
    }
    public int getVotosNulo() {
        return votosNulo;
    }

    public void imprimeCandidatos(){
        for(int i=0; i<candidatos.length; i++){
            System.out.print(candidatos[i].getNome() + " | " + candidatos[i].getNumero() + " - Votos: " + candidatos[i].getVotos() + "\n");
            
        }
    }
}
