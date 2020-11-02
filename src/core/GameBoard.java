package core;

import java.util.Random;

public class GameBoard {

    private int sizeX;
    private int sizeY;
    private int numberOfBombs;
    private GameCell[][] arrayOfCells;
    private boolean end = false;

    public GameBoard(int sizeX, int sizeY, int numberOfBombs) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.numberOfBombs = numberOfBombs;
        arrayOfCells = new GameCell[sizeX][sizeY];
    }

    public void createBoard() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                arrayOfCells[i][j] = new GameCell(i, j);
            }
        }
        Random random = new Random();
        for (int i = 0; i < numberOfBombs; i++) {
            int x = random.nextInt(sizeX);
            int y = random.nextInt(sizeY);
            while (arrayOfCells[x][y].getMine()) {
                x = random.nextInt(sizeX);
                y = random.nextInt(sizeY);
            }
            arrayOfCells[x][y].setMine();
            arrayOfCells[x][y].setListener();
        }
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (arrayOfCells[i][j].getMine()) {
                    arrayOfCells[i][j].setNearMines(this.getNeighbours(i, j));
                }
            }
        }
    }

    public int getNeighbours(int x, int y) {
        int result = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (arrayOfCells[x + dx][y + dy].getMine()) {
                    result++;
                }
            }
        }
        return result;
    }

    public ConditionOfGame openCell(GameCell cell) {
        if (getEnd()) {
            return ConditionOfGame.END;
        } else if (cell.getMine()) {
            end = true;
            return ConditionOfGame.LOSE;
        } else if (!cell.getConditionOfCell() || !cell.getFlag()) {
            cell.setOpened();
        }
        return ConditionOfGame.CONTINUE;
    }

    public boolean getEnd() {
        return end;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public GameCell getGameCell(int x, int y) {
        return arrayOfCells[x][y];
    }
}
