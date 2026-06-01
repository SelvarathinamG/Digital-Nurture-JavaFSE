import java.util.Scanner;

public class GradeCalc {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter marks out of 100: ");
            int marks = scanner.nextInt();
            
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks. Enter a value from 0 to 100.");
            } else {
                char grade;
                if (marks >= 90) {
                    grade = 'A';
                } else if (marks >= 80) {
                    grade = 'B';
                } else if (marks >= 70) {
                    grade = 'C';
                } else if (marks >= 60) {
                    grade = 'D';
                } else {
                    grade = 'F';
                }
                
                System.out.println("Grade: " + grade);
            }
        }
    }
}

/*
Input:
Enter marks out of 100: 85

Output:

Grade: B
*/