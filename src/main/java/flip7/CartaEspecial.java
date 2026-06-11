package flip7;

public class CartaEspecial extends Carta {

    private String tipoEfeito;

    public CartaEspecial(String tipoEfeito, int valor, int id) {
        super(valor, id);
        this.tipoEfeito = tipoEfeito;
    }

    public String getDescricaoEfeito() {
        return tipoEfeito;
    }

    @Override
    public void aplicarEfeito(Jogador jogador, Mesa mesa) {
        switch (tipoEfeito.toLowerCase()) {
            case "freeze":
                jogador.parar();
                break;

            case "x2":
                jogador.setMultiplicador(2);
                break;

            default:
                // Outros efeitos (como +2, +4, etc.) são baseados no valor da carta
                // e são processados no final da jogada.
                break;
        }
    }

    @Override
    public String toString() {
        return "Especial: " + tipoEfeito;
    }
}