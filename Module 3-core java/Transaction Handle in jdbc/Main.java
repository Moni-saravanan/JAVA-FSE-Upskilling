public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Accounts";
        String user = "root";
        String password = "1234";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            AccountDAO dao = new AccountDAO(conn);

            // Transfer $100 from account 1 to account 2
            boolean success = dao.transferMoney(1, 2, 100.0);
            System.out.println("Transfer success: " + success);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
