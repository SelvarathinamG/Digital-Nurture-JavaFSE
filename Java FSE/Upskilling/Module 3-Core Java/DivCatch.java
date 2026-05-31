import java.util.Scanner;

public class DivCatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter numerator: ");
        int a = scanner.nextInt();
        System.out.print("Enter denominator: ");
        int b = scanner.nextInt();
        try {
            int res = a / b;
            System.out.println("Result: " + res);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }
        scanner.close();
    }
}

/*

Input:
Enter numerator: 10
Enter denominator: 0

Output:
Enter numerator: 10
Enter denominator: 0
Cannot divide by zero
*/
