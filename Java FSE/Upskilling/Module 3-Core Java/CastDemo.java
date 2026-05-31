import java.util.Scanner;

public class CastDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a double value: ");
        double decimalValue = scanner.nextDouble();
        int wholeNumber = (int) decimalValue;

        System.out.print("Enter an int value: ");
        int count = scanner.nextInt();
        double promotedValue = (double) count;

        System.out.println("Original double: " + decimalValue);
        System.out.println("Double to int: " + wholeNumber);
        System.out.println("Original int: " + count);
        System.out.println("Int to double: " + promotedValue);
        scanner.close();
    }
}

/*
Input:
Enter a double value: 45.89
Enter an int value: 12

Output:

Original double: 45.89
Double to int: 45
Original int: 12
Int to double: 12.0
*/