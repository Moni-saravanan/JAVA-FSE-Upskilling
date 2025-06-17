import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDAO {
    private Connection conn;

    public AccountDAO(Connection conn) {
        this.conn = conn;
    }

    // Transfer money from one account to another with transaction management
    public boolean transferMoney(int fromAccount, int toAccount, double amount) {
        String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
        String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

        try {
            // Disable auto-commit mode
            conn.setAutoCommit(false);

            try (PreparedStatement debitStmt = conn.prepareStatement(debitSQL);
                 PreparedStatement creditStmt = conn.prepareStatement(creditSQL)) {

                // Debit from fromAccount
                debitStmt.setDouble(1, amount);
                debitStmt.setInt(2, fromAccount);
                int debitRows = debitStmt.executeUpdate();

                // Credit to toAccount
                creditStmt.setDouble(1, amount);
                creditStmt.setInt(2, toAccount);
                int creditRows = creditStmt.executeUpdate();

                // Check if both updates affected 1 row each
                if (debitRows == 1 && creditRows == 1) {
                    // Commit transaction
                    conn.commit();
                    System.out.println("Transaction successful: Transferred " + amount);
                    return true;
                } else {
                    // Something wrong, rollback
                    conn.rollback();
                    System.out.println("Transaction failed: Rolling back.");
                    return false;
                }
            } catch (SQLException e) {
                // Exception occurred, rollback
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                // Restore auto-commit mode to true
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
