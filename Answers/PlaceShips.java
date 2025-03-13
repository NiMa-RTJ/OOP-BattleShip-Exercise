import java.util.Scanner;
import java.util.random.RandomGenerator;

public class PlaceShips {

    static Print print = new Print();

    static IsValidInput isValidInput = new IsValidInput();


    void randomPlacement(int GRID_SIZE, char[][] grid) {

        int[] shipSize = {2, 3, 4, 5};

        for (int i = 0; i < shipSize.length; i++) {
            boolean complete = false;
            while (!complete) {
                int row = RandomGenerator.getDefault().nextInt(GRID_SIZE);
                int col = RandomGenerator.getDefault().nextInt(GRID_SIZE);
                boolean horizontal = RandomGenerator.getDefault().nextBoolean();

                if (canPlaceShip(grid, row, col, shipSize[i], horizontal)) {
                    for (int j = 0; j < shipSize[i]; j++) {
                        if (horizontal) grid[row][col + j] = 'S';
                        else grid[row + j][col] = 'S';
                    }
                    complete = true;
                }
            }
        }

    }

    private boolean canPlaceShip(char[][] grid, int row, int col, int size, boolean horizontal) {

        if (horizontal) {
            if (col + size > 10) return false;
            for (int i = col; i < col + size; i++) {
                if (grid[row][i] != '~') return false;
            }
        } else {
            if (row + size > 10) return false;
            for (int i = row; i < row + size; i++) {
                if (grid[i][col] != '~') return false;
            }
        }

        return true;
    }

    void playerPlacement(char[][] grid) {

        int[] size = new int[4];
        print.printTitle("Player Ship Placement");

        for (int i=0; i<4; i++) {

            System.out.print("Ship ");
            System.out.print(i+1);
            System.out.println("/4");
            Scanner input = new Scanner(System.in);
            print.printGrid(grid);

            boolean Horizontal = false;


            String startPoint = "";
            String endPoint;

            int startIndex0 = 0;
            int startIndex1 = 0;

            boolean whileLoop = true;

            while (whileLoop) {
                System.out.print("Enter ship start point(For example A5): ");
                startPoint = input.next().toUpperCase();
                if (isValidInput.valid(startPoint)) {
                    startIndex0 = startPoint.charAt(0) - 'A';
                    startIndex1 = Integer.parseInt(startPoint.substring(1));
                    whileLoop = false;
                    if (grid[startIndex1][startIndex0]=='S') {
                        print.printTitle("That spot has already been built");
                        whileLoop = true;
                    }
                }
                else print.printTitle("Invalid input, try again");
            }

            whileLoop = true;

            int endIndex0;
            int endIndex1;

            while (whileLoop) {
                System.out.print("Enter ship end point(For example A5): ");
                endPoint = input.next().toUpperCase();
                if (isValidInput.valid(endPoint)) {
                    endIndex0 = endPoint.charAt(0) - 'A';
                    endIndex1 = Integer.parseInt(endPoint.substring(1));
                    if (grid[endIndex1][endIndex0]=='S') {
                        print.printTitle("That spot has already been built");
                    }
                    else {
                        if (!startPoint.equals(endPoint)) {
                            if (startIndex0 == endIndex0) {
                                Horizontal = false;
                                size[i] = endIndex1 - startIndex1 + 1;
                                whileLoop = false;
                                if (size[i] > 5) {
                                    print.printTitle("The ship size must be a maximum of 5");
                                    whileLoop = true;
                                }
                                for (int j=0; j<i; j++) {
                                    if (size[j]==size[i]) {
                                        print.printTitle("This size of ship has been built before");
                                        whileLoop = true;
                                        break;
                                    }
                                }
                            } else {
                                Horizontal = true;
                                size[i] = endIndex0 - startIndex0 + 1;
                                whileLoop = false;
                                if (size[i] > 5) {
                                    System.out.println("The ship size must be a maximum of 5");
                                    whileLoop = true;
                                }
                                for (int j=0; j<i; j++) {
                                    if (size[j]==size[i]) {
                                        print.printTitle("This size of ship has been built before");
                                        whileLoop = true;
                                        break;
                                    }
                                }
                            }
                        } else print.printTitle("The ship size must be at least 2");
                    }

                } else print.printTitle("Invalid input, try again");
            }

            for (int j = 0; j < size[i]; j++) {
                if (Horizontal) grid[startIndex1][startIndex0 + j] = 'S';
                else grid[startIndex1 + j][startIndex0] = 'S';
            }
        }
        print.printTitle("Player placement completed successfully");
    }
}
