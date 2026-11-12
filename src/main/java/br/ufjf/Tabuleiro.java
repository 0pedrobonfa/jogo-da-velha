package br.ufjf;

public class Tabuleiro {

    private int line_num = 3;
    private int col_num = 3;
    private String[][] tabuleiro = new String[line_num][col_num];

    public String[][] getTabuleiro() {return tabuleiro;}

    public void setTabuleiro(int i, int j, String valor) {
        this.tabuleiro[i][j] = valor;
    }

    public void inicializaTabuleiro(){
        for(int i = 0;i<line_num;i++){
            for(int j=0;j<col_num;j++){
                setTabuleiro(i,j,"_");
            }
        }
    }

    public boolean estaLivre(int i, int j) {
        return this.tabuleiro[i][j].equals("_");
    }

    private boolean saoIguais(String a, String b, String c) {
        return !a.equals("_") && a.equals(b) && b.equals(c);
    }

    public String checaVencedor() {
        // Checa Linhas
        for (int i = 0; i < line_num; i++) {
            if (saoIguais(tabuleiro[i][0], tabuleiro[i][1], tabuleiro[i][2])) {
                return tabuleiro[i][0]; // Retorna "X" ou "O"
            }
        }
        // Checa Colunas
        for (int j = 0; j < col_num; j++) {
            if (saoIguais(tabuleiro[0][j], tabuleiro[1][j], tabuleiro[2][j])) {
                return tabuleiro[0][j]; // Retorna "X" ou "O"
            }
        }
        // Checa Diagonais
        if (saoIguais(tabuleiro[0][0], tabuleiro[1][1], tabuleiro[2][2])) {
            return tabuleiro[0][0];
        }
        if (saoIguais(tabuleiro[0][2], tabuleiro[1][1], tabuleiro[2][0])) {
            return tabuleiro[0][2];
        }
        return null;

    }

   // Verifica se tabuleiro está cheio
    public boolean estaCheio() {
        for (int i = 0; i < line_num; i++) {
            for (int j = 0; j < col_num; j++) {
                if (this.tabuleiro[i][j].equals("_")) {
                    return false;
                }
            }
        }

        return true;
    }

    // Verifica validade de uma posição
    public boolean posicaoValida(int i, int j){
        return ((i>=0 && i<line_num) && (j>=0 && j<col_num));
    }

}
