package flip7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jogo {

    private int jogadorAtual;
    private int pontuacaoJogador1;
    private int pontuacaoJogador2;

    private List<Integer> baralho;
    private List<Integer> cartasMao;

    private String mensagem;

    public Jogo() {
        jogadorAtual = 1;
        pontuacaoJogador1 = 0;
        pontuacaoJogador2 = 0;
        baralho = criarBaralho();
        cartasMao = new ArrayList<>();
        mensagem = "Toca no baralho para tirar cartas";
    }

    public void tirarCarta() {
        if (baralho.isEmpty()) {
            baralho = criarBaralho();
        }

        int carta = baralho.remove(0);

        if (cartasMao.contains(carta)) {
            mensagem = "BUST! Saiu outro " + carta + ". Vez do próximo jogador.";
            cartasMao.clear();
            trocarJogador();
            return;
        }

        cartasMao.add(carta);
        mensagem = "Carta tirada: " + carta;
    }

    public void pararJogador() {
        int pontosMao = calcularPontosMao();

        if (jogadorAtual == 1) {
            pontuacaoJogador1 += pontosMao;
        } else {
            pontuacaoJogador2 += pontosMao;
        }

        mensagem = "Jogador " + jogadorAtual + " parou e ganhou " + pontosMao + " pontos.";

        cartasMao.clear();
        trocarJogador();
    }

    private void trocarJogador() {
        if (jogadorAtual == 1) {
            jogadorAtual = 2;
        } else {
            jogadorAtual = 1;
        }
    }

    public int calcularPontosMao() {
        int total = 0;

        for (int carta : cartasMao) {
            total += carta;
        }

        return total;
    }

    private List<Integer> criarBaralho() {
        List<Integer> novoBaralho = new ArrayList<>();

        for (int numero = 1; numero <= 12; numero++) {
            for (int quantidade = 0; quantidade < numero; quantidade++) {
                novoBaralho.add(numero);
            }
        }

        Collections.shuffle(novoBaralho);
        return novoBaralho;
    }

    public int getJogadorAtual() {
        return jogadorAtual;
    }

    public int getPontuacaoJogador1() {
        return pontuacaoJogador1;
    }

    public int getPontuacaoJogador2() {
        return pontuacaoJogador2;
    }

    public List<Integer> getCartasMao() {
        return cartasMao;
    }

    public String getMensagem() {
        return mensagem;
    }
}