package core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;

import java.util.ArrayList;
import java.util.List;

import static core.ConditionOfGame.*;

class GameBoardTest {

    @Test
    void createBoard() {
        GameBoard testBoard = Mockito.spy(new GameBoard(5, 5, 5));
        List<Integer> listOfX = List.of(0, 1, 3, 4, 4);
        List<Integer> listOfY = List.of(3, 2, 4, 1, 3);
        Mockito.when(testBoard.getRandomX(testBoard.getSizeX())).then(new ReturnsElementsOf(listOfX));
        Mockito.when(testBoard.getRandomY(testBoard.getSizeY())).then(new ReturnsElementsOf(listOfY));
        testBoard.createBoard();
        List<Integer> testList = new ArrayList<>();
        GameCell[][] arrayOfCells = testBoard.getArrayOfCells();
        for (int x = 0; x < testBoard.getSizeX(); x++) {
            for (int y = 0; y < testBoard.getSizeY(); y++) {
                testList.add(arrayOfCells[x][y].getNearMines());
            }
        }
        assertEquals(
                List.of(0, 1, 2, 2, 1, 0, 1, 2, 2, 1, 0, 1, 1, 2, 1, 1, 1, 2, 2, 2, 1, 1, 2, 2, 2),
                testList
        );
        testBoard = Mockito.spy(new GameBoard(0,0,0));
        testBoard.createBoard();
        assertEquals(0, testBoard.getSizeX());
        assertEquals(0, testBoard.getSizeY());
    }

    @Test
    void openCell() {
        GameBoard testBoard = new GameBoard(2 , 2, 1);
        testBoard.createBoard();
        GameCell[][] arrayOfCells = testBoard.getArrayOfCells();
        List<Integer> values = List.of(1, 1, 1, 0);
        int count = 0;
        for (int x = 0; x < testBoard.getSizeX(); x++) {
            for (int y = 0; y < testBoard.getSizeY(); y++) {
                arrayOfCells[x][y].setNearMines(values.get(count));
                arrayOfCells[x][y].setMine(false);
            }
        }
        arrayOfCells[1][1].setMine(true);
        assertEquals(LOSE, testBoard.openCell(arrayOfCells[1][1]));
        assertEquals(END, testBoard.openCell(arrayOfCells[0][0]));

        testBoard = new GameBoard(2 , 2, 1);
        testBoard.createBoard();
        arrayOfCells = testBoard.getArrayOfCells();
        values = List.of(1, 1, 1, 0);
        count = 0;
        for (int x = 0; x < testBoard.getSizeX(); x++) {
            for (int y = 0; y < testBoard.getSizeY(); y++) {
                arrayOfCells[x][y].setNearMines(values.get(count));
                arrayOfCells[x][y].setMine(false);
            }
        }
        arrayOfCells[1][1].setMine(true);
        assertEquals(CONTINUE, testBoard.openCell(arrayOfCells[0][0]));
        testBoard.openCell(arrayOfCells[0][1]);
        assertEquals(WIN, testBoard.openCell(arrayOfCells[1][0]));
        assertEquals(END, testBoard.openCell(arrayOfCells[1][1]));
    }
}