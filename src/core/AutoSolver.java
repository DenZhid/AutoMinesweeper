package core;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;

public class AutoSolver {

    private List<Group> listOfGroups;
    private GameBoard board;

    public AutoSolver(List<Group> listOfGroups, GameBoard board) {
        this.listOfGroups = listOfGroups;
        this.board = board;
    }

    public void start() {
        GameCell[][] arrayOfCells = board.getArrayOfCells();
        board.openCell(arrayOfCells[0][0]);
        for (int i = 0; i < board.getSizeX(); i++) {
            for (int j = 0; j < board.getSizeY(); j++) {
                if (arrayOfCells[i][j].getConditionOfCell()) {
                    Group group = new Group(new ArrayList<>(), arrayOfCells[i][j].getNearMines());
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            if (i + dx >= 0 && j + dy >= 0) {
                                if (!arrayOfCells[i + dx][j + dy].getConditionOfCell()) {
                                    group.getCells().add(arrayOfCells[i + dx][j + dy]);
                                } //Хорошая идея: Начинать сравнение группы в момент, когда мы её уже создаём
                            }
                        }
                    }
                    if (!group.getCells().isEmpty()) {
                        listOfGroups.add(group);
                    }
                }
            }
        }
        for (int i = 0; i < listOfGroups.size(); i++) {
            for (int j = i + 1; j < listOfGroups.size(); j++) {
                Group first = listOfGroups.get(i);
                Group second = listOfGroups.get(j);
                int sizeOfFirst = first.getCells().size();
                int sizeOfSecond = second.getCells().size();
                if (sizeOfFirst == sizeOfSecond && first.equalsInCells(second)) {
                    listOfGroups.remove(j);
                    break; //Возможна ошибка
                }
                else if (sizeOfFirst > sizeOfSecond) {
                    if (first.remove(second)) break;
                    else if (first.cross(second) != null) {

                        break;
                    }
                }
                else {
                    if (second.remove(first)) break;
                    else if (second.cross(first) != null) {

                        break;
                    }
                }
            }
        }
    }

}
