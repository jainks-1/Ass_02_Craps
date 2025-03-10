/* The Rules of the Game:
- There is an initial roll of 2 dice resulting in a sum of 2 – 12.

i.	If the sum is 2, 3 or 12 it is called "craps" or "crapping out" and
    the game is over with a loss.
ii.	If the sum is 7 or 11 it is called a ‘natural’ and the game is over
    with a win.
iii. For all other values, the sum becomes ‘the point’ and the user makes
    subsequent rolls until they either throw a 7 in which case they lose
    or they make the point sum in which case they win.

- After a win or loss, the next player rolls the die for a new game. In our
    simulation, the program will simply ask the user if they want to
    continue to play.
- For each roll show the value for each die and the sum.
- For case i or ii above, indicate that the user either crapped out or won
    with a natural and then prompt to play again.
- For case iii indicate that the sum is now the point.  For each subsequent
    roll indicate the status of the result:
- Trying for point
- Made point and won.  (Prompt user to play again.)
- Got a seven and lost   (Prompt user to play again.)
*/

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Declare variables
        Scanner in = new Scanner(System.in);
        Random generator = new Random();

        int firstDice = 0;
        int secondDice = 0;
        int sumOfDice = 0;

        int thePoint;
        int thirdDice = 0;
        int fourthDice = 0;
        int newSumOfDice = 0;
        int newRollTotal = 0;

        int userCont;

        boolean playAgain = true;
        boolean gameNotOver = true;

        System.out.println("\nWelcome to Craps!");

        while (playAgain) {

            // do a roll
            firstDice = generator.nextInt(6) + 1;
            secondDice = generator.nextInt(6) + 1;
            sumOfDice = firstDice + secondDice;


            // Print roll results
            System.out.printf("\nDice-1 \tDice-2 \tSum %n");
            System.out.printf("-----------------------%n");
            System.out.printf("%d \t\t %d \t\t %d %n", firstDice, secondDice, sumOfDice);

            // logic for inital dice result
            if (sumOfDice == 2 || sumOfDice == 3 || sumOfDice == 12) {
                System.out.printf("\nYour dice roll total was: [%d], you crapped out and lost!\n", sumOfDice);
            }
            else if (sumOfDice == 7 || sumOfDice == 11) {

                System.out.printf("\nYour dice roll total was: [%d], you scored a natural and win!\n", sumOfDice);
            }
            else {
                thePoint = sumOfDice;
                System.out.printf("\nYour dice roll total is: [%d]. This is now your point. Keep rolling!\n", thePoint);

                gameNotOver = true;

                // start loop for re-rolling the dice
                while (gameNotOver) {
                    System.out.println("\nPress [1] to roll again, or [0] to quit the game.");

                    userCont = getValidInput(in);
                    if (userCont == 0) {
                        gameNotOver = false;
                        break;
                    }

                    // roll again
                    thirdDice = generator.nextInt(6) + 1;
                    fourthDice = generator.nextInt(6) + 1;
                    newSumOfDice = thirdDice + fourthDice;

                    // print that roll
                    System.out.printf("\nDice-1 \tDice-2 \tSum %n");
                    System.out.printf("-----------------------%n");
                    System.out.printf("%d \t\t %d \t\t %d %n", thirdDice, fourthDice, newSumOfDice);

                    // logic for roll result
                    if (newSumOfDice == 7) {
                        System.out.println("You threw a 7 and lost.");
                        gameNotOver = false;
                    }
                    else if (newSumOfDice == thePoint) {
                        System.out.println("You made the point and won!");
                        gameNotOver = false;
                    }
                    else {
                        System.out.println("Rolled again");
                    }
                }
            }

            // ask user if they want to play again
            System.out.println("\nDo you want to play again? [1] for yes, [0] for no: ");
            playAgain = (getValidInput(in) == 1);
        }

        System.out.println("\nThanks for playing!");
        in.close();

        // end program
        System.exit(0);
    }


    // input validation for rolling again, and quitting the program
    private static int getValidInput(Scanner in) {
        while (!in.hasNextInt()) {
            System.out.println("Invalid input. Please enter [1] for yes or [0] to quit.");
            in.next(); // Clear input
        }
        int input = in.nextInt();
        in.nextLine(); // Clear buffer
        while (input != 0 && input != 1) {
            System.out.println("Invalid choice. Please enter [1] for yes or [0] to quit.");
            input = in.nextInt();
            in.nextLine();
        }
        return input;
    }
}