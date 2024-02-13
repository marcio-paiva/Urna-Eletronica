package entities;

public class Cargo {
    
    private String nomeCargo;
    private int votosBranco;
    private Candidato[] candidatos;

    public Cargo(String nomeCargo){
        setNomeCargo(nomeCargo);
        this.votosBranco = 0;
        System.out.println("Cargo criado com sucesso.\n");
    }

    public void setNomeCargo(String nomeCargo){
        this.nomeCargo = nomeCargo;
    }
    public String getNomeCargo(){
        return this.nomeCargo;
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

    public void imprimeCandidatos(){
        for(int i=0; i<candidatos.length; i++){
            System.out.print(candidatos[i].getNome() + " | " + candidatos[i].getNumero() + " - Votos: " + candidatos[i].getVotos() + "\n");
            
        }
    }
}
