import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;


public class game {
    public Pane playgroundP;
    private boolean isCircle = true;
    private boolean isWin = false;
    private boolean isRunning = true;
    private int charsSetted = 0;
    static String winner;

    @FXML
    void initialize()
    {
        startGame();
    }
    private void startGame()
    {
        isCircle = true;
        isWin = false;
        isRunning = true;
        charsSetted = 0;

        Image O = new Image("/o.png");
        Image X = new Image("/x.png");
        playgroundP.setStyle("-fx-background-image: url('b.png')");
        playgroundP.setCursor(new ImageCursor(O));
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                Label l = new Label();
                l.setPrefHeight(100);
                l.setPrefWidth(100);
                l.setLayoutX(j * 100);
                l.setLayoutY(i * 100);
                l.setStyle("-fx-border-color: black");

                l.setOnMouseClicked(event -> {

                    System.out.println(playgroundP.getChildren().indexOf(l));

                    l.setAlignment(Pos.CENTER);
                    Font f = new Font("Arial", 80);
                    l.setFont(f);

                    if(l.getText().equals("") && isRunning)
                    {
                        if(isCircle)
                        {
                            l.setText("O");
                            isCircle = false;
                            playgroundP.setCursor(new ImageCursor(X));
                            charsSetted++;
                        }
                        else
                        {
                            l.setText("X");
                            isCircle = true;
                            playgroundP.setCursor(new ImageCursor(O));
                            charsSetted++;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(isCircle)checkWin(playgroundP.getChildren().indexOf(l), "X");
                        else checkWin(playgroundP.getChildren().indexOf(l), "O");
                        if(charsSetted == 9 && !isWin)
                        {
                            winner = "draw";
                        }
                    }
                });
                playgroundP.getChildren().add(l);
            }
        }
    }
    private void checkWin(int pos, String put)
    {
        int row = pos / 3;
        int column = pos % 3;

        if(row == 0 && ((Label)playgroundP.getChildren().get(0)).getText().equals(put) && ((Label)playgroundP.getChildren().get(1)).getText().equals(put) && ((Label)playgroundP.getChildren().get(2)).getText().equals(put))
            isWin = true;

        else if(row == 1 && ((Label)playgroundP.getChildren().get(3)).getText().equals(put) && ((Label)playgroundP.getChildren().get(4)).getText().equals(put) && ((Label)playgroundP.getChildren().get(5)).getText().equals(put))
            isWin = true;

        else if(row == 2 && ((Label)playgroundP.getChildren().get(6)).getText().equals(put) && ((Label)playgroundP.getChildren().get(7)).getText().equals(put) && ((Label)playgroundP.getChildren().get(8)).getText().equals(put))
            isWin = true;

        else if(column == 0 && ((Label)playgroundP.getChildren().get(0)).getText().equals(put) && ((Label)playgroundP.getChildren().get(3)).getText().equals(put) && ((Label)playgroundP.getChildren().get(6)).getText().equals(put))
            isWin = true;

        else if(column == 1 && ((Label)playgroundP.getChildren().get(1)).getText().equals(put) && ((Label)playgroundP.getChildren().get(4)).getText().equals(put) && ((Label)playgroundP.getChildren().get(7)).getText().equals(put))
            isWin = true;

        else if(column == 2 && ((Label)playgroundP.getChildren().get(2)).getText().equals(put) && ((Label)playgroundP.getChildren().get(5)).getText().equals(put) && ((Label)playgroundP.getChildren().get(8)).getText().equals(put))
            isWin = true;

        else if(((row == 0 && column == 0) || (row == 1 && column ==1) || (row == 2 && column ==2)) && ((Label)playgroundP.getChildren().get(0)).getText().equals(put) && ((Label)playgroundP.getChildren().get(4)).getText().equals(put) && ((Label)playgroundP.getChildren().get(8)).getText().equals(put))
            isWin = true;

        else if(((row == 0 && column == 2) || (row == 1 && column ==1) || (row == 2 && column ==0)) && ((Label)playgroundP.getChildren().get(2)).getText().equals(put) && ((Label)playgroundP.getChildren().get(4)).getText().equals(put) && ((Label)playgroundP.getChildren().get(6)).getText().equals(put))
            isWin = true;

        if(isWin){
            isRunning = false;
            playgroundP.setCursor(Cursor.DEFAULT);
            winner = put;
            endGame();
        }
    }
    private void endGame()
    {
        try {
            Parent endGameStage = FXMLLoader.load(getClass().getResource("endGame.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(endGameStage, 150, 100));
            stage.setResizable(false);
            stage.show();
            playgroundP.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
