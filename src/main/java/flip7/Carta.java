package flip7;

public abstract class Carta {
    protected int valor;
    protected int id;

    public Carta(int valor, int id) {
        this.valor = valor;
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public abstract void aplicarEfeito(Jogador jogador, Mesa mesa);

    @Override
    public String toString() {
        return "Carta{" +
                "valor=" + valor +
                ", id=" + id +
                '}';
    }
}