import java.util.Scanner;

public class StrReverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = scanner.nextLine();
        String rev = new StringBuilder(s).reverse().toString();
        System.out.println("Reversed: " + rev);
        scanner.close();
    }
}

/*
Input:
Enter a string: Hello

Output:
Enter a string: Hello
Reversed: olleH
*/
