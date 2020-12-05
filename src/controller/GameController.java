package controller;

import javafx.scene.control.Alert;

import core.AutoSolver;
import core.GameBoard;


public class GameController {

    private GameBoard board;

    public void startGame() {
        board.createBoard();
        AutoSolver solver = new AutoSolver(board);
        switch (solver.start()) {
            case LOSE: {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("AutoMinesweeper");
                alert.setHeaderText("В результате решения бот допустил ошибку");
                alert.show();
                break;
            }
            case WIN: {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("AutoMinesweeper");
                alert.setHeaderText("Решение удалось");
                alert.show();
                break;
            }
        }
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }
}
