import java.util.Scanner;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class CustomExceptionDemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                System.out.print("Enter your age: ");
                int age = scanner.nextInt();
                
                if (age < 18) {
                    throw new InvalidAgeException("Age must be 18 or above.");
                }
                
                System.out.println("You are eligible.");
            } catch (InvalidAgeException e) {
                System.out.println("Invalid age: " + e.getMessage());
            }
        }
    }
}

/*
Input:
Enter your age: 16

Output:
Invalid age: Age must be 18 or above.
*/