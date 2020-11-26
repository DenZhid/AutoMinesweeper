package core;

import java.util.List;

public class Group {

    private List<GameCell> cellsOfGroup;
    private int numberOfBombs;

    public Group(List<GameCell> cellsOfGroup, int numberOfBombs) {
        this.cellsOfGroup = cellsOfGroup;
        this.numberOfBombs = numberOfBombs;
    }

    public List<GameCell> getCells() {
        return cellsOfGroup;
    }

    public int getDependentCell() {
        return numberOfBombs;
    }

    public void setCells(List<GameCell> cellsOfGroup) {
        this.cellsOfGroup = cellsOfGroup;
    }

    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    public boolean areEquals(Group group) {
        List<GameCell> other = group.getCells();
        if (other.size() == cellsOfGroup.size()) {
            for (int i = 0; i < cellsOfGroup.size(); i++) {
                GameCell first = cellsOfGroup.get(i);
                GameCell second = other.get(i);
                if (first.getX() != second.getX() || first.getY() != second.getY()) return false;
            }
        } else return false;
        return true;
    }

    public boolean containsAndRemove(Group group) {

    }
}