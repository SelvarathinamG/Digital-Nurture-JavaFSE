import java.util.Scanner;

public class FibRec {
    static int fibonacci(int n) {
        if (n <= 1) return n;
        if (n == 2) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n (positive integer): ");
        int n = scanner.nextInt();
        System.out.println("Fibonacci(" + n + ") = " + fibonacci(n));
        scanner.close();
    }
}

/*
Input:
Enter n (positive integer): 7

Output:
Fibonacci(7) = 13
*/
