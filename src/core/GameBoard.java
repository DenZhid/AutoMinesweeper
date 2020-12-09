package core;

import java.util.Random;

import static core.ConditionOfGame.*;

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
        for (int i = 0; i < numberOfBombs; i++) {
            int x = getRandomX(sizeX);
            int y = getRandomY(sizeY);
            while (arrayOfCells[x][y].getMine()) {
                x = getRandomX(sizeX);
                y = getRandomY(sizeY);
            }
            arrayOfCells[x][y].setMine(true);
        }
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (arrayOfCells[i][j].getMine()) {
                    for (int di = -1; di <= 1; di++) {
                        int x = i + di;
                        for (int dj = -1; dj <= 1; dj++) {
                            int y = j + dj;
                            if (checkBorder(x,y))
                            arrayOfCells[x][y].setNearMines(arrayOfCells[x][y].getNearMines() + 1);
                        }
                    }
                }
            }
        }
    }

    public ConditionOfGame openCell(GameCell cell) {
        if (getEnd()) return END;
        else if (cell.getMine()) {
            end = true;
            return LOSE;
        } else if (!cell.getConditionOfCell() && !cell.getFlag()) {
            cell.setOpened();
            remainder--;
            if (cell.getNearMines() == 0) {
                for (int dx = -1; dx <= 1; dx++) {
                    int x = cell.getX() + dx;
                    for (int dy = -1; dy <= 1; dy++) {
                        int y = cell.getY() + dy;
                        if (checkBorder(x, y) && !arrayOfCells[x][y].getMine()) this.openCell(arrayOfCells[x][y]);
                    }
                }
            }
            if (remainder == 0) {
                end = true;
                return WIN;
            }
        }
        return CONTINUE;
    }

    public int getRandomX(int bound) {
        Random rnd = new Random();
        return rnd.nextInt(bound);
    }

    public int getRandomY(int bound) {
        Random rnd = new Random();
        return rnd.nextInt(bound);
    } //Две функции необходимы для упрощения процесса тестирования

    private boolean checkBorder(int x, int y) {
        return x >= 0 && y >= 0 && x <= (sizeX - 1) &&  y <= (sizeY - 1);
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

    public GameCell[][] getArrayOfCells() {
        return arrayOfCells;
    }
}
