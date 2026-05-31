import java.util.Scanner;

public class OpOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int first = scanner.nextInt();

        System.out.print("Enter second number: ");
        int second = scanner.nextInt();

        int result = first + second * 2;
        int groupedResult = (first + second) * 2;

        System.out.println(first + " + " + second + " * 2 = " + result);
        System.out.println("(" + first + " + " + second + ") * 2 = " + groupedResult);
        System.out.println("Multiplication happens before addition unless brackets change the order.");
        scanner.close();
    }
}

/*
Input:
Enter first number: 10
Enter second number: 5

Output:

10 + 5 * 2 = 20
(10 + 5) * 2 = 30
Multiplication happens before addition unless brackets change the order.
*/