import java.util.Scanner;

public class DataDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an int: ");
        int number = scanner.nextInt();

        System.out.print("Enter a float: ");
        float price = scanner.nextFloat();

        System.out.print("Enter a double: ");
        double pi = scanner.nextDouble();

        System.out.print("Enter a char: ");
        char grade = scanner.next().charAt(0);

        System.out.print("Enter a boolean: ");
        boolean isReady = scanner.nextBoolean();

        System.out.println("int value: " + number);
        System.out.println("float value: " + price);
        System.out.println("double value: " + pi);
        System.out.println("char value: " + grade);
        System.out.println("boolean value: " + isReady);
        scanner.close();
    }
}

/*
Input:
Enter an int: 25
Enter a float: 99.5
Enter a double: 3.14159
Enter a char: A
Enter a boolean: true

Output:

int value: 25
float value: 99.5
double value: 3.14159
char value: A
boolean value: true
*/