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
                break;
        }
    }

    @Override
    public String toString() { return tipoEfeito; }
}