package tictactoe.player;

import tictactoe.core.GridSymbol;

public abstract class Player {
    private final GridSymbol playerSymbol;

    public Player(GridSymbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public GridSymbol getPlayerSymbol() {
        return playerSymbol;
    }

    public abstract PlayerResponse getResponse(char[][] grid);
}