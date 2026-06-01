import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> studentNames = new ArrayList<String>();

        System.out.print("Enter number of students: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.print("Enter student name " + (i + 1) + ": ");
            studentNames.add(scanner.nextLine());
        }

        System.out.println("Student names:");
        for (String name : studentNames) {
            System.out.println(name);
        }

        scanner.close();
    }
}

/*
Input:
Enter number of students: 3
Enter student name 1: Arun
Enter student name 2: Balu
Enter student name 3: Charan

Output:
Student names:
Arun
Balu
Charan
*/