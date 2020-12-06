package core;

import java.util.Random;

public class GameBoard {

    private final int sizeX;
    private final int sizeY;
    private final int numberOfBombs;
    private int remainder;
    private final GameCell[][] arrayOfCells;
    private boolean end = false;

    public GameBoard(int sizeX, int sizeY, int numberOfBombs) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.numberOfBombs = numberOfBombs;
        remainder = sizeX * sizeY - numberOfBombs;
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
        }
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (arrayOfCells[i][j].getMine()) {
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if (i + di >= 0 && j + dj >= 0 && i + di <= (sizeX - 1) && j + dj <= (sizeY - 1))
                            arrayOfCells[i + di][j + dj].setNearMines(arrayOfCells[i + di][j +dj].getNearMines() + 1);
                        }
                    }
                }
            }
        }
    }

    public ConditionOfGame openCell(GameCell cell) {
        if (getEnd()) {
            return ConditionOfGame.END;
        } else if (cell.getMine()) {
            end = true;
            return ConditionOfGame.LOSE;
        } else if (!cell.getConditionOfCell() && !cell.getFlag()) {
            cell.setOpened();
            remainder--;
            if (cell.getNearMines() == 0) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (
                                cell.getX() + dx >= 0 && cell.getY() + dy >= 0 && cell.getX() + dx <= (sizeX - 1) && cell.getY() + dy <= (sizeY - 1) &&
                                !arrayOfCells[cell.getX() + dx][cell.getY() + dy].getMine()
                        ) this.openCell(arrayOfCells[cell.getX() + dx][cell.getY() + dy]);
                    }
                }
            }
            if (remainder == 0) return ConditionOfGame.WIN;
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

    public GameCell[][] getArrayOfCells() {
        return arrayOfCells;
    }
}
