package core;

import org.junit.jupiter.api.Test;

import static core.ConditionOfGame.LOSE;
import static core.ConditionOfGame.WIN;
import static org.junit.jupiter.api.Assertions.*;

class AutoSolverTest {
    int sizeX;
    int sizeY;
    int numberOfBombs;
    GameBoard testBoard;
    AutoSolver solver;
    ConditionOfGame finalCondition;

    @Test
    void TestOfStart1() {
        sizeX = 9;
        sizeY = 9;
        numberOfBombs = 10;
        testBoard = new GameBoard(sizeX, sizeY, numberOfBombs);
        testBoard.createBoard();
        solver = new AutoSolver(testBoard);
        finalCondition = solver.start();
        assertEquals(WIN, finalCondition, "Бот совершил возможную ошибку");
    }

    @Test
    void TestOfStart2() {
        sizeX = 16;
        sizeY = 16;
        numberOfBombs = 40;
        testBoard = new GameBoard(sizeX, sizeY, numberOfBombs);
        testBoard.createBoard();
        solver = new AutoSolver(testBoard);
        finalCondition = solver.start();
        assertEquals(WIN, finalCondition, "Бот совершил возможную ошибку");
    }

    @Test
    void TestOfStart3() {
        sizeX = 30;
        sizeY = 16;
        numberOfBombs = 99;
        testBoard = new GameBoard(sizeX, sizeY, numberOfBombs);
        testBoard.createBoard();
        solver = new AutoSolver(testBoard);
        finalCondition = solver.start();
        assertEquals(WIN, finalCondition, "Бот совершил возможную ошибку");
    }
}