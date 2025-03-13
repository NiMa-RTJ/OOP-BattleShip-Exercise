
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Print print = new Print();
        Game game = new Game();

        print.printTitle("BattleShip Game");

        Scanner input = new Scanner(System.in);
        boolean wantsExit = false;

        while (!wantsExit) {

            System.out.println("1_ Two-Player Mode");
            System.out.println("2_ Play Against AI");
            System.out.println("3_ Exit Game");
            System.out.print("What's your choise? ");
            int choise = input.nextInt();

            switch (choise) {
                case 1:
                    game.twoPlayerMode();
                    break;
                case 2:
                    game.playAgainstAI();
                    break;
                case 3:
                    print.printTitle("Exiting program, Sayonara!");
                    wantsExit = true;
                    break;
                default:
                    print.printTitle("Invalid input, try again");
                    break;
            }



        }

    }
}
