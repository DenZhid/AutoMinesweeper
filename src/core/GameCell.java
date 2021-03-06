package core;

import java.util.Objects;

public class GameCell {

    private final int x;
    private final int y;
    private int nearMines;
    private boolean anyMine;
    private boolean isFlagged;
    private boolean isOpened = false;

    public GameCell (int x, int y) {
        this.x = x;
        this.y = y;
        this.nearMines = 0;
        this.anyMine = false;
        this.isFlagged = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNearMines() {
        return nearMines;
    }

    public boolean getMine() {
        return anyMine;
    }

    public boolean getFlag() {
        return isFlagged;
    }

    public boolean getConditionOfCell() {
        return isOpened;
    }

    public void setNearMines(int numberOfMines) {
        this.nearMines = numberOfMines;
    }

    public void setFlag(boolean condition) {
        this.isFlagged = condition;
    }

    public void setMine(boolean condition) {
        anyMine = condition;
    }

    public void setOpened() {
        isOpened = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCell gameCell = (GameCell) o;
        return x == gameCell.x &&
                y == gameCell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
