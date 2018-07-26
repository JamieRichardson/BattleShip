import java.util.Scanner;
import java.util.regex.Pattern;

public class BattleshipGame {

    public static void main(String[] args) {
        Boolean playAgain = true;
        while (playAgain) {
            Ocean ocean = new Ocean();
            ocean.placeAllShipsRandomly();
            System.out.println("Welcome to Battleship");
            System.out.println("In this game you will take 5 shots at a time");
            System.out.println("The input format should look like this: 1, 1; 0, 3; 7, 3; 9, 11; 12, 17");
            System.out.println("");
            ocean.print();
            System.out.println("Enter shot coordinates to begin: ");
            Scanner scanner = new Scanner(System.in);
            while (ocean.shipsSunk < 13) {
                String userShots = scanner.nextLine();
                final Pattern pattern = Pattern.compile("^( ?(0|1[0-9]|[0-9]), (0|1[0-9]|[0-9]);){4}" +
                        " (0|1[0-9]|[0-9]), (0|1[0-9]|[0-9])$");
                if (pattern.matcher(userShots).matches()) {
                    String[] shots = userShots.split(";");
                    for (String shot : shots) {
                        String[] splitShot = shot.split(",");
                        int row = Integer.parseInt(splitShot[0].trim());
                        int column = Integer.parseInt(splitShot[1].trim());
                        ocean.shootAt(row, column);
                    }
                } else {
                    System.out.println("Invalid Shot Co-ordinates.  Number must be between 0 and 19" +
                            " and the input format should look like this: 1, 1; 0, 3; 7, 3; 9, 11; 12, 17");
                    System.out.println("");
                }
                System.out.println("");
                ocean.print();
                System.out.println("");
                if (ocean.shipsSunk < 13) {
                    System.out.println("Shoot again:");
                }
            }
            System.out.println("Congratulations, you sank all the ships!");
            System.out.println("It took you " + ocean.shotsFired + " shots");
            System.out.println("Would you like to play again? (Y or N)");
            String playAgainAnswer = scanner.nextLine();
            while (!playAgainAnswer.equalsIgnoreCase("Y") && !playAgainAnswer.equalsIgnoreCase("N")) {
                System.out.println("Please enter 'Y' or 'N'");
                playAgainAnswer = scanner.nextLine();
            }
            if ("Y".equalsIgnoreCase(playAgainAnswer)) {
                System.out.println("Okay, let's play again!");
                System.out.println("");
                playAgain = true;
            } else if ("N".equalsIgnoreCase(playAgainAnswer)) {
                System.out.print("Thanks for playing!");
                playAgain = false;
                scanner.close();
            }
        }
    }
}