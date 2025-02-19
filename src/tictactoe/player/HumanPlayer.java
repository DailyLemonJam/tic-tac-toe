package tictactoe.player;

import tictactoe.core.GridSymbol;
import tictactoe.input.InputManager;

public class HumanPlayer extends Player {
    public HumanPlayer(GridSymbol playerSymbol) {
        super(playerSymbol);
    }

    @Override
    public PlayerResponse getResponse(char[][] grid) {
        return InputManager.readHumanPlayerResponse(grid, getPlayerSymbol());
    }
}
