package view;

import core.GameBoard;

//import static controller.ControllerOfMainScreen.WIDTH;
//import static controller.ControllerOfMainScreen.HEIGHT;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.EnumMap;

public class GameView {

    private ImageView[][] arrayOfImages;
    private EnumMap<ViewSegments, Image> mapOfImages;
    private final GameBoard forGraphics;

    private enum ViewSegments {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        CLOSED
    }

    public GameView(GameBoard board) throws FileNotFoundException {
        this.forGraphics = board;
        arrayOfImages = new ImageView[board.getSizeX()][board.getSizeY()];
        importImages();
        for (int y = 0; y < board.getSizeY(); y++) {
            for (int x = 0; x < board.getSizeX(); x++) {
                ImageView newImage = new ImageView();
                newImage.setImage(mapOfImages.get(ViewSegments.CLOSED));
                //newImage.setFitWidth(WIDTH/game.getSizeX());
                //newImage.setFitHeight(HEIGHT/game.getSizeY());
                arrayOfImages[x][y] = newImage;
            }
        }
    }

    public void importImages() throws FileNotFoundException {
        mapOfImages = new EnumMap<>(ViewSegments.class);
        mapOfImages.put(ViewSegments.CLOSED, new Image(new FileInputStream("images/Empty.png")));
        mapOfImages.put(ViewSegments.ZERO, new Image(new FileInputStream("images/SnakeHead.png")));
        mapOfImages.put(ViewSegments.ONE, new Image(new FileInputStream("images/SnakeBody.png")));
        mapOfImages.put(ViewSegments.TWO, new Image(new FileInputStream("images/Fruit.png")));
        mapOfImages.put(ViewSegments.THREE, new Image(new FileInputStream("images/EnemySnakeHead.png")));
        mapOfImages.put(ViewSegments.FOUR, new Image(new FileInputStream("images/EnemySnakeBody.png")));
        mapOfImages.put(ViewSegments.FIVE, new Image(new FileInputStream("images/EnemySnakeBody.png")));
        mapOfImages.put(ViewSegments.SIX, new Image(new FileInputStream("images/EnemySnakeBody.png")));
        mapOfImages.put(ViewSegments.SEVEN, new Image(new FileInputStream("images/EnemySnakeBody.png")));
        mapOfImages.put(ViewSegments.EIGHT, new Image(new FileInputStream("images/EnemySnakeBody.png")));
    }

    public ImageView[][] updateBoard() {
        for (int y = 0; y < forGraphics.getSizeY(); y++) {
            for (int x = 0; x < forGraphics.getSizeX(); x++) {
                if (forGraphics.getGameCell(x, y).getConditionOfCell()) {
                    switch (forGraphics.getGameCell(x, y).getNearMines()) {
                        case 0: {
                            arrayOfImages[x][y].setImage((mapOfImages.get(ViewSegments.ZERO)));
                            break;
                        }
                        case 1: {
                            arrayOfImages[x][y].setImage((mapOfImages.get(ViewSegments.ONE)));
                            break;
                        }
                        case 2: {
                            arrayOfImages[x][y].setImage((mapOfImages.get(ViewSegments.TWO)));
                            break;
                        }
                        case 3: {
                            arrayOfImages[x][y].setImage((mapOfImages.get(ViewSegments.THREE)));
                            break;
                        }
                        case 4: {
                            arrayOfImages[x][y].setImage((mapOfImages.get(ViewSegments.FOUR)));
                            break;
                        }
                        case 5: {
                            arrayOfImages[x][y].setImage((mapOfImages.get(ViewSegments.FIVE)));
                            break;
                        }
                        case 6: {
                            arrayOfImages[x][y].setImage((mapOfImages.get(ViewSegments.SIX)));
                            break;
                        }
                        case 7: {
                            arrayOfImages[x][y].setImage((mapOfImages.get(ViewSegments.SEVEN)));
                            break;
                        }
                        case 8: {
                            arrayOfImages[x][y].setImage((mapOfImages.get(ViewSegments.EIGHT)));
                            break;
                        }
                    }
                } else {
                    arrayOfImages[x][y].setImage((mapOfImages.get(ViewSegments.CLOSED)));
                }
            }
        }
        return arrayOfImages;
    }

}
