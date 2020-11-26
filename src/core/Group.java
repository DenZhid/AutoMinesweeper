package core;

import java.util.ArrayList;
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

    public int getBombs() {
        return numberOfBombs;
    }

    public void setCells(List<GameCell> cellsOfGroup) {
        this.cellsOfGroup = cellsOfGroup;
    }

    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    public boolean equalsInCells(Group group) {
        List<GameCell> other = group.getCells();
        for (int i = 0; i < cellsOfGroup.size(); i++) {//Возможно стоит ввести size
            GameCell first = cellsOfGroup.get(i);
            GameCell second = other.get(i);
            if (first.getX() != second.getX() || first.getY() != second.getY()) return false;
        }
        return true;
    }

    public boolean remove(Group group) {
        List<GameCell> other = group.getCells();
        for (GameCell element: other) {
            if (!cellsOfGroup.contains(element)) return false;
        }
        for (GameCell element: other) {
            cellsOfGroup.remove(element);//Ошибка в remove
        }
        return true;
    }

    public List<Group> cross(Group group) {
        List<GameCell> other = group.getCells();
        List<GameCell> cellsForNewGroup = new ArrayList<>();
        List<Group> result = new ArrayList<>();
        for (GameCell element: other) {
            if (cellsOfGroup.contains(element)) cellsForNewGroup.add(element);
        }
        if (cellsForNewGroup.isEmpty()) return null;
        int bombsInNewGroup = Math.max(numberOfBombs, group.getBombs()) - (other.size() - cellsForNewGroup.size());
        if (bombsInNewGroup != Math.min(numberOfBombs, group.getBombs())) {
            result.add(this);
            result.add(group);
            result.add(new Group(cellsForNewGroup, bombsInNewGroup));
            return result;
        } //Возможна ошибка
        for (GameCell element: cellsForNewGroup) {
            if (cellsOfGroup.contains(element)) {}
        }
    }
}