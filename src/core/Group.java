package core;

import java.util.List;

public class Group {

    private final List<GameCell> cellsOfGroup;
    private int numberOfBombs;

    public Group(List<GameCell> cellsOfGroup, int numberOfBombs) { //сделать HashSet можно
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
        this.subtract(group);
        return true;
    }

    private void subtract(Group group) {
        for (GameCell element: group.getCells()) {
            cellsOfGroup.remove(element);
        }
        numberOfBombs -= group.getBombs();
    }

    public List<GameCell> getCells() {
        return cellsOfGroup;
    }

    public int getBombs() {
        return numberOfBombs;
    }

    /*public Group cross(Group group) {
        List<GameCell> other = group.getCells();
        List<GameCell> cellsForNewGroup = new ArrayList<>();
        for (GameCell element: other) {
            if (cellsOfGroup.contains(element)) cellsForNewGroup.add(element);
        }
        if (cellsForNewGroup.isEmpty()) return null;
        Group newGroup = new Group(
                cellsForNewGroup,
                Math.max(numberOfBombs, group.getBombs()) - (other.size() - cellsForNewGroup.size())
        );
        if (newGroup.getBombs() != Math.min(numberOfBombs, group.getBombs())) {
            return newGroup;
        }
        this.subtract(newGroup);
        group.subtract(newGroup);
        return newGroup;//Подумать
    }*/
}