package flip7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {

    private List<Carta> cartas;

    public Baralho() {
        cartas = new ArrayList<>();
        inicializar();
        embaralhar();
    }

    private void inicializar() {
        int id = 1;

        for (int numero = 1; numero <= 12; numero++) {
            for (int quantidade = 0; quantidade < numero; quantidade++) {
                cartas.add(new CartaNumero(numero, id++));
            }
        }

        cartas.add(new CartaEspecial("freeze", 0, id++));
        cartas.add(new CartaEspecial("x2", 0, id++));
        cartas.add(new CartaEspecial("+2", 2, id++));
        cartas.add(new CartaEspecial("+4", 4, id++));
        cartas.add(new CartaEspecial("+6", 6, id++));
        cartas.add(new CartaEspecial("+8", 8, id++));
        cartas.add(new CartaEspecial("+10", 10, id++));
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public Carta virarCarta() {
        if (cartas.isEmpty()) {
            return null;
        }

        return cartas.remove(0);
    }

    public void iniciar() {
        cartas.clear();
        inicializar();
        embaralhar();
    }

    public int totalCartas() {
        return cartas.size();
    }
}