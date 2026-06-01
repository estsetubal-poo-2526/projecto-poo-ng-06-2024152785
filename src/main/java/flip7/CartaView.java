package flip7;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CartaView extends StackPane {

    public CartaView(String numero) {
        setPrefSize(85, 120);
        setMaxSize(85, 120);

        Rectangle fundo = new Rectangle(85, 120);
        fundo.setArcWidth(18);
        fundo.setArcHeight(18);

        fundo.setFill(Color.rgb(230, 210, 175)); // bege
        fundo.setStroke(Color.rgb(130, 75, 160)); // roxo
        fundo.setStrokeWidth(3);

        fundo.setEffect(new DropShadow(8, Color.rgb(60, 20, 90)));

        Text textoSombra = new Text(numero);
        textoSombra.setStyle(
                "-fx-font-size: 42px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: #5A1E8A;"
        );
        textoSombra.setTranslateX(3);
        textoSombra.setTranslateY(3);

        Text textoNumero = new Text(numero);
        textoNumero.setStyle(
                "-fx-font-size: 42px;" +
                        "-fx-font-weight: bold;"
        );

        textoNumero.setFill(corDoNumero(numero));

        setAlignment(Pos.CENTER);
        getChildren().addAll(fundo, textoSombra, textoNumero);
    }

    private LinearGradient corDoNumero(String numero) {
        int n;

        try {
            n = Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            n = 0;
        }

        return switch (n) {
            case 1 -> gradiente("#FF4FA3", "#B000FF"); // rosa → roxo
            case 2 -> gradiente("#00E5FF", "#006BFF"); // ciano → azul
            case 3 -> gradiente("#00FF95", "#00A86B"); // verde claro → verde
            case 4 -> gradiente("#FF4F7B", "#D6007A"); // rosa forte
            case 5 -> gradiente("#8A5CFF", "#3B00FF"); // violeta → azul
            case 6 -> gradiente("#00FFC8", "#008CFF"); // água → azul
            case 7 -> gradiente("#FF5FD7", "#7B2CFF"); // magenta → roxo
            case 8 -> gradiente("#4DFF6D", "#00B85C"); // verde vivo
            case 9 -> gradiente("#FF6B6B", "#C4007A"); // coral → magenta escuro
            case 10 -> gradiente("#5B8CFF", "#7A00FF"); // azul → roxo
            case 11 -> gradiente("#00D4FF", "#00A86B"); // azul → verde
            case 12 -> gradiente("#FF4FBA", "#5F2CFF"); // rosa → violeta
            default -> gradiente("#00E5FF", "#B000FF");
        };
    }

    private LinearGradient gradiente(String cor1, String cor2) {
        return new LinearGradient(
                0,
                0,
                1,
                1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web(cor1)),
                new Stop(1, Color.web(cor2))
        );
    }
}