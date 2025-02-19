package tictactoe.renderer;

public class ConsoleRenderer implements Renderer {
    @Override
    public void render(char[][] grid) {
        // Column numbers
        System.out.print("  ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + 1);
            System.out.print(' ');
        }
        System.out.println();
        // Grid
        for (int line = 0; line < grid.length; line++) {
            System.out.print(line + 1); // Line numbers
            System.out.print('|');
            for (int column = 0; column < grid.length; column++) {
                System.out.print(grid[line][column]);
                System.out.print('|');
            }
            System.out.println();
        }
    }
}
