package br.ufjf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSetEstrTest {
    private Jogo jogo;
    private Tabuleiro tabuleiro;

    @BeforeEach
    void setUp() {
        jogo = new Jogo();
        jogo.iniciaJogo();
        tabuleiro = new Tabuleiro();
        tabuleiro.inicializaTabuleiro();
    }

    @Test
    void estr01_deveRetornarVitoriaDiagonalPrincipal() {
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
    void estr02_deveRetornarVitoriaDiagonalSecundaria() {
        jogo.fazJogada("(0,2,X)");
        jogo.fazJogada("(1,1,X)");
        jogo.fazJogada("(2,0,X)");
        jogo.verificaEstadojogo();
        assertEquals("VITORIA X", jogo.getEstadoJogo());
    }

    @Test
    void estr03_deveRetornarVitoriaVertical() {
        jogo.fazJogada("(0,2,X)");
        jogo.fazJogada("(1,2,X)");
        jogo.fazJogada("(2,2,X)");
        jogo.verificaEstadojogo();
        assertEquals("VITORIA X", jogo.getEstadoJogo());
    }

    @Test
    void estr04_deveCobrirCondicaoParcialEmSaoIguais() {
        jogo.fazJogada("(0,0,X)");
        jogo.fazJogada("(0,1,X)");
        jogo.fazJogada("(0,2,O)"); // Quebra a trinca no terceiro elemento
        jogo.verificaEstadojogo();
        assertEquals("EM_ANDAMENTO", jogo.getEstadoJogo());
    }

    @Test
    void estr05_deveRetornarTabuleiroVazio() {
        String[][] tabEsperado = {
                {"_", "_", "_"},
                {"_", "_", "_"},
                {"_", "_", "_"}
        };
        assertArrayEquals(tabEsperado, tabuleiro.getTabuleiro());
    }

    @Test
    void estr06_deveAlterarCelulaTabuleiroDiretamente() {
        tabuleiro.setTabuleiro(0, 1, "O");
        assertEquals("O", tabuleiro.getTabuleiro()[0][1]);
        assertFalse(tabuleiro.estaLivre(0, 1));
    }
}