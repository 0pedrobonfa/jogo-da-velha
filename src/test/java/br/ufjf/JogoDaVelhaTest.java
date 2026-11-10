package br.ufjf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JogoDaVelhaTest {
    private Jogo jogo;

    @BeforeEach
    void setUp(){
        jogo = new Jogo();
        jogo.iniciaJogo();
    }


    @Test
    void deveJogadorEscolherCaracter(){
        jogo.setCaractereJogador("X");
        assertEquals("X",jogo.getCaractereJogador());
    }

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

}
