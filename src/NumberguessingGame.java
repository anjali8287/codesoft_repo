import java.util.Random;
import java.util.Scanner;

public class NumberguessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0; // Initialize the user's score

        System.out.println("Welcome to the Number Guessing Game!");

        while (true) {
            int generatedNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
            int attempts = 0; // Count the number of attempts for the current round
            int maxAttempts = 7; // Limit the number of attempts per round

            System.out.println("\nA random number between 1 and 100 has been generated.");
            System.out.println("You have " + maxAttempts + " attempts to guess it correctly.");

            boolean guessedCorrectly = false;

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    scanner.next(); // Consume invalid input
                    continue;
                }

                int guess = scanner.nextInt();
                attempts++;

                if (guess == generatedNumber) {
                    System.out.println("Congratulations! You guessed the number " + generatedNumber + " correctly in " + attempts + " attempts.");
                    score++;
                    guessedCorrectly = true;
                    break;
                } else if (guess < generatedNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The correct number was " + generatedNumber + ".");
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next().trim().toLowerCase();

            if (!playAgain.equals("yes")) {
                System.out.println("Thanks for playing! Your final score is " + score + ".");
                break;
            }
        }

        scanner.close();
    }
}

