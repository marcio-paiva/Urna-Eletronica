package entities;

public class Cargo {
    
    private String nomeCargo;
    private int votosBranco = 0;

    public Cargo(String nomeCargo){
        setNomeCargo(nomeCargo);
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
}
