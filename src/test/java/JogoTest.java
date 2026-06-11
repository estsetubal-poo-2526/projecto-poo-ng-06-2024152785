import flip7.Jogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JogoTest {
    private Jogo jogo;

    @BeforeEach
    void setUp() {
        jogo = new Jogo(2);
    }

    @Test
    void testInicializacaoJogadores() {
        assertEquals(2, jogo.getJogadores().size(), "O jogo deve ter 2 jogadores.");
    }

    @Test
    void testJogadorInicial() {
        assertEquals(1, jogo.getJogadorAtual(), "O jogo deve começar pelo Jogador 1.");
    }

    @Test
    void testMensagemInicial() {
        assertEquals("Toca no baralho para começar!", jogo.getMensagem());
    }
}