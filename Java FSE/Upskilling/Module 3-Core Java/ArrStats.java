import java.util.Scanner;

public class ArrStats {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();
        double sum = 0;
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter element " + (i+1) + ": ");
            arr[i] = scanner.nextDouble();
            sum += arr[i];
        }
        double avg = n > 0 ? sum / n : 0;
        System.out.println("Sum = " + sum);
        System.out.println("Average = " + avg);
        scanner.close();
    }
}

/*
Input:
Enter number of elements: 4
Enter element 1: 10.0
Enter element 2: 20.0
Enter element 3: 30.0
Enter element 4: 40.0

Output:

Sum = 100.0
Average = 25.0
*/
