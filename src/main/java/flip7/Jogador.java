package flip7;

import java.util.ArrayList;
import java.util.List;

public class Jogador {

    private String nome;
    private int pontuacao;
    private List<Carta> cartasNaMao;
    private boolean parado;
    private int multiplicador;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
        this.cartasNaMao = new ArrayList<>();
        this.parado = false;
        this.multiplicador = 1;
    }

    public Carta virarCarta(Baralho baralho) {
        Carta carta = baralho.virarCarta();
        if (carta != null) {
            carta.aplicarEfeito(this, null);
        }
        return carta;
    }

    public void pararRodada() {
        int pontos = calcularPontos();
        pontuacao += pontos;
        parar();
    }

    public int calcularPontos() {
        int total = 0;

        for (Carta carta : cartasNaMao) {
            total += carta.getValor();
        }

        return total * multiplicador;
    }

    public boolean temBust() {
        List<Integer> numeros = new ArrayList<>();

        for (Carta carta : cartasNaMao) {
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

    public void adicionarCarta(Carta carta) {
        cartasNaMao.add(carta);
    }

    public void limparMao() {
        cartasNaMao.clear();
        parado = false;
        multiplicador = 1;
    }

    public void parar() {
        this.parado = true;
    }

    public boolean estaParado() {
        return parado;
    }

    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public List<Carta> getCartasNaMao() {
        return cartasNaMao;
    }

    public void setMultiplicador(int multiplicador) {
        this.multiplicador = multiplicador;
    }
}