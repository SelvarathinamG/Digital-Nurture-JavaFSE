import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double first = sc.nextDouble();

        System.out.print("Enter second number: ");
        double second = sc.nextDouble();

        System.out.print("Choose an operation (+, -, *, /): ");
        char operation = sc.next().charAt(0);

        if (operation == '+') 
        {
            System.out.println("Result: " + (first + second));
        } else if (operation == '-') 
        {
            System.out.println("Result: " + (first - second));
        } else if (operation == '*') 
        {
            System.out.println("Result: " + (first * second));
        } else if (operation == '/') 
        {
            if (second != 0) 
            {
                System.out.println("Result: " + (first / second));
            } else
            {
                System.out.println("Cannot divide by zero.");
            }
        } else 
        {
            System.out.println("Invalid operation.");
        }

        sc.close();
    }
}

/*
Input:
Enter first number: 10
Enter second number: 4
Choose operation (+, -, *, /): /

Output:
Result: 10.0 / 4.0 = 2.5
*/