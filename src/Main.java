import tictactoe.core.Game;
import tictactoe.input.InputManager;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to Tic-Tac-Toe game!");
        System.out.println("Choose your grid size (from 3x3 to 9x9)");
        System.out.println("E.g. write 3 for 3x3, write 5 for 5x5 and etc");
        int gridSize = InputManager.readGridSize();

        System.out.println("Choose your game type");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Bot");
        System.out.println("3. Bot vs Bot");
        var gameType = InputManager.readGameType();

        System.out.println("--- Examples of how to choose ---");
        System.out.println("3rd line 1st column - 31");
        System.out.println("5th line 7th column - 57 and etc");

        var game = new Game(gridSize, gameType);
        game.start();
    }
}