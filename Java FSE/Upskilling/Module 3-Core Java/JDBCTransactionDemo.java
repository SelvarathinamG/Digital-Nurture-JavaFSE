import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCTransactionDemo {
    private static final String URL = "jdbc:sqlite:bank.db";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Class.forName("org.sqlite.JDBC");
            initializeDatabase();
            seedAccounts();

            System.out.print("Enter sender account id: ");
            int fromAccountId = scanner.nextInt();
            System.out.print("Enter receiver account id: ");
            int toAccountId = scanner.nextInt();
            System.out.print("Enter transfer amount: ");
            double amount = scanner.nextDouble();

            transfer(fromAccountId, toAccountId, amount);
            showAccounts();
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
                    "CREATE TABLE IF NOT EXISTS accounts ("
                            + "id INTEGER PRIMARY KEY, "
                            + "holder_name TEXT NOT NULL, "
                            + "balance REAL NOT NULL)"
            );
        }
    }

    private static void seedAccounts() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM accounts")) {
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                try (PreparedStatement insert = connection.prepareStatement(
                        "INSERT INTO accounts (id, holder_name, balance) VALUES (?, ?, ?)")) {
                    insert.setInt(1, 1);
                    insert.setString(2, "Arun");
                    insert.setDouble(3, 1000.0);
                    insert.executeUpdate();

                    insert.setInt(1, 2);
                    insert.setString(2, "Balu");
                    insert.setDouble(3, 500.0);
                    insert.executeUpdate();
                }
            }
        }
    }

    private static void transfer(int fromAccountId, int toAccountId, double amount) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(false);

            try (PreparedStatement debit = connection.prepareStatement(
                         "UPDATE accounts SET balance = balance - ? WHERE id = ? AND balance >= ?");
                 PreparedStatement credit = connection.prepareStatement(
                         "UPDATE accounts SET balance = balance + ? WHERE id = ?")) {

                debit.setDouble(1, amount);
                debit.setInt(2, fromAccountId);
                debit.setDouble(3, amount);

                credit.setDouble(1, amount);
                credit.setInt(2, toAccountId);

                int debited = debit.executeUpdate();
                int credited = credit.executeUpdate();

                if (debited == 1 && credited == 1) {
                    connection.commit();
                    System.out.println("Transfer successful.");
                } else {
                    connection.rollback();
                    System.out.println("Transfer failed. Transaction rolled back.");
                }
            } catch (SQLException exception) {
                connection.rollback();
                throw exception;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    private static void showAccounts() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT id, holder_name, balance FROM accounts ORDER BY id")) {
            System.out.println("Account balances:");
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("id") + " | "
                                + resultSet.getString("holder_name") + " | "
                                + resultSet.getDouble("balance")
                );
            }
        }
    }
}

/*
Input:
Enter sender account id: 1
Enter receiver account id: 2
Enter transfer amount: 250

Output:
Transfer successful.
Account balances:
1 | Arun | 750.0
2 | Balu | 750.0
*/