package core;

import java.util.Scanner;

public class MainScreenView {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите желаемое количество тестов: ");
        int iterations = scanner.nextInt();
        System.out.print("Введите сторону поля: ");
        int size = scanner.nextInt();
        System.out.print("Введите количество мин: ");
        int numberOfMines = scanner.nextInt();
        int countOfWins = 0;
        int countOfLoses = 0;
        for (int i = 0; i < iterations; i++) {
            GameBoard board = new GameBoard(size, size, numberOfMines);
            board.createBoard();
            AutoSolver solver = new AutoSolver(board);
            ConditionOfGame resultOfOperation = solver.start();
            if (resultOfOperation == ConditionOfGame.WIN) countOfWins++;
            else countOfLoses++;
        }
        int percentOfWins = (countOfWins * 100 / iterations);
        int percentOfLoses = (countOfLoses * 100 / iterations);
        System.out.println("Процент правильных решений: " + percentOfWins);
        System.out.println("Процент неверных решений: " + percentOfLoses);
    }
}
