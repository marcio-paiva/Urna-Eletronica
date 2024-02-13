import java.util.Scanner;

import entities.*;



public class Urna {
    public static void main(String[] args){
        final int QTDCARGOS = 2;
        final int QTDCANDIDATOS = 2; //não pode ser 1
        Scanner sc = new Scanner(System.in);
        Cargo cargos[] = new Cargo[QTDCARGOS];
        Candidato[][] candidatosPorCargo = new Candidato[QTDCARGOS][QTDCANDIDATOS];
        Validacoes validacao = new Validacoes(); 
        System.out.println("\n\n");
        

        //criar cargos
        for(int i=0; i<QTDCARGOS; i++){
            System.out.println("Informe o nome do cargo " + (i+1) +": ");
            cargos[i] = new Cargo(sc.nextLine());
            validacao.setCargosCriados(true);
        }

        //crair candidatos por cargo
        if(validacao.getCargosCriados()){ //validando se os cargos foram criados anteriormente
            for(int j=0; j<QTDCARGOS; j++){
                System.out.println("\nPreencha as informacoes dos candidatos ao cargo de " + cargos[j].getNomeCargo() + ".");
                for(int i=0; i<QTDCANDIDATOS; i++){
                    System.out.println("Informe o nome do candidato " + (i+1) + ": ");
                    String nomeCandidato = sc.nextLine();
                    int numeroCandidato;
                    do{
                        System.out.println("Informe o numero do candidato " + (i+1) + " (O numero deve estar entre 1 e 998): ");
                        numeroCandidato = sc.nextInt();
                        sc.nextLine(); //limpando o buffer do teclado
                    }while(numeroCandidato <= 0 || numeroCandidato >= 999);
                    candidatosPorCargo[j][i] = new Candidato(nomeCandidato, numeroCandidato);
                }
                cargos[j].setCandidato(candidatosPorCargo[j]);
            }
            validacao.setCandidatosCriados(true); //validando a criacao dos candidatos
        }else{
            System.out.println("E necessario criar os cargos antes de registrar os candidatos.");
        }

        //imprime informacoes dos candidatos
        for(int i=0; i<QTDCARGOS; i++){
            System.out.println(cargos[i].getNomeCargo());
            cargos[i].imprimeCandidatos();
        }

        if(validacao.getCandidatosCriados() == true){
            for(int i=0; i<QTDCARGOS; i++){
                System.out.println("\n\n");
                System.out.println(cargos[i].getNomeCargo());
                System.out.println("\n");
                System.out.println("Seu voto: \nPara votar em branco");
                int numero = sc.nextInt();
                sc.nextLine(); //limpando buffer
                int j, k=0; //sera usada fora do loop posteriormente
                for(j=0; j<QTDCANDIDATOS; j++){
                    if(candidatosPorCargo[i][j].getNumero() == numero){
                        System.out.println(candidatosPorCargo[i][j].getNome());
                        k = j;
                        validacao.setVotoValido(true); //valida se o voto foi em um candidato cadastrado
                    }
                }
                if(validacao.getVotoValido() == true){
                    System.out.println("Digite a opcao desejada: | BRANCO | CORRIGE | CONFIRMA |");
                    String opcaoVotacao = sc.nextLine().toUpperCase();
                    do{
                        switch(opcaoVotacao){
                            case "BRANCO":
                                cargos[i].setVotosBranco();
                                break;
                            case "CONFIRMA":
                                Candidato[] candidatos = cargos[i].getCandidados();
                                    System.out.println(candidatos[k].getVotos());
                                    candidatos[k].somaVoto();
                                    System.out.println("CHEGOU AQUI\n");
                                    System.out.println(candidatos[k].getVotos());
                                break;
                            default:
                                break;
                        }
                    }while(opcaoVotacao.equals("CORRIGE"));
                }
            }
        }


        System.out.println("\n\n");
        sc.close();
    }
}
