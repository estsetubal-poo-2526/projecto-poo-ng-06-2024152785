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

            case "+2":
                jogador.adicionarPontuacaoTemporaria(2);
                break;

            case "+4":
                jogador.adicionarPontuacaoTemporaria(4);
                break;

            case "+6":
                jogador.adicionarPontuacaoTemporaria(6);
                break;

            case "+8":
                jogador.adicionarPontuacaoTemporaria(8);
                break;

            case "+10":
                jogador.adicionarPontuacaoTemporaria(10);
                break;

            default:
                System.out.println("Efeito desconhecido: " + tipoEfeito);
        }
    }

    @Override
    public String toString() {
        return "Especial: " + tipoEfeito;
    }
}