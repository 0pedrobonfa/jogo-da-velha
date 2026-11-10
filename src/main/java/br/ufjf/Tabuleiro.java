package br.ufjf;

public class Tabuleiro {

    private int line_num = 3;
    private int col_num = 3;
    private String[][] tabuleiro = new String[line_num][col_num];

    public String[][] getTabuleiro() {
        return tabuleiro;
    }

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

    public void imprimeTabuleiro() {
        System.out.println("----------------------------------------------");
        for(int i = 0;i<line_num;i++){
            for(int j=0;j<col_num;j++){
                System.out.print(this.tabuleiro[i][j]+" ");
            }
                System.out.println();
        }
        System.out.println("----------------------------------------------");
    }

}
