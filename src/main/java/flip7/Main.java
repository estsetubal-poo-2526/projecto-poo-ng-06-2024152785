package flip7;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class Main extends Application {

    private StackPane root;
    private Image imagemFundo;

    private boolean somLigado = true;
    private double volume = 50;

    private int numJogadores = 2;

    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) {
        root = new StackPane();

        imagemFundo = new Image(
                getClass().getResource("/images/bckg.jpeg").toExternalForm()
        );

        aplicarFundo();
        mostrarEcraInicial();

        Scene scene = new Scene(root, 900, 550);

        stage.setTitle("Vira7");
        stage.setScene(scene);
        stage.show();

        iniciarMusica(); // Adiciona esta linha
        aplicarFundo();
        mostrarEcraInicial();
    }

    private void aplicarFundo() {
        BackgroundImage fundo = new BackgroundImage(
                imagemFundo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );

        root.setBackground(new Background(fundo));
    }

    private void iniciarMusica() {
        try {
            URL resource = getClass().getResource("chill.mp3");
            if (resource != null) {
                Media media = new Media(resource.toExternalForm());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Toca em loop
                mediaPlayer.setVolume(volume / 100.0);
                if (somLigado) {
                    mediaPlayer.play();
                }
            } else {
                System.err.println("Ficheiro chill.mp3 não encontrado no pacote flip7!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarEcraInicial() {
        root.getChildren().clear();

        Image imagemLogo = new Image(
                getClass().getResource("/images/vira7.png").toExternalForm()
        );

        ImageView logoView = new ImageView(imagemLogo);
        logoView.setFitWidth(500);
        logoView.setPreserveRatio(true);

        Button botaoLogo = new Button();
        botaoLogo.setGraphic(logoView);
        botaoLogo.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-padding: 0;" +
                        "-fx-cursor: hand;"
        );

        botaoLogo.setOnMouseEntered(event -> {
            botaoLogo.setScaleX(0.92);
            botaoLogo.setScaleY(0.92);
        });

        botaoLogo.setOnMouseExited(event -> {
            botaoLogo.setScaleX(1.0);
            botaoLogo.setScaleY(1.0);
        });

        botaoLogo.setOnAction(event -> mostrarMenuPrincipal());

        root.getChildren().add(botaoLogo);
    }

    private void mostrarMenuPrincipal() {
        root.getChildren().clear();

        Button botaoJogar = new Button("Jogar");
        Button botaoDefinicoes = new Button("Definições");

        botaoJogar.setPrefWidth(240);
        botaoDefinicoes.setPrefWidth(240);

        botaoJogar.setStyle(estiloBotaoMenu());
        botaoDefinicoes.setStyle(estiloBotaoMenu());

        botaoJogar.setOnAction(event -> mostrarJogo());
        botaoDefinicoes.setOnAction(event -> mostrarDefinicoes());

        VBox menu = new VBox(25);
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(botaoJogar, botaoDefinicoes);

        root.getChildren().add(menu);
    }

    private void mostrarJogo() {
        root.getChildren().clear();
        JogoView jogoView = new JogoView(numJogadores,
                () -> mostrarMenuPrincipal(),
                v -> mostrarVencedor(v));
        root.getChildren().add(jogoView);
    }

    private void mostrarVencedor(int vencedor) {
        root.getChildren().clear();
        VictoryView vv = new VictoryView(vencedor,
                () -> mostrarMenuPrincipal(),
                () -> mostrarJogo());
        root.getChildren().add(vv);
    }

    private void mostrarDefinicoes() {
        root.getChildren().clear();

        DefinicoesView definicoesView = new DefinicoesView(
                () -> {
                    DefinicoesView viewAtual = (DefinicoesView) root.getChildren().get(0);
                    somLigado = viewAtual.isSomLigado();
                    volume = viewAtual.getVolume();
                    numJogadores = viewAtual.getNumJogadores();
                    mostrarMenuPrincipal();
                },
                somLigado,
                volume,
                numJogadores
        );

        if (mediaPlayer != null) {
            definicoesView.getSliderVolume().valueProperty().addListener((obs, velho, novo) -> {
                volume = novo.doubleValue();
                mediaPlayer.setVolume(volume / 100.0);
            });

            definicoesView.getCheckSom().selectedProperty().addListener((obs, velho, novo) -> {
                somLigado = novo;
                if (somLigado) {
                    mediaPlayer.play();
                } else {
                    mediaPlayer.pause();
                }
            });
        }

        root.getChildren().add(definicoesView);
    }


    private String estiloBotaoMenu() {
        return "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: rgba(255, 255, 255, 0.85);" +
                "-fx-text-fill: black;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 12 30 12 30;" +
                "-fx-cursor: hand;";
    }

    public static void main(String[] args) {
        launch(args);
    }
}