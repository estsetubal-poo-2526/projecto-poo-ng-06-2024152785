package flip7;

import java.util.ArrayList;
import java.util.List;

public class Mesa {

    private List<Carta> cartasViradas;

    public Mesa() {
        cartasViradas = new ArrayList<>();
    }

    public void adicionarCarta(Carta carta) {
        cartasViradas.add(carta);
    }

    public boolean temRepetido() {
        List<Integer> numeros = new ArrayList<>();

        for (Carta carta : cartasViradas) {
            if (carta instanceof CartaNumero) {
                int valor = carta.getValor();

                if (numeros.contains(valor)) {
                    return true;
                }

                numeros.add(valor);
            }
        }

        return false;
    }

    public void limpar() {
        cartasViradas.clear();
    }

    public int totalCartas() {
        return cartasViradas.size();
    }

    public List<Carta> getCartasViradas() {
        return cartasViradas;
    }
}