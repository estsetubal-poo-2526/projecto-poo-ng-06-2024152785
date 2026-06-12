import flip7.Baralho;
import flip7.Carta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BaralhoTest {

    @Test
    void testCriacaoBaralhoNaoVazio() {
        Baralho baralho = new Baralho();
        assertTrue(baralho.totalCartas() > 0, "O baralho deve ter cartas ao ser criado.");
    }

    @Test
    void testVirarCarta() {
        Baralho baralho = new Baralho();
        int totalAntes = baralho.totalCartas();
        Carta carta = baralho.virarCarta();

        assertNotNull(carta, "Deve ser possível virar uma carta.");
        assertEquals(totalAntes - 1, baralho.totalCartas(), "O número de cartas deve diminuir.");
    }

    @Test
    void testReiniciarBaralho() {
        Baralho baralho = new Baralho();
        while(baralho.virarCarta() != null);

        assertEquals(0, baralho.totalCartas());
        baralho.iniciar();
        assertTrue(baralho.totalCartas() > 0, "O baralho deve ser reposto.");
    }

    @Test
    void testCartasEspeciaisExistem() {
        Baralho baralho = new Baralho();
        boolean encontrouDobro = false;

        Carta c;
        while ((c = baralho.virarCarta()) != null) {
            if (c.toString().toLowerCase().contains("x2")) {
                encontrouDobro = true;
                break;
            }
        }
        assertTrue(encontrouDobro, "O baralho deve conter a carta Double.");
    }
}