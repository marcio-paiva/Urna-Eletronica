import java.util.Scanner;

import entities.*;


public class Urna {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n");
        
        Eleicoes icea = new Eleicoes();

        icea.adicionarCargo();
        icea.adicionarCandidato();


        //VOTACOES

        if(icea.getCandidatosCriados()){ // valida se os candidatos e cargos estao registrados.
            icea.buscaNomeCargo(0);
            System.out.println("\n\n");
            int numero = sc.nextInt();
            icea.buscaNomeCandidato(numero);
            if(icea.getVotoValido() == true){
                System.out.println("Digite a opcao desejada: | BRANCO | CORRIGE | CONFIRMA |");
                if(sc.nextLine() == "BRANCO"){
                    icea.setVotosBranco(icea.buscaNomeCargo(0));
                }else if(sc.nextLine() == "CONFIRMA"){
                    //icea.
                }

            }
            sc.nextLine();
        }
        



        System.out.println("\n\n");
    }
}
