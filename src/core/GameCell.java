package core;

public class GameCell {

    private int x;
    private int y;
    private int nearMines;
    private boolean anyMine;
    private boolean isFlagged;
    private boolean isOpened = false;
    private ListenerInterface listener;

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

    public void setMine() {
        anyMine = true;
    }

    public void setOpened() {
        isOpened = true;
    }

    public void setListener(ListenerInterface listener) {
        this.listener = listener;
    }
}
