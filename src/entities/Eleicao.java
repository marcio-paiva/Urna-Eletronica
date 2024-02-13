package entities;
import interfaces.*;
import exceptions.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Eleicao implements Relatorio{
    public void iniciarEleicao() throws NumeroInvalidoException{
        final int QTDCARGOS = 2;
        final int QTDCANDIDATOS = 2; //não pode ser 1
        Scanner sc = new Scanner(System.in);
        Cargo cargos[] = new Cargo[QTDCARGOS];
        Candidato[][] candidatosPorCargo = new Candidato[QTDCARGOS][QTDCANDIDATOS];
        Validacoes validacao = new Validacoes(); 
        System.out.println("\n\n");
        System.out.println("***************************\n      URNA ELETRONICA      \n***************************\n\n");
        int opcaoAdministrador = 0;
            do{
                try{
                    System.out.println("Para cadastrar as informacoes da eleicao digite a senha de acesso administrativo: ");
                    opcaoAdministrador = sc.nextInt();
                    validacao.setEntradaValida(true);
                }catch(InputMismatchException e){
                    System.out.println("ERRO - Entrada invalida. Por favor, digite um numero inteiro.\n");
                    sc.nextLine(); //limpando buffer do teclado
                }
            }while(!validacao.getEntradaValida());
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
                        int numeroCandidato = 0;
                        do{
                            try{
                                System.out.println("Informe o numero do candidato " + (i+1) + " (O numero deve estar entre 1 e 998): ");
                                numeroCandidato = sc.nextInt();
                                for(int l=0; l<i; l++){
                                    if(candidatosPorCargo[j][l].getNumero() == numeroCandidato){ //teste se o numero ja foi cadastrado para algum candidato
                                        throw new NumeroInvalidoException("ERRO - Esse numero foi cadastrado para outro candidato.");
                                    }
                                }
                                if(numeroCandidato <= 0 || numeroCandidato >= 999)
                                    throw new NumeroInvalidoException("ERRO - Numero fora do intervalo permitido (1 a 998).");
                                sc.nextLine(); //limpando o buffer do teclado
                                validacao.setNumeroValido(true);
                            }catch(InputMismatchException e){
                                System.out.println("ERRO - Entrada invalida. Por favor, digite um numero inteiro.\n");
                                validacao.setNumeroValido(false);
                                sc.nextLine(); //limpando buffer do teclado
                            }catch(NumeroInvalidoException e){
                                System.out.println(e.getMessage() + "\n");
                                validacao.setNumeroValido(false);
                                sc.nextLine(); //limpando buffer do teclado
                            }
                        }while(!validacao.getNumeroValido());
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
            validacao.setFinalizarVotacao(true);
        }

        if(validacao.getFinalizarVotacao()){
            sc.close();
            return;
        }

        //imprime informacoes dos candidatos
        System.out.println("\nURNA ZERADA\n");
        imprimeRelatorio(cargos, QTDCARGOS);

        //votacao
        if(validacao.getCandidatosCriados() == true){
            do{
                int contadorFinalizar = 0;
                for(int i=0; i<QTDCARGOS; i++){
                    String opcaoVotacao = "";
                    do{
                        try{
                            do{
                                System.out.println("\n\n" + cargos[i].getNomeCargo() + "\n");
                                System.out.println("Seu voto            | BRANCO | CORRIGE | CONFIRMA |\n");
                                String votoDoEleitor = sc.nextLine().toUpperCase();
                                if(votoDoEleitor.equals("CONFIRMA")){
                                    System.out.println("Para CONFIRMAR e necessario digitar o numero do candidato ou votar em BRANCO.");
                                    opcaoVotacao = "CORRIGE";    
                                }else if(votoDoEleitor.equals("CORRIGE")){
                                    System.out.println("Para utilizar o CORRIGE voce deve ter digitado algum numero ou ter votado em BRANCO."); 
                                    opcaoVotacao = "CORRIGE";     
                                }else if(votoDoEleitor.equals("BRANCO")){
                                    System.out.println("SEU VOTO PARA " + cargos[i].getNomeCargo() + " \n\n    VOTO EM BRANCO\n\n");
                                    System.out.println("\nCONFIRMA para CONFIRMAR este voto.\nCORRIGE para REINICIAR este voto.");
                                    opcaoVotacao = sc.nextLine().toUpperCase(); 
                                    if(opcaoVotacao.equals("CONFIRMA")){
                                        cargos[i].setVotosBranco(); 
                                    }
                                }else if(Integer.parseInt(votoDoEleitor) == 999){
                                    System.out.println("\nCONFIRMA para CONFIRMAR este voto.\nCORRIGE para REINICIAR este voto.");
                                    opcaoVotacao = sc.nextLine().toUpperCase(); 
                                    if(opcaoVotacao.equals("CONFIRMA")){
                                        contadorFinalizar++; 
                                    } 
                                    if(contadorFinalizar == QTDCANDIDATOS){
                                        validacao.setFinalizarVotacao(true);
                                    }     
                                }else{
                                    validacao.setVotoValido(false);
                                    int j, k=0; //sera usada fora do loop posteriormente
                                    for(j=0; j<QTDCANDIDATOS; j++){
                                        if(candidatosPorCargo[i][j].getNumero() == Integer.parseInt(votoDoEleitor)){ //transforma a opcao em inteiro para comparar
                                            System.out.println("\n       Candidato: " + candidatosPorCargo[i][j].getNome());
                                            k = j;
                                            validacao.setVotoValido(true); //valida se o voto foi em um candidato cadastrado
                                        }
                                    }
                                    if(validacao.getVotoValido() == true){
                                        validacao.setConfirmacaoValida(false);
                                        do{
                                            System.out.println("                    \n| BRANCO | CORRIGE | CONFIRMA |");
                                            System.out.println("\nCONFIRMA para CONFIRMAR este voto.\nCORRIGE para REINICIAR este voto.");
                                            opcaoVotacao = sc.nextLine().toUpperCase();
                                            switch(opcaoVotacao){
                                                case "BRANCO":
                                                    System.out.println("\nSEU VOTO PARA " + cargos[i].getNomeCargo() + " \n\n    VOTO EM BRANCO\n\n");
                                                    System.out.println("\nCONFIRMA para CONFIRMAR este voto.\nCORRIGE para REINICIAR este voto.");
                                                    opcaoVotacao = sc.nextLine().toUpperCase(); 
                                                    if(opcaoVotacao.equals("CONFIRMA")){
                                                        cargos[i].setVotosBranco(); 
                                                    }
                                                    validacao.setConfirmacaoValida(true);
                                                    break;
                                                case "CONFIRMA":
                                                    Candidato[] candidatos = cargos[i].getCandidados();
                                                    candidatos[k].somaVoto();
                                                    validacao.setConfirmacaoValida(true);
                                                    break;
                                                default:
                                                    System.out.println("\nERRO - Entrada invalida, tente novamente.\n");
                                                    break;
                                            }
                                        }while(!validacao.getConfirmacaoValida());
                                    }else{
                                        System.out.println("O numero informado nao corresponde a nenhum candidato.");
                                        System.out.println("SEU VOTO PARA " + cargos[i].getNomeCargo() + " \n\n    VOTO NULO\n\n");
                                        System.out.println("CONFIRMA para CONFIRMAR este voto.\nCORRIGE para REINICIAR este voto.");
                                        opcaoVotacao = sc.nextLine().toUpperCase(); 
                                        if(opcaoVotacao.equals("CONFIRMA")){
                                            cargos[i].setVotosNulo(); 
                                        }
                                    }
                                }
                            }while(opcaoVotacao.equals("CORRIGE"));
                            validacao.setOpcaoValida(true);
                        }catch(NumberFormatException e){
                            System.out.println("ERRO - Entrada invalida, tente novamente.");
                            validacao.setOpcaoValida(false);
                        }
                    }while(!validacao.getOpcaoValida());
                } //primeiro for da votacao
            }while(validacao.getFinalizarVotacao() == false);
        }else{
            System.out.println("ERRO - Os candidatos e cargos nao foram registrados.");
            validacao.setFinalizarVotacao(true);
        } //primeiro if da votacao

        //imprime informacoes dos candidatos
        System.out.println("\n***************************\n      RESULTADO FINAL      \n***************************\n");
        imprimeRelatorio(cargos, QTDCARGOS);
        System.out.println("\n");

        sc.close();
    }
    
    @Override
    public void imprimeRelatorio(Cargo[] cargos, int QTDCARGOS){
        for(int i=0; i<QTDCARGOS; i++){
            System.out.println(cargos[i].getNomeCargo());
            cargos[i].imprimeCandidatos();
            System.out.println("Votos em branco: " + cargos[i].getVotosBranco());
            System.out.println("Votos Nulos: " + cargos[i].getVotosNulo() + "\n");
        }
    }
}

