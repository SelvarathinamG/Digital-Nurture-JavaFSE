import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListExample {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<String> studentNames = new ArrayList<>();
            
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
        }
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