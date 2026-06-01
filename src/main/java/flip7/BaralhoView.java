package flip7;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class BaralhoView extends Button {

    public BaralhoView() {
        StackPane conteudo = new StackPane();
        conteudo.setAlignment(Pos.CENTER);

        Rectangle fundo = new Rectangle(130, 180);
        fundo.setArcWidth(22);
        fundo.setArcHeight(22);

        fundo.setFill(new LinearGradient(
                0, 0,
                1, 1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#4A78C2")),
                new Stop(1, Color.web("#2F5FA8"))
        ));

        fundo.setStroke(Color.web("#244A86"));
        fundo.setStrokeWidth(4);

        Image imagemLogo = new Image(
                getClass().getResource("/images/vira7.png").toExternalForm()
        );

        ImageView logoView = new ImageView(imagemLogo);
        logoView.setFitWidth(95);
        logoView.setPreserveRatio(true);

        conteudo.getChildren().addAll(fundo, logoView);

        setGraphic(conteudo);

        setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-padding: 0;" +
                        "-fx-cursor: hand;"
        );

        setOnMouseEntered(event -> {
            setScaleX(0.96);
            setScaleY(0.96);
        });

        setOnMouseExited(event -> {
            setScaleX(1.0);
            setScaleY(1.0);
        });
    }
}