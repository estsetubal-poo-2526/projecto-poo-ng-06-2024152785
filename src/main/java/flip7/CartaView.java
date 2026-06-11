package flip7;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CartaView extends StackPane {
    public CartaView(String numero) {
        setPrefSize(85, 120);
        Rectangle f = new Rectangle(85, 120);
        f.setArcWidth(18); f.setArcHeight(18);

        Color corFundo;
        Color corBorda;

        if (numero.matches("\\d+")) {
            int valor = Integer.parseInt(numero);
            corFundo = switch (valor) {
                case 1 -> Color.web("#FF5252");
                case 2 -> Color.web("#FF4081");
                case 3 -> Color.web("#E040FB");
                case 4 -> Color.web("#7C4DFF");
                case 5 -> Color.web("#536DFE");
                case 6 -> Color.web("#448AFF");
                case 7 -> Color.web("#40C4FF");
                case 8 -> Color.web("#18FFFF");
                case 9 -> Color.web("#64FFDA");
                case 10 -> Color.web("#69F0AE");
                case 11 -> Color.web("#B2FF59");
                case 12 -> Color.web("#EEFF41");
                default -> Color.rgb(230, 210, 175);
            };
            corBorda = corFundo.darker();
        } else {
            if (numero.toLowerCase().contains("freeze")) {
                corFundo = Color.LIGHTBLUE;
                corBorda = Color.DARKBLUE;
            } else if (numero.toLowerCase().contains("x2")) {
                corFundo = Color.GOLD;
                corBorda = Color.DARKORANGE;
            } else {
                corFundo = Color.rgb(230, 210, 175);
                corBorda = Color.rgb(130, 75, 160);
            }
        }

        f.setFill(corFundo);
        f.setStroke(corBorda);
        f.setStrokeWidth(3);

        boolean isNum = numero.matches("\\d+");
        String estilo = "-fx-font-weight: bold; -fx-font-size: " + (isNum ? "42px" : "18px") + ";";

        Text tNum = new Text(numero);
        tNum.setStyle(estilo);
        tNum.setFill(isNum ? Color.WHITE : corBorda.darker());

        getChildren().addAll(f, tNum);
        setAlignment(Pos.CENTER);
    }
}