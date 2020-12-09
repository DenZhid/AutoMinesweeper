package core;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return numberOfBombs == group.numberOfBombs && Objects.equals(cellsOfGroup, group.cellsOfGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellsOfGroup, numberOfBombs);
    }
}