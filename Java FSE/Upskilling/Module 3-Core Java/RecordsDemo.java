import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecordsDemo {
    public record Person(String name, int age) {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Person> people = new ArrayList<>();
            System.out.print("Enter number of people: ");
            int count = scanner.nextInt();
            scanner.nextLine();

            for (int i = 1; i <= count; i++) {
                System.out.print("Enter name " + i + ": ");
                String name = scanner.nextLine();
                System.out.print("Enter age " + i + ": ");
                int age = scanner.nextInt();
                scanner.nextLine();
                people.add(new Person(name, age));
            }

            people.forEach(System.out::println);
            System.out.println("Adults:");
            people.stream().filter(person -> person.age() >= 18).forEach(System.out::println);
        }
    }
}

/*
Input:
Enter number of people: 2
Enter name 1: Arun
Enter age 1: 21
Enter name 2: Balu
Enter age 2: 17

Output:
Person[name=Arun, age=21]
Person[name=Balu, age=17]
Adults:
Person[name=Arun, age=21]
*/