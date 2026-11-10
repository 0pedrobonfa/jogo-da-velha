package br.ufjf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TabuleiroTest {
    private Tabuleiro tabuleiro;
    @BeforeEach
    void setUp(){
        tabuleiro = new Tabuleiro();
        tabuleiro.inicializaTabuleiro();
    }

    @Test
    void deveRetornarTabuleiroVazio(){

        String[][] tabEsperado = {
                {"_", "_", "_"},
                {"_", "_", "_"},
                {"_", "_", "_"}
        };
        assertArrayEquals(tabEsperado, tabuleiro.getTabuleiro());
    }

    @Test
    void deveRetornarTabuleiroComEscolha(){

        String[][] tabEsperado = {
                {"_", "X", "_"},
                {"_", "_", "_"},
                {"_", "_", "_"}
        };
        tabuleiro.setTabuleiro(0,1,"X");
        assertArrayEquals(tabEsperado, tabuleiro.getTabuleiro());
    }

}
