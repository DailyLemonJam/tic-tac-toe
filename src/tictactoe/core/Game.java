package tictactoe.core;

import tictactoe.player.BotPlayer;
import tictactoe.player.HumanPlayer;
import tictactoe.player.Player;
import tictactoe.renderer.ConsoleRenderer;
import tictactoe.renderer.Renderer;
import tictactoe.rules.ConditionsChecker;

public class Game {
    private final Renderer renderer;
    private final ConditionsChecker conditionsChecker;

    private final int gridSize;
    private char [][] grid;
    private final GameType gameType;

    private Player playerX;
    private Player playerO;

    private boolean gameIsOver = false;
    private boolean playerXTurn = true;
    private int movesCount = 0;

    public Game(int gridSize, GameType gameType) {
        this.gridSize = gridSize;
        this.gameType = gameType;
        createGrid();
        createPlayers();
        renderer = new ConsoleRenderer();
        conditionsChecker = new ConditionsChecker();
    }

    public void start() {
        drawGrid();
        while (!gameIsOver) {
            getPlayerResponse();
            drawGrid();
            checkConditions();
        }
    }

    private void drawGrid() {
        renderer.render(grid);
    }

    private void getPlayerResponse() {
        if (playerXTurn) {
            var response = playerX.getResponse(grid);
            grid[response.line()][response.column()] = GridSymbol.X.getChar();
        } else {
            var response = playerO.getResponse(grid);
            grid[response.line()][response.column()] = GridSymbol.O.getChar();
        }
        playerXTurn = !playerXTurn;
        movesCount++;
    }

    private void checkConditions() {
        var response = conditionsChecker.check(grid, movesCount);
        if (response.gameIsOver()) {
            System.out.println(response.finalMessage());
            gameIsOver = true;
        }
    }

    private void createGrid() {
        grid = new char[gridSize][gridSize];
        for (int line = 0; line < gridSize; line++) {
            for (int column = 0; column < gridSize; column++) {
                grid[line][column] = GridSymbol.SPACE.getChar();
            }
        }
    }

    private void createPlayers() {
        switch (gameType) {
            case HUMAN_VS_HUMAN:
                playerX = new HumanPlayer(GridSymbol.X);
                playerO = new HumanPlayer(GridSymbol.O);
                break;
            case HUMAN_VS_BOT:
                playerX = new HumanPlayer(GridSymbol.X);
                playerO = new BotPlayer(GridSymbol.O);
                break;
            case BOT_VS_BOT:
                playerX = new BotPlayer(GridSymbol.X);
                playerO = new BotPlayer(GridSymbol.O);
                break;
        }
    }
}
