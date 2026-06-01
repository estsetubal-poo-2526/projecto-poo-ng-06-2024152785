package flip7;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class JogoView extends BorderPane {

    private Jogo jogo;

    private VBox zonaCartas;
    private HBox areaCartasNormais;
    private Text tituloZonaCartas;
    private Text textoMensagem;

    private Text textoJogadorAtual;
    private Text textoPontuacaoJogador1;
    private Text textoPontuacaoJogador2;
    private Text textoPontosMao;

    private boolean mostrarEspeciais = false;

    public JogoView(Runnable aoVoltar) {
        jogo = new Jogo();

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
        botaoVoltar.setOnAction(event -> aoVoltar.run());

        HBox topo = new HBox();
        topo.setAlignment(Pos.TOP_RIGHT);
        topo.getChildren().add(botaoVoltar);

        return topo;
    }

    private VBox criarPainelPontuacoes() {
        Text titulo = new Text("Pontuação");
        titulo.setStyle(estiloTituloPequeno());

        textoPontuacaoJogador1 = new Text();
        textoPontuacaoJogador1.setStyle(estiloTexto());

        textoPontuacaoJogador2 = new Text();
        textoPontuacaoJogador2.setStyle(estiloTexto());

        textoJogadorAtual = new Text();
        textoJogadorAtual.setStyle(estiloTexto());

        textoPontosMao = new Text();
        textoPontosMao.setStyle(estiloTexto());

        VBox painel = new VBox(20);
        painel.setAlignment(Pos.TOP_LEFT);
        painel.setPadding(new Insets(40, 20, 20, 20));
        painel.setPrefWidth(240);
        painel.getChildren().addAll(
                titulo,
                textoPontuacaoJogador1,
                textoPontuacaoJogador2,
                textoJogadorAtual,
                textoPontosMao
        );

        return painel;
    }

    private StackPane criarTabuleiro() {
        StackPane tabuleiro = new StackPane();

        BaralhoView botaoBaralho = new BaralhoView();

        botaoBaralho.setOnAction(event -> {
            if (mostrarEspeciais) {
                mostrarEspeciais = false;
                tituloZonaCartas.setText("Cartas");
            }

            jogo.tirarCarta();
            atualizarEcra();
        });

        tabuleiro.getChildren().add(botaoBaralho);
        StackPane.setAlignment(botaoBaralho, Pos.CENTER);

        return tabuleiro;
    }

    private VBox criarPainelDireito() {
        Button botaoParar = new Button("PARAR");
        botaoParar.setPrefSize(150, 70);
        botaoParar.setStyle(estiloBotaoParar());

        botaoParar.setOnAction(event -> {
            jogo.pararJogador();

            if (mostrarEspeciais) {
                mostrarEspeciais = false;
                tituloZonaCartas.setText("Cartas");
            }

            atualizarEcra();
        });

        VBox painel = new VBox();
        painel.setAlignment(Pos.CENTER);
        painel.setPadding(new Insets(20));
        painel.setPrefWidth(200);
        painel.getChildren().add(botaoParar);

        return painel;
    }

    private BorderPane criarZonaCartas() {
        BorderPane painelCartas = new BorderPane();
        painelCartas.setPadding(new Insets(15));
        painelCartas.setPrefHeight(210);
        painelCartas.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.45);" +
                        "-fx-background-radius: 25;" +
                        "-fx-border-color: rgba(255, 255, 255, 0.7);" +
                        "-fx-border-radius: 25;" +
                        "-fx-border-width: 2;"
        );

        Button botaoTrocar = new Button("↔");
        botaoTrocar.setStyle(estiloBotaoTrocar());

        tituloZonaCartas = new Text("Cartas");
        tituloZonaCartas.setStyle(estiloTituloPequeno());

        HBox topoZona = new HBox(15);
        topoZona.setAlignment(Pos.CENTER_LEFT);
        topoZona.getChildren().addAll(botaoTrocar, tituloZonaCartas);

        textoMensagem = new Text();
        textoMensagem.setStyle(estiloTexto());

        areaCartasNormais = new HBox(15);
        areaCartasNormais.setAlignment(Pos.CENTER);

        zonaCartas = new VBox(10);
        zonaCartas.setAlignment(Pos.CENTER);
        zonaCartas.getChildren().addAll(textoMensagem, areaCartasNormais);

        botaoTrocar.setOnAction(event -> trocarZonaCartas());

        painelCartas.setTop(topoZona);
        painelCartas.setCenter(zonaCartas);

        BorderPane.setMargin(painelCartas, new Insets(0, 40, 20, 40));

        return painelCartas;
    }

    private void atualizarEcra() {
        textoPontuacaoJogador1.setText("Jogador 1: " + jogo.getPontuacaoJogador1());
        textoPontuacaoJogador2.setText("Jogador 2: " + jogo.getPontuacaoJogador2());
        textoJogadorAtual.setText("Vez: Jogador " + jogo.getJogadorAtual());
        textoPontosMao.setText("Mão: " + jogo.calcularPontosMao());

        textoMensagem.setText(jogo.getMensagem());

        if (!mostrarEspeciais) {
            mostrarCartasNormais();
        }
    }

    private void mostrarCartasNormais() {
        areaCartasNormais.getChildren().clear();

        for (int carta : jogo.getCartasMao()) {
            areaCartasNormais.getChildren().add(new CartaView(String.valueOf(carta)));
        }
    }

    private void trocarZonaCartas() {
        mostrarEspeciais = !mostrarEspeciais;

        zonaCartas.getChildren().clear();

        if (mostrarEspeciais) {
            tituloZonaCartas.setText("Cartas especiais");

            Text texto = new Text("Ainda não tens cartas especiais");
            texto.setStyle(estiloTexto());

            zonaCartas.getChildren().add(texto);
        } else {
            tituloZonaCartas.setText("Cartas");

            textoMensagem = new Text(jogo.getMensagem());
            textoMensagem.setStyle(estiloTexto());

            areaCartasNormais = new HBox(15);
            areaCartasNormais.setAlignment(Pos.CENTER);

            zonaCartas.getChildren().addAll(textoMensagem, areaCartasNormais);

            mostrarCartasNormais();
        }
    }

    private String estiloBotaoVoltar() {
        return "-fx-background-color: red;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 30;" +
                "-fx-min-width: 50px;" +
                "-fx-min-height: 50px;" +
                "-fx-cursor: hand;";
    }

    private String estiloBotaoParar() {
        return "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: rgba(220, 30, 30, 0.9);" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 25;" +
                "-fx-cursor: hand;";
    }

    private String estiloBotaoTrocar() {
        return "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: rgba(255, 255, 255, 0.9);" +
                "-fx-text-fill: black;" +
                "-fx-background-radius: 15;" +
                "-fx-min-width: 45px;" +
                "-fx-min-height: 35px;" +
                "-fx-cursor: hand;";
    }

    private String estiloTexto() {
        return "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: white;";
    }

    private String estiloTituloPequeno() {
        return "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: white;";
    }
}