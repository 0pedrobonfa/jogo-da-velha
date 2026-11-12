package br.ufjf;

public class Jogo {


    private Tabuleiro tab = new Tabuleiro();
    private String player_caractere;
    private String estadoJogo;


    public String[][] getTabJogo(){
        return tab.getTabuleiro();
    }

    public void setCaractereJogador(String caractere){
        if(caractere.equals("X") || caractere.equals("O")){
            this.player_caractere = caractere;
        }
        else{
            throw new IllegalArgumentException("Caractere Invalido");
        }
    }

    public String getCaractereJogador(){
        return this.player_caractere;
    }

    public void iniciaJogo(){
        tab.inicializaTabuleiro();
    }

    // Converte a string e manda para setTabuleiro
    public String fazJogada(String jogada) {

        // Remove parênteses e espaços: "( -1 , 1 , X )" vira "-1,1,X"
        String limpa = jogada.replace("(", "").replace(")",
                "").replaceAll("\\s+", "");
        String[] partes = limpa.split(",");

        int i = Integer.parseInt(partes[0]);
        int j = Integer.parseInt(partes[1]);
        String valor = partes[2];

        if (!tab.posicaoValida(i, j)) {
            return "Essa coordenada não existe.";
        }
        if (!tab.estaLivre(i, j)) {
            return "Posição já ocupada.";
        }

        tab.setTabuleiro(i, j, valor);
        return "Jogada realizada";
    }

    public void setEstadoJogo(String c){
        this.estadoJogo = c;
    }

    public String getEstadoJogo(){
        return this.estadoJogo;
    }


    public void verificaEstadojogo() {
        String vencedor = tab.checaVencedor();
        if (vencedor != null) {
            setEstadoJogo("VITORIA " + vencedor);
        } else if (tab.estaCheio()) {
            setEstadoJogo("EMPATE");
        } else {
            setEstadoJogo("EM_ANDAMENTO");
        }
    }


}
