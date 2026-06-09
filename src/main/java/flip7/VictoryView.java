package flip7;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VictoryView extends VBox {
    public VictoryView(int vencedor, Runnable aoVoltar, Runnable aoReiniciar) {
        setAlignment(Pos.CENTER);
        setSpacing(30);
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.85);");

        Text titulo = new Text("VITÓRIA!");
        titulo.setStyle("-fx-font-size: 70px; -fx-font-weight: bold; -fx-fill: #FFD700;");

        Text subTitulo = new Text("O Jogador " + vencedor + " venceu!");
        subTitulo.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-fill: white;");

        Button botaoReiniciar = new Button("Jogar Novamente");
        botaoReiniciar.setPrefWidth(280);
        botaoReiniciar.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 20; -fx-cursor: hand;");
        botaoReiniciar.setOnAction(event -> aoReiniciar.run());

        Button botaoVoltar = new Button("Voltar ao Menu");
        botaoVoltar.setPrefWidth(280);
        botaoVoltar.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-background-color: white; -fx-text-fill: black; -fx-background-radius: 20; -fx-cursor: hand;");
        botaoVoltar.setOnAction(event -> aoVoltar.run());

        getChildren().addAll(titulo, subTitulo, botaoReiniciar, botaoVoltar);
    }
}