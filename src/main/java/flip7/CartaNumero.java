package flip7;

public class CartaNumero extends Carta {

    private int numero;

    public CartaNumero(int numero, int id) {
        super(numero, id);
        this.numero = numero;
    }

    public int getPontuacao() {
        return numero;
    }

    @Override
    public void aplicarEfeito(Jogador jogador, Mesa mesa) {
        // Carta de número não tem efeito imediato no jogador.
        // Os pontos são contabilizados quando o jogador decide parar.
    }

    @Override
    public String toString() {
        return "Carta " + numero;
    }
}