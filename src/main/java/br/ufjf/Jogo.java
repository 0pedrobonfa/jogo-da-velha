package br.ufjf;

import java.util.Scanner;

public class Jogo {


    private Tabuleiro tab = new Tabuleiro();
    private String p1_caractere;


    public String[][] getTabJogo(){
        return tab.getTabuleiro();
    }

    public void setCaractereJogador(String caractere){
        if(caractere.equals("X") || caractere.equals("O")){
            this.p1_caractere = caractere;
        }
        else{
            throw new IllegalArgumentException(
                    String.format("Caractere inválido.")
            );
        }
    }

    public String getCaractereJogador(){
        return this.p1_caractere;
    }

    public void iniciaJogo(){
        tab.inicializaTabuleiro();
    }

    // Converte a string e manda para setTabuleiro
    public void fazJogada(String jogada){

        // retira espaços antes e depois da entrada
        jogada = jogada.strip();
        // retira todos os espaços
        String jogadaRefinada = jogada.replaceAll("\\s+","");

        // aqui a estrutura SEMPRE será (i,j,X|O)
        char x = jogadaRefinada.charAt(1); // pega jogadaRefinada[1]
        char y = jogadaRefinada.charAt(3); // pega jogadaRefinada[3]
        char c = jogadaRefinada.charAt(5); // pega jogadaRefinada[5]


        int i = Character.getNumericValue(x);
        int j = Character.getNumericValue(y);
        String valor = String.valueOf(c);

        tab.setTabuleiro(i,j,valor);

    }


}
