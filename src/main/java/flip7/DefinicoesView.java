package flip7;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DefinicoesView extends BorderPane {

    private CheckBox checkSom;
    private Slider sliderVolume;
    private Slider sliderJogadores;

    public DefinicoesView(Runnable aoVoltar, boolean somInicial, double volumeInicial, int numJogadoresInicial) {
        setPadding(new Insets(25));

        Button botaoVoltar = new Button("X");
        botaoVoltar.setStyle(
                "-fx-background-color: red;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 30;" +
                        "-fx-min-width: 50px;" +
                        "-fx-min-height: 50px;" +
                        "-fx-cursor: hand;"
        );

        botaoVoltar.setOnAction(event -> aoVoltar.run());

        HBox topo = new HBox();
        topo.setAlignment(Pos.TOP_RIGHT);
        topo.getChildren().add(botaoVoltar);

        Text titulo = new Text("Definições");
        titulo.setStyle(
                "-fx-font-size: 42px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: white;"
        );

        checkSom = new CheckBox("Som ligado");
        checkSom.setSelected(somInicial);
        checkSom.setStyle(
                "-fx-font-size: 26px;" +
                        "-fx-text-fill: white;"
        );

        Text textoVolume = new Text("Volume");
        textoVolume.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-fill: white;");

        sliderVolume = new Slider(0, 100, volumeInicial);
        sliderVolume.setPrefWidth(300);
        sliderVolume.setShowTickLabels(true);
        sliderVolume.setShowTickMarks(true);
        sliderVolume.setMajorTickUnit(25);

        VBox areaVolume = new VBox(10);
        areaVolume.setAlignment(Pos.CENTER);
        areaVolume.getChildren().addAll(textoVolume, sliderVolume);

        Text textoJogadores = new Text("Número de Jogadores");
        textoJogadores.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-fill: white;");

        sliderJogadores = new Slider(2, 4, numJogadoresInicial);
        sliderJogadores.setPrefWidth(300);
        sliderJogadores.setShowTickLabels(true);
        sliderJogadores.setShowTickMarks(true);
        sliderJogadores.setMajorTickUnit(1);
        sliderJogadores.setSnapToTicks(true);
        sliderJogadores.setMinorTickCount(0);

        VBox areaJogadores = new VBox(10);
        areaJogadores.setAlignment(Pos.CENTER);
        areaJogadores.getChildren().addAll(textoJogadores, sliderJogadores);

        VBox conteudo = new VBox(30);
        conteudo.setAlignment(Pos.CENTER);
        conteudo.getChildren().addAll(titulo, checkSom, areaVolume, areaJogadores);

        setTop(topo);
        setCenter(conteudo);
    }

    public boolean isSomLigado() {
        return checkSom.isSelected();
    }

    public double getVolume() {
        return sliderVolume.getValue();
    }

    public int getNumJogadores() {
        return (int) sliderJogadores.getValue();
    }
}