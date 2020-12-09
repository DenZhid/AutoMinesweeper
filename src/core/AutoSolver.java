package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static core.ConditionOfGame.*;

public class AutoSolver {

    private boolean isChanged;
    private boolean changedAgain;
    private final List<Group> listOfGroups = new ArrayList<>();
    private final GameBoard board;
    private ConditionOfGame lastCondition;

    public AutoSolver(GameBoard board) {
        this.board = board;
    }

    public ConditionOfGame start() {
        GameCell[][] arrayOfCells = board.getArrayOfCells();
        int sizeX = board.getSizeX();
        int sizeY = board.getSizeY();
        lastCondition = board.openCell(arrayOfCells[0][0]);
        if (lastCondition == LOSE || lastCondition == WIN) {
            return lastCondition;
        }
        while (true) {
            isChanged = false;
            changedAgain = false;
            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    if (arrayOfCells[i][j].getConditionOfCell()) {
                        Group group = new Group(new ArrayList<>(), arrayOfCells[i][j].getNearMines());
                        List<GameCell> cells = group.getCells();
                        for (int dx = -1; dx <= 1; dx++) {
                            int x = i + dx;
                            for (int dy = -1; dy <= 1; dy++) {
                                int y = j + dy;
                                if (
                                        x >= 0 && y >= 0 && x <= (sizeX - 1) && y <= (sizeY - 1) &&
                                        !arrayOfCells[x][y].getConditionOfCell()
                                ) cells.add(arrayOfCells[x][y]);
                            }
                        }
                        if (!cells.isEmpty() && group.getBombs() != 0) {
                            changedAgain = true;
                            listOfGroups.add(0, group);
                            for (int k = 0; k < listOfGroups.size(); k++) {
                                for (int n = k + 1; n < listOfGroups.size(); n++) {
                                    Group first = listOfGroups.get(k);
                                    Group second = listOfGroups.get(n);
                                    int sizeOfFirst = first.getCells().size();
                                    int sizeOfSecond = second.getCells().size();
                                    if (sizeOfFirst == sizeOfSecond && first.equalsInCells(second))
                                        listOfGroups.remove(second);
                                     else if (sizeOfFirst > sizeOfSecond) {
                                        if (first.remove(second)) k = 0;
                                    } else if (sizeOfFirst < sizeOfSecond){
                                        if (second.remove(first)) k = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            while (changedAgain) {
                restructure();
                if (lastCondition == LOSE || lastCondition == WIN) return lastCondition;
            }
            if (!isChanged) {
                Random rnd = new Random();
                int x = rnd.nextInt(sizeX);
                int y = rnd.nextInt(sizeY);
                while (arrayOfCells[x][y].getConditionOfCell() || arrayOfCells[x][y].getFlag()) {
                    x = rnd.nextInt(sizeX);
                    y = rnd.nextInt(sizeY);
                }
                lastCondition = board.openCell(arrayOfCells[x][y]);
                changedAgain = true;
                if (lastCondition == ConditionOfGame.LOSE || lastCondition == ConditionOfGame.WIN) return lastCondition;
                while (changedAgain) {
                    restructure();
                    if (lastCondition == LOSE || lastCondition == WIN) return lastCondition;
                }
            }
        }
    }

    private void restructure() {
        changedAgain = false;
        List<Group> groupsToRemove = new ArrayList<>();
        for (Group element : listOfGroups) {
            List<GameCell> cells = element.getCells();
            int numberOfBombs = element.getBombs();
            if (numberOfBombs == 0) {
                for (GameCell cell : cells) {
                    lastCondition = board.openCell(cell);
                    if (lastCondition == LOSE || lastCondition == WIN) {
                        return;
                    }
                }
                groupsToRemove.add(element);
                isChanged = true;
                changedAgain = true;
            } else if (numberOfBombs == cells.size()) {
                for (GameCell cell : cells) {
                    if (!cell.getFlag()) {
                        cell.setFlag(true);
                        isChanged = true;
                        changedAgain = true;
                    }
                }
            }
        }
        for (Group element: groupsToRemove) listOfGroups.remove(element);
        for (Group element: listOfGroups) {
            List<GameCell> cellsToRemove = new ArrayList<>();
            List<GameCell> cells = element.getCells();
            int numberOfBombs = element.getBombs();
            for (GameCell cell: cells) {
                if (cell.getConditionOfCell()) {
                    cellsToRemove.add(cell);
                } else if (cell.getFlag() && cells.size() > numberOfBombs) {
                    cellsToRemove.add(cell);
                    element.setBombs(numberOfBombs - 1);
                    numberOfBombs = element.getBombs();
                }
            }
            for (GameCell cell: cellsToRemove) cells.remove(cell);
        }
    }
}
