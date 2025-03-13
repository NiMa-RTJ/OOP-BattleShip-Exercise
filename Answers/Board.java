public class Board {
    private char[][] grid;
    private int size;

    void Board(int size) {
        this.size = size;
        this.grid = new char[size][size];
        createBoard(grid);
    }

    private void createBoard(char[][] grid) {

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                grid[i][j] = '~';
            }
        }

    }

    public char[][] getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }
}