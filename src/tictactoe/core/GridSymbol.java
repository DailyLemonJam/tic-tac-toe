package tictactoe.core;

public enum GridSymbol {
    X('X'),
    O('O'),
    SPACE(' ');

    private final char symbol;

    GridSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getChar() {
        return symbol;
    }
}
