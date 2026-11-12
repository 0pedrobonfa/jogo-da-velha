package br.ufjf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JogoDaVelhaTest {
    private Jogo jogo;

    @BeforeEach
    void setUp(){
        jogo = new Jogo();
        jogo.iniciaJogo();
    }

    // ESCOLHAS DE CARACTERE
    @Test
    void deveJogadorEscolherCaracterX(){
        jogo.setCaractereJogador("X");
        assertEquals("X",jogo.getCaractereJogador());
    }

    @Test
    void deveJogadorEscolherCaracterO(){
        jogo.setCaractereJogador("O");
        assertEquals("O",jogo.getCaractereJogador());
    }

    @Test
    void deveJogadorEscolherCaracterInvalido(){

        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            jogo.setCaractereJogador("Z");
        });
        assertEquals("Caractere Invalido", excecao.getMessage());
    }


    // INSERIR EM POSICOES
    @Test
    void deveExecutarJogadaZeroZeroX(){
        jogo.fazJogada("(0,0,X)");
        String[][] tabEsperado = {
                {"X", "_", "_"},
                {"_", "_", "_"},
                {"_", "_", "_"}
        };
        assertArrayEquals(tabEsperado, jogo.getTabJogo());
    }

    @Test
    void deveExecutarJogadaDoisDoisO(){
        jogo.fazJogada("(2,2,O)");
        String[][] tabEsperado = {
                {"_", "_", "_"},
                {"_", "_", "_"},
                {"_", "_", "O"}
        };
        assertArrayEquals(tabEsperado, jogo.getTabJogo());
    }
    @Test
    void deveExecutarJogadaCentroO() {
        jogo.fazJogada("(1,1,O)");
        String[][] tabEsperado = {
                {"_", "_", "_"},
                {"_", "O", "_"},
                {"_", "_", "_"}
        };
        assertArrayEquals(tabEsperado, jogo.getTabJogo());
    }

    @Test
    void naoDeveInserirEmPosicaoOcupada(){
        jogo.fazJogada("(2,1,X)");

        assertEquals("Posição já ocupada.",jogo.fazJogada("(2,1,O)"));
    }

    @Test
    void naoDeveAceitarCoordenadasForaDoLimite() {
        // Seguindo AVL, posicao invalid
        assertEquals("Essa coordenada não existe.", jogo.fazJogada("(-1,1,X)"));
        assertEquals("Essa coordenada não existe.", jogo.fazJogada("(3,1,X)"));
        assertEquals("Essa coordenada não existe.", jogo.fazJogada("(1,-1,X)"));
        assertEquals("Essa coordenada não existe.", jogo.fazJogada("(1,3,X)"));
    }

    // ESTADOS DO JOGO

    @Test
    void deveRetornarVitoriaDiagonalPrincipal(){
        jogo.fazJogada("(2,1,X)");
        jogo.fazJogada("(0,0,O)");
        jogo.fazJogada("(0,1,X)");
        jogo.fazJogada("(1,1,O)");
        jogo.fazJogada("(1,2,X)");
        jogo.fazJogada("(2,2,O)");
        jogo.verificaEstadojogo();
        assertEquals("VITORIA O", jogo.getEstadoJogo());
    }

    @Test
    void deveRetornarVitoriaDiagonalSecundaria() {
        jogo.fazJogada("(0,2,X)");
        jogo.fazJogada("(1,1,X)");
        jogo.fazJogada("(2,0,X)");
        jogo.verificaEstadojogo();
        assertEquals("VITORIA X", jogo.getEstadoJogo());
    }

    @Test
    void deveRetornarVitoriaHorizontal(){
        jogo.fazJogada("(2,1,X)");
        jogo.fazJogada("(2,0,X)");
        jogo.fazJogada("(2,2,X)");
        jogo.verificaEstadojogo();
        assertEquals("VITORIA X", jogo.getEstadoJogo());
    }

    @Test
    void deveRetornarVitoriaVertical(){
        jogo.fazJogada("(0,2,X)");
        jogo.fazJogada("(1,2,X)");
        jogo.fazJogada("(2,2,X)");
        jogo.verificaEstadojogo();
        assertEquals("VITORIA X", jogo.getEstadoJogo());
    }


    @Test
    void deveRetornarEmpate(){
        // X O X
        // O X O
        // O X O

        jogo.fazJogada("(0,0,X)");
        jogo.fazJogada("(0,1,O)");
        jogo.fazJogada("(0,2,X)");

        jogo.fazJogada("(1,0,O)");
        jogo.fazJogada("(1,1,X)");
        jogo.fazJogada("(1,2,O)");

        jogo.fazJogada("(2,0,O)");
        jogo.fazJogada("(2,1,X)");
        jogo.fazJogada("(2,2,O)");

        jogo.verificaEstadojogo();
        assertEquals("EMPATE", jogo.getEstadoJogo());
    }

    @Test
    void deveRetornarJogoEmAndamento() {
        jogo.fazJogada("(0,0,X)");
        jogo.verificaEstadojogo();
        assertEquals("EM_ANDAMENTO", jogo.getEstadoJogo());
    }
}
