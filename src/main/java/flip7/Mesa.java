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
        List<Integer> valores = new ArrayList<>();

        for (Carta carta : cartasViradas) {
            int valor = carta.getValor();

            // Só verificamos repetição para valores maiores que 0.
            // Cartas com valor 0 (como Freeze ou x2) não causam BUST por si só.
            if (valor > 0) {
                if (valores.contains(valor)) {
                    return true;
                }
                valores.add(valor);
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