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

        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection(URL);
                 Statement statement = connection.createStatement();
                 Scanner scanner = new Scanner(System.in)) {

                statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "age INTEGER NOT NULL)"
                );

                statement.executeUpdate("DELETE FROM students");

                System.out.print("Enter number of students: ");
                int count = scanner.nextInt();
                scanner.nextLine();

                try (PreparedStatement insert = connection.prepareStatement(
                    "INSERT INTO students(name, age) VALUES(?, ?)"
                )) {
                    for (int i = 1; i <= count; i++) {
                        System.out.print("Enter student name " + i + ": ");
                        String name = scanner.nextLine();

                        System.out.print("Enter student age " + i + ": ");
                        int age = scanner.nextInt();
                        scanner.nextLine();

                        insert.setString(1, name);
                        insert.setInt(2, age);
                        insert.executeUpdate();
                    }
                }

                System.out.println("\nStudent records:");

                try (ResultSet rs = statement.executeQuery(
                    "SELECT id, name, age FROM students ORDER BY id"
                )) {
                    while (rs.next()) {
                        System.out.println(
                            rs.getInt("id") + " | " +
                            rs.getString("name") + " | " +
                            rs.getInt("age")
                        );
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
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