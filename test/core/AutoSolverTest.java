package core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static core.ConditionOfGame.WIN;

class AutoSolverTest {

    @Test
    void start() {
        for (int i = 0; i < 100; i++) {
            int sizeX = 5;
            int sizeY = 5;
            GameBoard testBoard = new GameBoard(sizeX, sizeY, 5);
            testBoard.createBoard();
            GameCell[][] testArray = testBoard.getArrayOfCells();
            List<Integer> cells = List.of(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 2, 3, 2, 0, 1, 3, 5, 4, 0, 1, 2, 4, 3);
            int count = 0;
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    testArray[x][y].setMine(false);
                    testArray[x][y].setNearMines(cells.get(count));
                    count++;
                }
            }
            testArray[2][3].setMine(true);
            testArray[3][2].setMine(true);
            testArray[3][4].setMine(true);
            testArray[4][3].setMine(true);
            testArray[4][4].setMine(true);
            AutoSolver solver = new AutoSolver(testBoard);
            assertEquals(WIN, solver.start());
        }
    }
}