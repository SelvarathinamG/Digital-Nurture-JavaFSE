import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StreamAPI {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Integer> numbers = new ArrayList<>();
            System.out.print("Enter number of integers: ");
            int count = scanner.nextInt();

            for (int i = 1; i <= count; i++) {
                System.out.print("Enter integer " + i + ": ");
                numbers.add(scanner.nextInt());
            }

            List<Integer> evenNumbers = numbers.stream()
                    .filter(number -> number % 2 == 0)
                    .collect(Collectors.toList());

            System.out.println("Even numbers:");
            evenNumbers.forEach(System.out::println);
        }
    }
}

/*
Input:
Enter number of integers: 5
Enter integer 1: 10
Enter integer 2: 15
Enter integer 3: 20
Enter integer 4: 25
Enter integer 5: 30

Output:
Even numbers:
10
20
30
*/