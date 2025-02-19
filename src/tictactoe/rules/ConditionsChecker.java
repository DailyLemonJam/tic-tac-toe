package tictactoe.rules;

import tictactoe.core.GridSymbol;

public class ConditionsChecker {
    private record InnerConditionsCheckerResponse(boolean gameIsOver, String message) {}

    public ConditionsCheckerResponse check(char[][] grid, int movesCount) {
        var response = checkRows(grid);
        if (response.gameIsOver()) {
            return new ConditionsCheckerResponse(true, response.message());
        }
        response = checkColumns(grid);
        if (response.gameIsOver()) {
            return new ConditionsCheckerResponse(true, response.message());
        }
        response = checkDiagonals(grid);
        if (response.gameIsOver()) {
            return new ConditionsCheckerResponse(true, response.message());
        }
        response = checkMoves(grid.length, movesCount);
        return new ConditionsCheckerResponse(response.gameIsOver(), response.message());
    }

    private InnerConditionsCheckerResponse checkRows(char[][] grid) {
        for (int line = 0; line < grid.length; line++) {
            boolean isCompleteRow = true;
            if (grid[line][0] == GridSymbol.SPACE.getChar()) {
                break;
            }
            for (int column = 1; column < grid.length; column++) {
                if (grid[line][0] != grid[line][column]) {
                    isCompleteRow = false;
                    break;
                }
            }
            if (isCompleteRow) {
                return new InnerConditionsCheckerResponse(true, grid[line][0] + " won!");
            }
        }
        return new InnerConditionsCheckerResponse(false, "");
    }

    private InnerConditionsCheckerResponse checkColumns(char[][] grid) {
        for (int column = 0; column < grid.length; column++) {
            boolean isCompleteColumn = true;
            if (grid[0][column] == GridSymbol.SPACE.getChar()) {
                break;
            }
            for (int line = 1; line < grid.length; line++) {
                if (grid[0][column] != grid[line][column]) {
                    isCompleteColumn = false;
                    break;
                }
            }
            if (isCompleteColumn) {
                return new InnerConditionsCheckerResponse(true, grid[0][column] + " won!");
            }
        }
        return new InnerConditionsCheckerResponse(false, "");
    }

    private InnerConditionsCheckerResponse checkDiagonals(char[][] grid) {
        // Top-left -> Bottom-right
        if (grid[0][0] != GridSymbol.SPACE.getChar()) {
            boolean isCompleteDiagonal = true;
            for (int i = 1; i < grid.length; i++) {
                if (grid[0][0] != grid[i][i]) {
                    isCompleteDiagonal = false;
                    break;
                }
            }
            if (isCompleteDiagonal) {
                return new InnerConditionsCheckerResponse(true, grid[0][0] + " won!");
            }
        }
        // Bottom-left -> Top-right
        if (grid[grid.length - 1][0] != GridSymbol.SPACE.getChar()) {
            boolean isCompleteDiagonal = true;
            for (int i = 1; i < grid.length; i++) {
                if (grid[grid.length - 1][0] != grid[grid.length - 1 - i][i]) {
                    isCompleteDiagonal = false;
                    break;
                }
            }
            if (isCompleteDiagonal) {
                return new InnerConditionsCheckerResponse(true, grid[grid.length - 1][0] + " won!");
            }
        }
        return new InnerConditionsCheckerResponse(false, "");
    }

    private InnerConditionsCheckerResponse checkMoves(int gridSize, int movesCount) {
        if (movesCount == gridSize * gridSize) {
            return new InnerConditionsCheckerResponse(true, "Draw! No available moves left.");
        }
        return new InnerConditionsCheckerResponse(false, "");
    }
}
