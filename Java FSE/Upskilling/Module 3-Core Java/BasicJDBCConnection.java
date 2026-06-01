import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BasicJDBCConnection {
    private static final String URL = "jdbc:sqlite:students.db";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {
            Class.forName("org.sqlite.JDBC");

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS students ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "name TEXT NOT NULL, "
                            + "age INTEGER NOT NULL)"
            );

            System.out.print("Enter number of students: ");
            int count = scanner.nextInt();

            try (PreparedStatement insert = connection.prepareStatement(
                    "INSERT INTO students (name, age) VALUES (?, ?)")) {
                for (int i = 1; i <= count; i++) {
                    scanner.nextLine();
                    System.out.print("Enter student name " + i + ": ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student age " + i + ": ");
                    int age = scanner.nextInt();
                    insert.setString(1, name);
                    insert.setInt(2, age);
                    insert.executeUpdate();
                }
            }

            System.out.println("Student records:");
            try (ResultSet resultSet = statement.executeQuery("SELECT id, name, age FROM students ORDER BY id")) {
                while (resultSet.next()) {
                    System.out.println(
                            resultSet.getInt("id") + " | "
                                    + resultSet.getString("name") + " | "
                                    + resultSet.getInt("age")
                    );
                }
            }
        } catch (SQLException exception) {
            System.out.println("JDBC error: " + exception.getMessage());
            System.out.println("Make sure the SQLite JDBC driver is available on the classpath.");
        } catch (ClassNotFoundException exception) {
            System.out.println("JDBC driver not found: " + exception.getMessage());
        }
    }
}

/*
Input:
Enter number of students: 2
Enter student name 1: Arun
Enter student age 1: 21
Enter student name 2: Balu
Enter student age 2: 22

Output:
Student records:
1 | Arun | 21
2 | Balu | 22
*/