import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReadingDemo {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Unable to read output.txt");
        }
    }
}

/*
Input:
output.txt contains:
Hello, Java!

Output:
Hello, Java!
*/