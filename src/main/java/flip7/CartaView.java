package flip7;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CartaView extends StackPane {
    public CartaView(String numero) {
        setPrefSize(85, 120);
        Rectangle f = new Rectangle(85, 120);
        f.setArcWidth(18); f.setArcHeight(18);
        f.setFill(Color.rgb(230, 210, 175));
        f.setStroke(Color.rgb(130, 75, 160));
        f.setStrokeWidth(3);

        boolean isNum = numero.matches("\\d+");
        String estilo = "-fx-font-weight: bold; -fx-font-size: " + (isNum ? "42px" : "18px") + ";";

        Text tNum = new Text(numero);
        tNum.setStyle(estilo);
        tNum.setFill(Color.BLACK);

        getChildren().addAll(f, tNum);
        setAlignment(Pos.CENTER);
    }
}