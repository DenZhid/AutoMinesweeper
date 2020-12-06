package core;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AutoSolverTest {


    @Test
    void start() {
            int countOfLoses = 0;
            int countOfWins = 0;
            for (int i = 0; i < 100; i++) {
                Random rnd = new Random();
                int size = rnd.nextInt(100);
                while (size < 3) {
                    size = rnd.nextInt(100);
                }
                GameBoard board = new GameBoard(size, size, size);
                board.createBoard();
                AutoSolver solver = new AutoSolver(board);
                ConditionOfGame finalCondition = solver.start();
                switch (finalCondition) {
                    case LOSE:
                        countOfLoses++;
                        break;
                    case WIN:
                        countOfWins++;
                        break;
                }
            }
            System.out.println(countOfLoses);
            System.out.println(countOfWins);
            assertTrue(countOfLoses < countOfWins);
        }
    }