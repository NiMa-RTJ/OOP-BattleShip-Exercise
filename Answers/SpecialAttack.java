import java.util.Scanner;
import java.util.random.RandomGenerator;

public class SpecialAttack {

        static Print print = new Print();

        static Game game = new Game();

        static AIPlayer aiPlayer = new AIPlayer();

        public void specialAttackMenu(boolean isPlayer,char[][] enemyGrid,char[][] grid) {

            if (isPlayer) {

                print.printTitle("Specia Attack On");
                System.out.println("More than 50% of your ships are sunk, you can use one of these:");
                System.out.println("1_ Radar Scan");
                System.out.println("2_ Multi Strike Attack");
                System.out.print("Whats your choise? ");
                Scanner input = new Scanner(System.in);
                int choise = input.nextInt();

                boolean whileloop = true;

               while (whileloop) {

                    switch (choise) {
                        case 1:
                            String scanResult = radarScan(enemyGrid,true);
                            if (scanResult.equals("No ships found")) print.printTitle(scanResult);
                            else print.printTitle("Ship detected at " + scanResult);
                            return;
                        case 2:
                            multiStrike(enemyGrid, grid,true);
                            return;
                        default:
                            print.printTitle("Invalid input, try again");
                            break;
                    }
                }

            }
            else {

                print.printTitle("AI Specia Attack On");
                multiStrike(enemyGrid, grid, false);
            }

        }

        private static String radarScan(char[][] enemyGrid,boolean isPlayer) {

            int row = RandomGenerator.getDefault().nextInt(10);
            int col = RandomGenerator.getDefault().nextInt(10);

            for (int i=0; i<row; i++) {
                for (int j=0; j<col; j++) {
                    if (enemyGrid[i][j]=='S') return "" + (char) ('A' + j) + i;
                }
            }
            return "No ships found";
        }

        private static void multiStrike(char[][] enemyGrid, char[][] grid,boolean isPlayer) {

            if (!isPlayer) {
                for (int i=1; i<=3; i++) {
                    print.printTitle("Multi Strike Attack");
                    System.out.println("Shot " + i + "/3");
                    aiPlayer.playAI(grid, enemyGrid);
                }
            }
            else {
                for (int i=1; i<=3; i++) {
                    print.printTitle("Multi Strike Attack");
                    System.out.println("Shot " + i + "/3");
                    print.printGrid(grid);
                    game.playGame(enemyGrid, grid);
                }
            }

        }

}
