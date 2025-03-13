import java.util.Random;

public class AIPlayer extends Player {

    static Print print = new Print();

    public void playAI(char[][] grid,char[][] enemyGrid) {

        boolean canShot = true;

       while (canShot) {
            String target = makeMove();
           int row = Integer.parseInt(target.substring(1));
           int col = target.charAt(0) - 'A';

            if (grid[row][col] == '~') {
                if (enemyGrid[row][col] == 'S') {
                    print.printTitle(target + " Hit");
                    grid[row][col] = 'X';
                    enemyGrid[row][col] = 'X';
                } else {
                    print.printTitle(target + " Miss");
                    grid[row][col] = 'O';
                }
                canShot = false;
            }
        }
    }
    private String makeMove() {
        Random rand = new Random();
        return "" + (char) ('A' + rand.nextInt(10)) + rand.nextInt(10);
    }


}
