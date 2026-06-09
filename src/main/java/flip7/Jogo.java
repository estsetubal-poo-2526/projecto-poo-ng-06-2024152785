package flip7;

import java.util.ArrayList;
import java.util.List;

public class Jogo {
    private List<Jogador> jogadores;
    private int indiceJogadorAtual;
    private Baralho baralho;
    private Mesa mesa;
    private String mensagem;

    public Jogo(int numJogadores) {
        jogadores = new ArrayList<>();
        for (int i = 1; i <= numJogadores; i++) {
            jogadores.add(new Jogador("Jogador " + i));
        }
        indiceJogadorAtual = 0;
        baralho = new Baralho();
        mesa = new Mesa();
        mensagem = "Toca no baralho para começar!";
    }

    public void tirarCarta() {
        Carta carta = baralho.virarCarta();
        if (carta == null) {
            baralho.iniciar();
            carta = baralho.virarCarta();
        }

        mesa.adicionarCarta(carta);
        mensagem = "Saiu: " + carta.toString();
        if (mesa.temRepetido()) {
            mensagem = "BUST! Repetiste o " + carta.getValor() + ". Vez do próximo.";
            getJogadorAtualObj().limparMao();
            mesa.limpar();
            trocarJogador();
        } else {
            carta.aplicarEfeito(getJogadorAtualObj(), mesa);
            if (getJogadorAtualObj().estaParado()) {
                pararJogador();
            }
        }
    }

    public void pararJogador() {
        Jogador atual = getJogadorAtualObj();
        for (Carta c : mesa.getCartasViradas()) {
            atual.adicionarCarta(c);
        }
        int pontosGanhos = atual.calcularPontos();
        atual.pararRodada();
        mensagem = "O " + atual.getNome() + " parou e ganhou " + pontosGanhos + " pontos!";

        atual.limparMao();
        mesa.limpar();
        trocarJogador();
    }

    private void trocarJogador() {
        indiceJogadorAtual = (indiceJogadorAtual + 1) % jogadores.size();
    }

    public int getVencedor() {
        for (int i = 0; i < jogadores.size(); i++) {            if (jogadores.get(i).getPontuacao() >= 200) return i + 1;
        }
        return 0;
    }

    public int getJogadorAtual() { return indiceJogadorAtual + 1; }
    public Jogador getJogadorAtualObj() { return jogadores.get(indiceJogadorAtual); }
    public List<Jogador> getJogadores() { return jogadores; }
    public List<Carta> getCartasMesa() { return mesa.getCartasViradas(); }
    public String getMensagem() { return mensagem; }
}