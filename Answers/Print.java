public class Print {

    void printTitle(String title) {

        System.out.println();

        for (int i = 0; i < title.length() + 10; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println("**** " + title + " ****");
        for (int i = 0; i < title.length() + 10; i++) {
            System.out.print("*");
        }
        System.out.println("\n");
    }

    void printGrid(char[][] grid) {

        System.out.println("  A B C D E F G H I J");
        for (int i=0; i<10; i++) {
            System.out.print(i + " ");
            for (int j=0; j<10; j++) {
                if (j==9) System.out.print(grid[i][j]);
                else System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
