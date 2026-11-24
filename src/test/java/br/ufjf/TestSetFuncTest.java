package br.ufjf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSetFuncTest {
    private Jogo jogo;

    @BeforeEach
    void setUp() {
        jogo = new Jogo();
        jogo.iniciaJogo();
    }


    @Test
    void ct01_deveJogadorEscolherCaracterX() {
        jogo.setCaractereJogador("X");
        assertEquals("X", jogo.getCaractereJogador());
    }


    @Test
    void ct02_deveJogadorEscolherCaracterO() {
        jogo.setCaractereJogador("O");
        assertEquals("O", jogo.getCaractereJogador());
    }


    @Test
    void ct03_deveJogadorEscolherCaracterInvalido() {
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            jogo.setCaractereJogador(" ");
        });
        assertEquals("Caractere Invalido", excecao.getMessage());
    }


    @Test
    void ct04_deveExecutarJogadaZeroZeroX() {
        String retorno = jogo.fazJogada("(0,0,X)");
        jogo.verificaEstadojogo();
        String[][] tabEsperado = {
                {"X", "_", "_"},
                {"_", "_", "_"},
                {"_", "_", "_"}
        };
        assertEquals("Jogada realizada", retorno);
        assertArrayEquals(tabEsperado, jogo.getTabJogo());
        assertEquals("EM_ANDAMENTO", jogo.getEstadoJogo());
    }


    @Test
    void ct05_deveExecutarJogadaDoisDoisX() {
        String retorno = jogo.fazJogada("(2,2,X)");
        String[][] tabEsperado = {
                {"_", "_", "_"},
                {"_", "_", "_"},
                {"_", "_", "X"}
        };
        assertEquals("Jogada realizada", retorno);
        assertArrayEquals(tabEsperado, jogo.getTabJogo());
    }


    @Test
    void ct06_deveExecutarJogadaCentroO() {
        String retorno = jogo.fazJogada("(1,1,O)");
        String[][] tabEsperado = {
                {"_", "_", "_"},
                {"_", "O", "_"},
                {"_", "_", "_"}
        };
        assertEquals("Jogada realizada", retorno);
        assertArrayEquals(tabEsperado, jogo.getTabJogo());
    }


    @Test
    void ct07_naoDeveAceitarLinhaMenorQueZero() {
        assertEquals("Essa coordenada não existe.", jogo.fazJogada("(-1,1,X)"));
    }


    @Test
    void ct08_naoDeveAceitarLinhaMaiorQueDois() {
        assertEquals("Essa coordenada não existe.", jogo.fazJogada("(3,1,X)"));
    }


    @Test
    void ct09_naoDeveAceitarColunaMenorQueZero() {
        assertEquals("Essa coordenada não existe.", jogo.fazJogada("(1,-1,X)"));
    }


    @Test
    void ct10_naoDeveAceitarColunaMaiorQueDois() {
        assertEquals("Essa coordenada não existe.", jogo.fazJogada("(1,3,X)"));
    }


    @Test
    void ct11_naoDeveInserirEmPosicaoOcupada() {
        jogo.fazJogada("(0,0,O)");
        assertEquals("Posição já ocupada.", jogo.fazJogada("(0,0,X)"));
    }


    @Test
    void ct12_deveRetornarVitoriaHorizontalLinhaZero() {
        jogo.fazJogada("(0,0,X)");
        jogo.fazJogada("(0,1,X)");
        jogo.fazJogada("(0,2,X)");
        jogo.verificaEstadojogo();
        assertEquals("VITORIA X", jogo.getEstadoJogo());
    }


    @Test
    void ct13_deveRetornarEmpate() {

        // O X X
        // X O O
        // X O X

        jogo.fazJogada("(0,0,O)");
        jogo.fazJogada("(0,1,X)");
        jogo.fazJogada("(0,2,X)");

        jogo.fazJogada("(1,0,X)");
        jogo.fazJogada("(1,1,O)");
        jogo.fazJogada("(1,2,O)");

        jogo.fazJogada("(2,0,X)");
        jogo.fazJogada("(2,2,X)");
        jogo.fazJogada("(2,1,O)");

        jogo.verificaEstadojogo();
        assertEquals("EMPATE", jogo.getEstadoJogo());
    }
}