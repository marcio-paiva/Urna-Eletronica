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

        System.out.println("***************************\n      URNA ELETRONICA      \n***************************\n\n");
        System.out.println("Para cadastrar as informacoes da eleicao digite a senha de acesso administrativo: ");
        int opcaoAdministrador = sc.nextInt();
        sc.nextLine(); //limpando buffer do teclado
        if(opcaoAdministrador == 999){
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
                            for(int l=0; l<i; l++){
                                System.out.println("Valor do i: " + i);
                                if(candidatosPorCargo[j][l].getNumero() == numeroCandidato){ //teste se o numero ja foi acadastrado para algum candidato
                                    numeroCandidato = 0;
                                }
                            }
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
        }else{
            System.out.println("Senha incorreta.\nNao e possivel realizar a eleicao sem registrar os cargos e candidatos.\n\n");
            return;
        }

        //imprime informacoes dos candidatos
        for(int i=0; i<QTDCARGOS; i++){
            System.out.println("\n" + cargos[i].getNomeCargo());
            cargos[i].imprimeCandidatos();
        }

        //votacao
        if(validacao.getCandidatosCriados() == true){
            String votoDoEleitor = null;
            do{
                for(int i=0; i<QTDCARGOS; i++){
                    String opcaoVotacao = null;
                            do{
                                System.out.println("\n\n" + cargos[i].getNomeCargo() + "\n");
                                System.out.println("Seu voto            | BRANCO | CORRIGE | CONFIRMA |");
                                votoDoEleitor = sc.nextLine().toUpperCase();
                                if(votoDoEleitor.equals("CONFIRMA")){
                                    System.out.println("Para CONFIRMAR e necessario digitar o numero do candidato ou votar em BRANCO.");
                                    opcaoVotacao = "CORRIGE";    
                                }else if(votoDoEleitor.equals("CORRIGE")){
                                    System.out.println("Para utilizar o CORRIGE voce deve ter digitado algum numero ou ter votado em BRANCO."); 
                                    opcaoVotacao = "CORRIGE";     
                                }else if(votoDoEleitor.equals("BRANCO")){
                                    System.out.println("SEU VOTO PARA " + cargos[i].getNomeCargo() + " \n\n    VOTO EM BRANCO\n\n");
                                    System.out.println("CONFIRMA para CONFIRMAR este voto.\nCORRIGE para REINICIAR este voto.");
                                    opcaoVotacao = sc.nextLine().toUpperCase(); 
                                    if(opcaoVotacao.equals("CONFIRMA")){
                                        cargos[i].setVotosBranco(); 
                                    }
                                }else if(Integer.parseInt(votoDoEleitor) == 999){
                                    System.out.println("\n\nEleicao finalizada. Resultado final:\n");
                                    for(int m=0; m<QTDCARGOS; m++){
                                        System.out.println("\n" + cargos[m].getNomeCargo());
                                        cargos[m].imprimeCandidatos();
                                    }
                                    System.out.println("\n\n");
                                    return;      
                                }else{
                                    int j, k=0; //sera usada fora do loop posteriormente
                                    for(j=0; j<QTDCANDIDATOS; j++){
                                        if(candidatosPorCargo[i][j].getNumero() == Integer.parseInt(votoDoEleitor)){ //transforma a opcao em inteiro para comparar
                                            System.out.println("Candidato: " + candidatosPorCargo[i][j].getNome());
                                            k = j;
                                            validacao.setVotoValido(true); //valida se o voto foi em um candidato cadastrado
                                        }
                                    }
                                    if(validacao.getVotoValido() == true){
                                        System.out.println("                    | BRANCO | CORRIGE | CONFIRMA |");
                                        System.out.println("CONFIRMA para CONFIRMAR este voto.\nCORRIGE para REINICIAR este voto.");
                                        opcaoVotacao = sc.nextLine().toUpperCase();
                                        switch(opcaoVotacao){
                                            case "BRANCO":
                                                System.out.println("SEU VOTO PARA " + cargos[i].getNomeCargo() + " \n\n    VOTO EM BRANCO\n\n");
                                                System.out.println("CONFIRMA para CONFIRMAR este voto.\nCORRIGE para REINICIAR este voto.");
                                                opcaoVotacao = sc.nextLine().toUpperCase(); 
                                                if(opcaoVotacao.equals("CONFIRMA")){
                                                    cargos[i].setVotosBranco(); 
                                                }
                                                break;
                                            case "CONFIRMA":
                                                Candidato[] candidatos = cargos[i].getCandidados();
                                                candidatos[k].somaVoto();
                                                break;
                                            default:
                                                break;
                                        }
                                    }else{
                                        System.out.println("O numero informado nao corresponde a nenhum candidato.");
                                        opcaoVotacao = "CORRIGE";
                                    }
                                }
                            }while(opcaoVotacao.equals("CORRIGE"));
                } //primeiro for da votacao
            }while(Integer.parseInt(votoDoEleitor) != 999);
        }else{
            System.out.println("ERRO - Os candidatos e cargos nao foram registrados.");
            return;
        } //primeiro if da votacao

        //imprime informacoes dos candidatos
        for(int i=0; i<QTDCARGOS; i++){
            System.out.println(cargos[i].getNomeCargo());
            cargos[i].imprimeCandidatos();
            System.out.println("Votos em branco: " + cargos[i].getVotosBranco() + "\n");
        }

        System.out.println("\n");
        sc.close();
    }
}

// FALTA FAZER : fazer loop na votação


