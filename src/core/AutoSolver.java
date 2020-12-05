package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoSolver {

    private final List<Group> listOfGroups = new ArrayList<>();
    private final GameBoard board;

    public AutoSolver(GameBoard board) {
        this.board = board;
    }

    public ConditionOfGame start() {
        boolean isWorking = true;
        boolean isChanged = false;
        GameCell[][] arrayOfCells = board.getArrayOfCells();
        ConditionOfGame lastCondition = board.openCell(arrayOfCells[0][0]);
        if (lastCondition == ConditionOfGame.LOSE) {
            return lastCondition;
        }
        while (isWorking) {
            for (int i = 0; i < board.getSizeX(); i++) {
                for (int j = 0; j < board.getSizeY(); j++) {
                    if (arrayOfCells[i][j].getConditionOfCell()) {
                        Group group = new Group(new ArrayList<>(), arrayOfCells[i][j].getNearMines());
                        for (int dx = -1; dx <= 1; dx++) {
                            for (int dy = -1; dy <= 1; dy++) {
                                if (i + dx >= 0 && j + dy >= 0) {
                                    if (!arrayOfCells[i + dx][j + dy].getConditionOfCell()) {
                                        group.getCells().add(arrayOfCells[i + dx][j + dy]);
                                    }
                                }
                            }
                        }
                        if (!group.getCells().isEmpty() && group.getBombs() != 0) {
                            listOfGroups.add(0, group);
                            for (int k = 0; k < listOfGroups.size(); k++) {
                                for (int n = k + 1; n < listOfGroups.size(); n++) {
                                    Group first = listOfGroups.get(k);
                                    Group second = listOfGroups.get(n);
                                    int sizeOfFirst = first.getCells().size();
                                    int sizeOfSecond = second.getCells().size();
                                    if (sizeOfFirst == sizeOfSecond && first.equalsInCells(second)) {
                                        listOfGroups.remove(second);
                                        break;
                                    } else if (sizeOfFirst > sizeOfSecond) {
                                        if (first.remove(second)) break;
                                        /*else if (first.cross(second) != null) {
                                            listOfGroups.add(first.cross(second));
                                            break;
                                        }*/
                                    } else {
                                        if (second.remove(first)) break;
                                        /*else if (second.cross(first) != null) {
                                            listOfGroups.add(second.cross(first));
                                            break;
                                        }*/
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < listOfGroups.size(); i++) {
                Group element = listOfGroups.get(i);
                if (element.getBombs() == 0) {
                    for (GameCell cell : element.getCells()) {
                        lastCondition = board.openCell(cell);
                        if (lastCondition == ConditionOfGame.LOSE || lastCondition == ConditionOfGame.WIN) {
                            isWorking = false;
                            break;
                        }
                    }
                    listOfGroups.remove(element);
                    isChanged = true;
                } else if (element.getBombs() == element.getCells().size()) {
                    for (GameCell cell : element.getCells()) {
                        cell.setFlag(true);
                    }
                    listOfGroups.remove(element);
                    isChanged = true;
                }
            }
            if (!isChanged) {
                Random rnd = new Random();
                int x = rnd.nextInt(board.getSizeX());
                int y = rnd.nextInt(board.getSizeY());
                while (
                        x > board.getSizeX() - 1 || y > board.getSizeY() - 1 ||
                                board.getArrayOfCells()[x][y].getConditionOfCell()  ||
                                board.getArrayOfCells()[x][y].getFlag()

                ) {
                    x = rnd.nextInt(board.getSizeX());
                    y = rnd.nextInt(board.getSizeY());
                }
            }
        }
        return lastCondition;
    }
}
