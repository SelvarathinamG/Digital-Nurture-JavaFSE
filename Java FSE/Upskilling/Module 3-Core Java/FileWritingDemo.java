import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWritingDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter a string: ");
            String data = scanner.nextLine();

            FileWriter writer = new FileWriter("output.txt");
            writer.write(data);
            writer.close();

            System.out.println("Data has been written to output.txt");
        } catch (IOException e) {
            System.out.println("Error while writing to file.");
        }

        scanner.close();
    }
}

/*
Input:
Enter a string: Hello, Java!

Output:
Data has been written to output.txt
*/