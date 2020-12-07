package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GameBoardTest {

    @Test
    void createBoard() {
        GameBoard testBoard = Mockito.spy(new GameBoard(5, 5, 5));
        List<Integer> listOfX = List.of(0, 1, 3, 4, 4);
        List<Integer> listOfY = List.of(3, 2, 4, 1, 3);
        //Mockito.when(random.nextInt(testBoard.getSizeX())).then(new ReturnsElementsOf(listOfX));
        //Mockito.when(random.nextInt(testBoard.getSizeY())).then(new ReturnsElementsOf(listOfY));
        testBoard.createBoard();
        List<Integer> testList = new ArrayList<>();
        GameCell[][] arrayOfCells = testBoard.getArrayOfCells();
        for (int x = 0; x < testBoard.getSizeX(); x++) {
            for (int y = 0; y < testBoard.getSizeY(); y++) {
                testList.add(arrayOfCells[x][y].getNearMines());
            }
        }
        System.out.println(testList);
        Assertions.assertEquals(
                List.of(0, 1, 2, 2, 1, 0, 1, 2, 2, 1, 0, 1, 1, 2, 1, 1, 1, 2, 2, 2, 1, 1, 2, 2, 2),
                testList
        );
    }

    @Test
    void openCell() {
        //GameBoard testBoard =
    }
}