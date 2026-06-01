import java.util.Random;
import java.util.Scanner;

public class GuessGame {
    public static void main(String[] args) {
        Random random = new Random();
        try (Scanner scanner = new Scanner(System.in)) {
            int secretNumber = args.length > 0 ? Integer.parseInt(args[0]) : random.nextInt(100) + 1;
            int guess;
            
            System.out.println("Guess a number between 1 and 100.");
            
            do {
                System.out.print("Enter your guess: ");
                guess = scanner.nextInt();
                
                if (guess > secretNumber) {
                    System.out.println("Too high");
                } else if (guess < secretNumber) {
                    System.out.println("Too low");
                }
            } while (guess != secretNumber);
            
            System.out.println("Correct! The number was " + secretNumber + ".");
        }
    }
}

/*


Output:
Guess a number between 1 and 100.
Enter your guess: 50
Too low
Enter your guess: 75
Too high
Enter your guess: 62
Correct! The number was 62.
*/