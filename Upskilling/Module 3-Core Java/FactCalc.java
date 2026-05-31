import java.util.Scanner;

public class FactCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a non-negative integer: ");
        int n = scanner.nextInt();
        long fact = 1;
        for (int i = 2; i <= n; i++) fact *= i;
        System.out.println("Factorial of " + n + " is " + fact);
        scanner.close();
    }
}

/*


Input:
Enter a non-negative integer: 5

Output:

Factorial of 5 is 120
*/
