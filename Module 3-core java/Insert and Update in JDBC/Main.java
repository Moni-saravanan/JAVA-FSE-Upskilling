import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/school"; // Your DB URL
        String user = "root";                              // Your DB username
        String password = "1234";                 // Your DB password

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            StudentDAO dao = new StudentDAO(conn);

            // Insert a student
            boolean inserted = dao.insertStudent(1, "Alice", "alice@example.com", 20);
            System.out.println("Insert successful? " + inserted);

            // Update the student's details
            boolean updated = dao.updateStudent(1, "Alice Smith", "alice.smith@example.com", 21);
            System.out.println("Update successful? " + updated);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
