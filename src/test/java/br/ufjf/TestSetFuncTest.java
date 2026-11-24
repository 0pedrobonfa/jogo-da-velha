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

    // CT01 - Escolha válida 'X' (V1)
    @Test
    void ct01_deveJogadorEscolherCaracterX() {
        jogo.setCaractereJogador("X");
        assertEquals("X", jogo.getCaractereJogador());
    }

    // CT02 - Escolha válida 'O' (V2)
    @Test
    void ct02_deveJogadorEscolherCaracterO() {
        jogo.setCaractereJogador("O");
        assertEquals("O", jogo.getCaractereJogador());
    }

    // CT03 - Escolha inválida (I1)
    @Test
    void ct03_deveJogadorEscolherCaracterInvalido() {
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            jogo.setCaractereJogador(" ");
        });
        assertEquals("Caractere Invalido", excecao.getMessage());
    }

    // CT04 - Limite inferior (0,0) com 'X' (V1, V3a, V4a, V5, V6)
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

    // CT05 - Limite superior (2,2) com 'X' (V1, V3b, V4b, V5, V6)
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

    // CT06 - Posição central (1,1) com 'O' (V2, V3, V4, V5, V6)
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

    // CT07 - Linha abaixo do limite (-1, 1) (I2, V4)
    @Test
    void ct07_naoDeveAceitarLinhaMenorQueZero() {
        assertEquals("Essa coordenada não existe.", jogo.fazJogada("(-1,1,X)"));
    }

    // CT08 - Linha acima do limite (3, 1) (I3, V4)
    @Test
    void ct08_naoDeveAceitarLinhaMaiorQueDois() {
        assertEquals("Essa coordenada não existe.", jogo.fazJogada("(3,1,X)"));
    }

    // CT09 - Coluna abaixo do limite (1, -1) (V3, I4)
    @Test
    void ct09_naoDeveAceitarColunaMenorQueZero() {
        assertEquals("Essa coordenada não existe.", jogo.fazJogada("(1,-1,X)"));
    }

    // CT10 - Coluna acima do limite (1, 3) (V3, I5)
    @Test
    void ct10_naoDeveAceitarColunaMaiorQueDois() {
        assertEquals("Essa coordenada não existe.", jogo.fazJogada("(1,3,X)"));
    }

    // CT11 - Célula já ocupada (V1, V3a, V4a, I6)
    @Test
    void ct11_naoDeveInserirEmPosicaoOcupada() {
        jogo.fazJogada("(0,0,O)");
        assertEquals("Posição já ocupada.", jogo.fazJogada("(0,0,X)"));
    }

    // CT12 - Vitória em linha (0,2) completando linha 0 (V1, V3a, V4b, V5, V7)
    @Test
    void ct12_deveRetornarVitoriaHorizontalLinhaZero() {
        jogo.fazJogada("(0,0,X)");
        jogo.fazJogada("(0,1,X)");
        jogo.fazJogada("(0,2,X)");
        jogo.verificaEstadojogo();
        assertEquals("VITORIA X", jogo.getEstadoJogo());
    }

    // CT13 - Empate na 9ª jogada em (2,1) com 'O' (V2, V3b, V4, V5, V8)
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