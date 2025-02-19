package tictactoe.player;

import tictactoe.core.GridSymbol;

import java.util.ArrayList;
import java.util.Random;

public class BotPlayer extends Player {
    private record InnerBotPlayerResponse(boolean shouldReturn, int line, int column) {}
    private record Cell(int line, int column) {}

    private final GridSymbol enemySymbol;

    public BotPlayer(GridSymbol playerSymbol) {
        super(playerSymbol);
        if (playerSymbol == GridSymbol.X) {
            enemySymbol = GridSymbol.O;
        } else {
            enemySymbol = GridSymbol.X;
        }
    }

    @Override
    public PlayerResponse getResponse(char[][] grid) {

        // Bot logic:
        // If bot can win (1 symbol left for complete row) - put missing "playerSymbol" in that place
        // If bot can't win but enemy can win next round - find enemy's row where he can win and block it
        // Otherwise - 2 options:
        // 1. Put "playerSymbol" in random place (more fun)
        // 2. Put "playerSymbol" in row with the biggest amount of "playerSymbol"s
        // 2nd option is a little bit boring and will probably cause bots to place symbols in line by line
        // That's why 1st option was chosen

        // Simulation of the thought process (just for immersion, can be removed if necessary)
        try {
            int millisToWait = new Random().nextInt(200, 1000);
            Thread.sleep(millisToWait);
        } catch (InterruptedException _) {
            // Will never happen, just for sleep method
        }

        // Check for potential win
        var response = checkRows(grid, getPlayerSymbol());
        if (response.shouldReturn()) {
            return new PlayerResponse(response.line(), response.column());
        }
        response = checkColumns(grid, getPlayerSymbol());
        if (response.shouldReturn()) {
            return new PlayerResponse(response.line(), response.column());
        }
        response = checkDiagonals(grid, getPlayerSymbol());
        if (response.shouldReturn()) {
            return new PlayerResponse(response.line(), response.column());
        }

        // Check for potential lose
        response = checkRows(grid, enemySymbol);
        if (response.shouldReturn()) {
            return new PlayerResponse(response.line(), response.column());
        }
        response = checkColumns(grid, enemySymbol);
        if (response.shouldReturn()) {
            return new PlayerResponse(response.line(), response.column());
        }
        response = checkDiagonals(grid, enemySymbol);
        if (response.shouldReturn()) {
            return new PlayerResponse(response.line(), response.column());
        }

        // If code goes here then there are no instant possibilities for win or lose
        // So just return random empty cell
        response = getRandomEmptyCell(grid);
        return new PlayerResponse(response.line(), response.column());
    }

    private InnerBotPlayerResponse checkRows(char[][] grid, GridSymbol currentSymbol) {
        for (int line = 0; line < grid.length; line++) {
            int sameSymbolCounter = 0;
            for (int column = 0; column < grid.length; column++) {
                if (grid[line][column] == currentSymbol.getChar()) {
                    sameSymbolCounter++;
                }
            }
            if (sameSymbolCounter + 1 == grid.length) {
                for (int column = 0; column < grid.length; column++) {
                    if (grid[line][column] == GridSymbol.SPACE.getChar()) {
                        return new InnerBotPlayerResponse(true, line, column);
                    }
                }
            }
        }
        return new InnerBotPlayerResponse(false, -1, -1);
    }

    private InnerBotPlayerResponse checkColumns(char[][] grid, GridSymbol currentSymbol) {
        for (int column = 0; column < grid.length; column++) {
            int sameSymbolCounter = 0;
            for (int line = 0; line < grid.length; line++) {
                if (grid[line][column] == currentSymbol.getChar()) {
                    sameSymbolCounter++;
                }
            }
            if (sameSymbolCounter + 1 == grid.length) {
                for (int line = 0; line < grid.length; line++) {
                    if (grid[line][column] == GridSymbol.SPACE.getChar()) {
                        return new InnerBotPlayerResponse(true, line, column);
                    }
                }
            }
        }
        return new InnerBotPlayerResponse(false, -1, -1);
    }

    private InnerBotPlayerResponse checkDiagonals(char[][] grid, GridSymbol currentSymbol) {
        // Top-left -> Bottom-right
        int sameSymbolCounter = 0;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][i] == currentSymbol.getChar()) {
                sameSymbolCounter++;
            }
        }
        if (sameSymbolCounter + 1 == grid.length) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][i] == GridSymbol.SPACE.getChar()) {
                    return new InnerBotPlayerResponse(true, i, i);
                }
            }
        }

        // Bottom-left -> Top-right
        sameSymbolCounter = 0;
        for (int i = 0; i < grid.length; i++) {
            if (grid[grid.length - 1 - i][i] == currentSymbol.getChar()) {
                sameSymbolCounter++;
            }
        }
        if (sameSymbolCounter + 1 == grid.length) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[grid.length - 1 - i][i] == GridSymbol.SPACE.getChar()) {
                    return new InnerBotPlayerResponse(true, grid.length - 1 - i, i);
                }
            }
        }

        return new InnerBotPlayerResponse(false, -1, -1);
    }

    private InnerBotPlayerResponse getRandomEmptyCell(char[][] grid) {
        var emptyCells = new ArrayList<Cell>();
        for (int line = 0; line < grid.length; line++) {
            for (int column = 0; column < grid.length; column++) {
                if (grid[line][column] == GridSymbol.SPACE.getChar()) {
                    emptyCells.add(new Cell(line, column));
                }
            }
        }
        var generator = new Random();
        int i = generator.nextInt(emptyCells.size());
        int line = emptyCells.get(i).line();
        int column = emptyCells.get(i).column();
        return new InnerBotPlayerResponse(true, line, column);
    }
}
