package core;

import java.util.Scanner;

public class MainScreenView {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите желаемое количество тестов: ");
        int iterations = scanner.nextInt();
        System.out.print("Введите первую сторону поля: ");
        int sizeX = scanner.nextInt();
        System.out.print("Введите вторую сторону поля: ");
        int sizeY = scanner.nextInt();
        System.out.print("Введите количество мин: ");
        int numberOfMines = scanner.nextInt();
        int countOfWins = 0;
        int countOfLoses = 0;
        for (int i = 0; i < iterations; i++) {
            GameBoard board = new GameBoard(sizeX, sizeY, numberOfMines);
            board.createBoard();
            AutoSolver solver = new AutoSolver(board);
            ConditionOfGame resultOfOperation = solver.start();
            if (resultOfOperation == ConditionOfGame.WIN) countOfWins++;
            else countOfLoses++;
        }
        int percentOfWins = (countOfWins * 100 / iterations);
        int percentOfLoses = (countOfLoses * 100 / iterations);
        System.out.println("Процент правильных решений: " + percentOfWins + "%");
        System.out.println("Процент неверных решений: " + percentOfLoses + "%");
    }
}
