import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentDAO {
    private static final String URL = "jdbc:sqlite:students.db";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Class.forName("org.sqlite.JDBC");
            initializeDatabase();

            StudentDAO dao = new StudentDAO();
            System.out.print("Enter student name to insert: ");
            String name = scanner.nextLine();
            System.out.print("Enter student age: ");
            int age = scanner.nextInt();
            dao.insertStudent(name, age);

            System.out.print("Enter student id to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            dao.updateStudentName(id, newName);

            dao.displayStudents();
        } catch (SQLException exception) {
            System.out.println("JDBC error: " + exception.getMessage());
            System.out.println("Make sure the SQLite JDBC driver is available on the classpath.");
        } catch (ClassNotFoundException exception) {
            System.out.println("JDBC driver not found: " + exception.getMessage());
        }
    }

    private static void initializeDatabase() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS students ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "name TEXT NOT NULL, "
                            + "age INTEGER NOT NULL)"
            );
        }
    }

    public void insertStudent(String name, int age) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO students (name, age) VALUES (?, ?)")) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
        }
    }

    public void updateStudentName(int id, String newName) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE students SET name = ? WHERE id = ?")) {
            statement.setString(1, newName);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

    public void displayStudents() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, name, age FROM students ORDER BY id")) {
            System.out.println("Student records:");
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("id") + " | "
                                + resultSet.getString("name") + " | "
                                + resultSet.getInt("age")
                );
            }
        }
    }
}

/*
Input:
Enter student name to insert: Kiran
Enter student age: 20
Enter student id to update: 1
Enter new name: Arun Kumar

Output:
Student records:
1 | Arun Kumar | 20
*/