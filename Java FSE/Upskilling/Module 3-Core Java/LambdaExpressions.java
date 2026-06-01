import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LambdaExpressions {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<String> names = new ArrayList<>();
            System.out.print("Enter number of names: ");
            int count = scanner.nextInt();
            scanner.nextLine();

            for (int i = 1; i <= count; i++) {
                System.out.print("Enter name " + i + ": ");
                names.add(scanner.nextLine());
            }

            Collections.sort(names, (first, second) -> first.compareToIgnoreCase(second));

            System.out.println("Sorted names:");
            names.forEach(System.out::println);
        }
    }
}

/*
Input:
Enter number of names: 3
Enter name 1: zara
Enter name 2: Arun
Enter name 3: balu

Output:
Sorted names:
Arun
balu
zara
*/