import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class endGame {
    @FXML
    public Label scoreL;

    @FXML
    void initialize()
    {
        scoreL.setAlignment(Pos.CENTER);
        switch (game.winner) {
            case "X":
                scoreL.setText("X wygrał!");
                break;
            case "O":
                scoreL.setText("O wygrało!");
                break;
            default:
                scoreL.setText("Remis!");
                break;
        }
    }

    public void onNewGame() {
        try {
            Parent endGameStage = FXMLLoader.load(getClass().getResource("game.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(endGameStage, 300, 300));
            stage.setResizable(false);
            stage.show();
            scoreL.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onQuit() {
        Platform.exit();
    }
}
