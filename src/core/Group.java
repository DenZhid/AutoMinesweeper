package core;

import java.util.List;

public class Group {

    private List<GameCell> cellsOfGroup;
    private int numberOfBombs;

    public Group(List<GameCell> cellsOfGroup, int numberOfBombs) {
        this.cellsOfGroup = cellsOfGroup;
        this.numberOfBombs = numberOfBombs;
    }

    public boolean equalsInCells(Group group) {
        if (numberOfBombs != group.numberOfBombs) return false;
        for (int i = 0; i < cellsOfGroup.size(); i++) {
            GameCell first = cellsOfGroup.get(i);
            GameCell second = group.cellsOfGroup.get(i);
            if (!first.equals(second)) return false;
        }
        return true;
    }

    public boolean remove(Group group) {
        for (GameCell element: group.cellsOfGroup) {
            if (!cellsOfGroup.contains(element)) return false;
        }
        for (GameCell element: group.cellsOfGroup) {
            cellsOfGroup.remove(element);
        }
        numberOfBombs -= group.getBombs();
        return true;
    }

    public List<GameCell> getCells() {
        return cellsOfGroup;
    }

    public int getBombs() {
        return numberOfBombs;
    }

    public void setBombs(int num) {
        this.numberOfBombs = num;
    }

    public void setCellsOfGroup(List<GameCell> cells) {
        this.cellsOfGroup = cells;
    }
}