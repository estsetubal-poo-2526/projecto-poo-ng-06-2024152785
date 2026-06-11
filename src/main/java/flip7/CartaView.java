package flip7;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CartaView extends StackPane {
    public CartaView(String numero) {
        setPrefSize(85, 120);

        Rectangle f = new Rectangle(85, 120);
        f.setArcWidth(18);
        f.setArcHeight(18);

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
            String label = numero.toLowerCase();
            if (label.contains("freeze")) {
                corFundo = Color.DEEPSKYBLUE;
                corBorda = Color.WHITE;
            } else if (label.contains("x2")) {
                corFundo = Color.GOLD;
                corBorda = Color.DARKORANGE;
            } else {
                corFundo = Color.PURPLE;
                corBorda = Color.WHITE;
            }
        }

        f.setFill(corFundo);
        f.setStroke(corBorda);
        f.setStrokeWidth(3);

        boolean isNum = numero.matches("\\d+");
        String estilo = "-fx-font-weight: bold; -fx-font-size: " + (isNum ? "42px" : "16px") + ";";

        Text tNum = new Text(numero.toUpperCase());
        tNum.setStyle(estilo);
        tNum.setFill(isNum ? Color.WHITE : Color.BLACK);

        this.getChildren().clear();
        this.getChildren().addAll(f, tNum);
        this.setAlignment(Pos.CENTER);
    }
}