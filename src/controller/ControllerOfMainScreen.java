package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import core.GameBoard;

public class ControllerOfMainScreen {

    int numberOfBombs = 10;
    int sizeOfField  = 10;
    //public static final double WIDTH = 600;
    //public static final double HEIGHT = 400;

    private Stage stage;

    @FXML
    public void startWork() throws Exception {
        GameBoard gameBoard = new GameBoard(sizeOfField, sizeOfField, numberOfBombs);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScreen.fxml"));
        Parent root = loader.load();
        GameController gameController = loader.getController();
        gameController.setBoard(gameBoard);
        gameController.startGame();

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("AutoMinesweeper");
        stage.setScene(scene);
    }

    public void setStage (Stage stage) {
        this.stage = stage;
    }
}
