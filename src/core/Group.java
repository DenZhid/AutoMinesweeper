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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(cellsOfGroup, group.cellsOfGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellsOfGroup, numberOfBombs);
    }
}