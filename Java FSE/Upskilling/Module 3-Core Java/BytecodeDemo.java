import java.util.Scanner;

public class BytecodeDemo {

    int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        BytecodeDemo obj = new BytecodeDemo();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter first number: ");
            int a = scanner.nextInt();

            System.out.print("Enter second number: ");
            int b = scanner.nextInt();

            System.out.println("Sum: " + obj.add(a, b));
        }
    }
}

/*
Input:
Enter first number: 10
Enter second number: 20

Output:
Sum: 30
*/