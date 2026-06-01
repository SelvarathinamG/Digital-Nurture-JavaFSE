import java.util.Scanner;

public class DecompileDemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.println("Welcome " + name);
        }
    }
}

/*
Input:
Enter your name: Selva

Output:
Welcome Selva
*/
