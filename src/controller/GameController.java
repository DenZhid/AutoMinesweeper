package controller;

import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import core.GameBoard;
import javafx.scene.layout.GridPane;
import view.GameView;

import java.io.FileNotFoundException;

public class GameController {

    public GridPane gamePane;

    private GameBoard board;
    private GameView graphics;

    public void startGame() throws FileNotFoundException {
        board.createBoard();
        graphics = new GameView(board);
        setGraphicsOnPane(graphics.updateBoard());
    }

    public void mouseListener(MouseEvent e) {
        switch (e.getButton()) {
            case PRIMARY: {
                switch (board.openCell(cell)) {
                    case LOSE:
                    case END: {
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
                    case CONTINUE: {
                        graphics.updateBoard();
                        setGraphicsOnPane(graphics.updateBoard());
                        break;
                    }
                }
            }
            case SECONDARY: {
                if (cell.getFlag()) {
                    cell.setFlag(false);
                } else {
                    cell.setFlag(true);
                }
            }
        }
    }

    private void setGraphicsOnPane(ImageView[][] images) {
        gamePane.getChildren().clear();
        for (int y = 0; y < board.getSizeY(); y++) {
            for (int x = 0; x < board.getSizeX(); x++) {
                gamePane.add(images[x][y], x, y);
            }
        }
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }
}
