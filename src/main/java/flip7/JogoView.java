package flip7;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.util.function.Consumer;

public class JogoView extends BorderPane {
    private Jogo jogo;
    private VBox painelPontuacoes;
    private HBox areaCartasNormais;
    private Text textoMensagem;
    private Consumer<Integer> aoVencer;

    public JogoView(int numJogadores, Runnable aoVoltar, Consumer<Integer> aoVencer) {
        this.jogo = new Jogo(numJogadores);
        this.aoVencer = aoVencer;
        setPadding(new Insets(20));

        setTop(criarTopo(aoVoltar));
        setLeft(criarPainelPontuacoes());
        setCenter(criarTabuleiro());
        setRight(criarPainelDireito());
        setBottom(criarZonaCartas());

        atualizarEcra();
    }

    private HBox criarTopo(Runnable aoVoltar) {
        Button botaoVoltar = new Button("X");
        botaoVoltar.setStyle(estiloBotaoVoltar());
        botaoVoltar.setOnAction(event -> aoVoltar.run());HBox topo = new HBox();
        topo.setAlignment(Pos.TOP_RIGHT);
        topo.getChildren().add(botaoVoltar);
        return topo;
    }

    private VBox criarPainelPontuacoes() {
        painelPontuacoes = new VBox(20);
        painelPontuacoes.setAlignment(Pos.TOP_LEFT);
        painelPontuacoes.setPadding(new Insets(40, 20, 20, 20));
        painelPontuacoes.setPrefWidth(240);
        return painelPontuacoes;
    }

    private StackPane criarTabuleiro() {
        StackPane tabuleiro = new StackPane();
        BaralhoView botaoBaralho = new BaralhoView();
        botaoBaralho.setOnAction(event -> {
            jogo.tirarCarta();
            atualizarEcra();
        });
        tabuleiro.getChildren().add(botaoBaralho);
        return tabuleiro;
    }

    private VBox criarPainelDireito() {
        Button botaoParar = new Button("PARAR");
        botaoParar.setPrefSize(150, 70);
        botaoParar.setStyle(estiloBotaoParar());
        botaoParar.setOnAction(event -> {
            jogo.pararJogador();
            atualizarEcra();
        });
        VBox painel = new VBox();
        painel.setAlignment(Pos.CENTER);
        painel.setPadding(new Insets(20));painel.setPrefWidth(200);
        painel.getChildren().add(botaoParar);
        return painel;
    }

    private BorderPane criarZonaCartas() {
        BorderPane painelCartas = new BorderPane();
        painelCartas.setPadding(new Insets(15));
        painelCartas.setPrefHeight(210);
        painelCartas.setStyle("-fx-background-color: rgba(0, 0, 0, 0.45); -fx-background-radius: 25; -fx-border-color: rgba(255, 255, 255, 0.7); -fx-border-radius: 25; -fx-border-width: 2;");

        textoMensagem = new Text();
        textoMensagem.setStyle(estiloTexto());
        areaCartasNormais = new HBox(15);
        areaCartasNormais.setAlignment(Pos.CENTER);

        VBox zona = new VBox(10);
        zona.setAlignment(Pos.CENTER);
        zona.getChildren().addAll(textoMensagem, areaCartasNormais);
        painelCartas.setCenter(zona);
        return painelCartas;
    }

    private void atualizarEcra() {
        painelPontuacoes.getChildren().clear();
        Text titulo = new Text("Pontuação");
        titulo.setStyle(estiloTituloPequeno());
        painelPontuacoes.getChildren().add(titulo);

        for (Jogador j : jogo.getJogadores()) {
            Text t = new Text(j.getNome() + ": " + j.getPontuacao());
            String cor = (jogo.getJogadorAtualObj() == j) ? "#00FF00" : "white";
            t.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: " + cor + ";");painelPontuacoes.getChildren().add(t);
        }

        textoMensagem.setText(jogo.getMensagem());
        areaCartasNormais.getChildren().clear();
        for (Carta c : jogo.getCartasMesa()) {
            String valor = (c instanceof CartaNumero) ? String.valueOf(c.getValor()) : c.toString();
            areaCartasNormais.getChildren().add(new CartaView(valor));
        }

        int vencedor = jogo.getVencedor();
        if (vencedor > 0) {
            aoVencer.accept(vencedor);
        }
    }

    private String estiloBotaoVoltar() { return "-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold; -fx-background-radius: 30; -fx-min-width: 50px; -fx-min-height: 50px; -fx-cursor: hand;"; }
    private String estiloBotaoParar() { return "-fx-font-size: 24px; -fx-font-weight: bold; -fx-background-color: rgba(220, 30, 30, 0.9); -fx-text-fill: white; -fx-background-radius: 25; -fx-cursor: hand;"; }
    private String estiloTexto() { return "-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: white;"; }
    private String estiloTituloPequeno() { return "-fx-font-size: 28px; -fx-font-weight: bold; -fx-fill: white;"; }
}