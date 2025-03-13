import java.util.Scanner;

public class Game {

    static Print print = new Print();

    static IsValidInput isValidInput = new IsValidInput();

    static PlaceShips placeShips = new PlaceShips();

    static Player player1 = new Player();

    static Player player2 = new Player();

    static AIPlayer aiPlayer = new AIPlayer();

    static Board Player_1_Grid = new Board();

    static Board Player_2_Grid = new Board();

    static Board viewBoard1 = new Board();

    static  Board viewBoard2 = new Board();

    static Board aiGrid = new Board();

    static Board AIView = new Board();

    void playAgainstAI() {

        Player_1_Grid.Board(10);
        viewBoard1.Board(10);
        aiGrid.Board(10);
        AIView.Board(10);

        player1Start();
        aiPlayer.setPlayer("AI");
        placeShips.randomPlacement(10,aiGrid.getGrid());

        boolean player1Turn = true;

        while (!isGameOver(Player_1_Grid.getGrid(), aiGrid.getGrid())) {
            if (player1Turn) {
                print.printTitle(player1.getPlayer()+ " Turn");
                print.printGrid(viewBoard1.getGrid());
                playGame(aiGrid.getGrid(), viewBoard1.getGrid());
                player1Turn = false;
            }
            else {
                print.printTitle(aiPlayer.getPlayer() + " Turn");
                print.printGrid(AIView.getGrid());
                aiPlayer.playAI(AIView.getGrid(), Player_1_Grid.getGrid());
                player1Turn = true;
            }

        }
    }

    void twoPlayerMode() {

        Player_1_Grid.Board(10);
        Player_2_Grid.Board(10);
        viewBoard1.Board(10);
        viewBoard2.Board(10);

        player1Start();
        player2Start();

        boolean player1Turn = true;

        while (!isGameOver(Player_2_Grid.getGrid(),Player_2_Grid.getGrid())) {
            if (player1Turn) {
                print.printTitle(player1.getPlayer() + " Turn");
                print.printGrid(viewBoard1.getGrid());
                playGame(Player_2_Grid.getGrid(), viewBoard1.getGrid());
                player1Turn = false;
            }
            else {
                print.printTitle(player2.getPlayer() + " Turn");
                print.printGrid(viewBoard2.getGrid());
                playGame(Player_1_Grid.getGrid(), viewBoard2.getGrid());
                player1Turn = true;
            }

        }

    }

    private void playGame(char[][] enemyGrid,char[][] grid) {

        Scanner input = new Scanner(System.in);
        boolean canShot = true;

        while (canShot) {

            System.out.print("Enter your target (For example A5): ");
            String target = input.next().toUpperCase();

            if (isValidInput.valid(target)) {

                int col = target.charAt(0) - 'A';
                int row = Integer.parseInt(target.substring(1));

                if (grid[row][col] == '~') {
                    if (enemyGrid[row][col] == 'S') {
                        print.printTitle("Hit");
                        grid[row][col] = 'X';
                        enemyGrid[row][col] = 'X';
                    }
                    else {
                        print.printTitle("Miss");
                        grid[row][col] = 'O';
                    }
                    canShot = false;
                }
                else print.printTitle("Already targeted, try again");

            }
            else print.printTitle("Invalid input, try again");
        }

    }

    private void player1Start() {

        Scanner input = new Scanner(System.in);

        print.printTitle("Naming The Player 1");
        System.out.print("Name of player 1: ");
        player1.setPlayer(input.nextLine());

        boolean whileLoop = true;

        while(whileLoop) {
            print.printTitle("Ship Placement of " + player1.getPlayer());

            System.out.println("1_ Random Placement");
            System.out.println("2_ Place Yourself");
            System.out.print("Whats your choise? ");
            int choise = input.nextInt();

            switch (choise) {
                case 1:
                    placeShips.randomPlacement(Player_1_Grid.getSize(), Player_1_Grid.getGrid());
                    whileLoop = false;
                    break;
                case 2:
                    placeShips.playerPlacement(Player_1_Grid.getGrid());
                    whileLoop = false;
                    break;
                default:
                    print.printTitle("Invalid input, try again");
                    break;
            }
        }

    }

    private void player2Start() {

        Scanner input = new Scanner(System.in);
        boolean finishedNaming = false;
        while (!finishedNaming) {
            print.printTitle("Naming The Player 2");
            System.out.print("Name of player 2: ");
            String name = input.nextLine();
            if (name.equals(player1.getPlayer())) print.printTitle("This name is already taken, try again");
            else {
                player2.setPlayer(name);
                finishedNaming = true;
            }

        }

        boolean whileLoop = true;

        while(whileLoop) {
            print.printTitle("Ship Placement of " + player2.getPlayer());

            System.out.println("1_ Random Placement");
            System.out.println("2_ Place Yourself");
            System.out.print("Whats your choise? ");
            int choise = input.nextInt();

            switch (choise) {
                case 1:
                    placeShips.randomPlacement(Player_2_Grid.getSize(), Player_2_Grid.getGrid());
                    whileLoop = false;
                    break;
                case 2:
                    placeShips.playerPlacement(Player_2_Grid.getGrid());
                    whileLoop = false;
                    break;
                default:
                    print.printTitle("Invalid input, try again");
                    break;
            }
        }


    }

    boolean isGameOver(char[][] grid1, char[][] grid2) {
        return allShipSunk(grid1) || allShipSunk(grid2);
    }

    boolean allShipSunk(char[][] grid) {

        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                if (grid[i][j] == 'S') return false;
            }
        }
        return true;

    }

}
