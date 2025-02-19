package tictactoe.input;

import tictactoe.core.GameType;
import tictactoe.core.GridSymbol;
import tictactoe.player.PlayerResponse;

import java.util.Scanner;

public class InputManager {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readGridSize() {
        String input;
        while (true) {
            try {
                input = scanner.nextLine(); // nextInt() is not used on purpose (because of how Scanner works)
                int gridSize = Integer.parseInt(input);
                if (gridSize >= 3 && gridSize <= 9) {
                    return gridSize;
                }
            } catch (Exception _) {
                // Don't actually care about exception, that's invalid anyway
            }
            System.out.println("Incorrect grid size, try again");
        }
    }

    public static GameType readGameType() {
        String input;
        while (true) {
            try {
                input = scanner.nextLine(); // nextInt() is not used on purpose (because of how Scanner works)
                int gameType = Integer.parseInt(input);
                switch (gameType) {
                    case 1:
                        return GameType.HUMAN_VS_HUMAN;
                    case 2:
                        return GameType.HUMAN_VS_BOT;
                    case 3:
                        return GameType.BOT_VS_BOT;
                }
            } catch (Exception _) {
                // Don't actually care about exception, that's invalid anyway
            }
            System.out.println("Incorrect game type, try again");
        }
    }

    public static PlayerResponse readHumanPlayerResponse(char[][] grid, GridSymbol playerSymbol) {
        String input;
        System.out.print(playerSymbol.getChar() + " turn: ");
        while (true) {
            try {
                input = scanner.nextLine(); // nextInt() is not used on purpose (because of how Scanner works)
                if (input.length() != 2) {
                    throw new Exception();
                }
                int line = Character.getNumericValue(input.charAt(0)) - 1;
                int column = Character.getNumericValue(input.charAt(1)) - 1;
                char chosenChar = grid[line][column];
                if (chosenChar != GridSymbol.X.getChar()
                        && chosenChar != GridSymbol.O.getChar()) {
                    return new PlayerResponse(line, column);
                }
            } catch (Exception _) {
                // Don't actually care about exception, that's invalid anyway
            }
            System.out.println("Incorrect choice, try again");
        }
    }
}
