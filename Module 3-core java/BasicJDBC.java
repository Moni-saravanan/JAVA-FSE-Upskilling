import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BasicJDBC {
    public static void main(String[] args) {
        // Database credentials and URL
        String url = "jdbc:mysql://localhost:3306/school";  // DB URL, database 'school'
        String username = "root";  
        String password = "1234";  
        try {
            // 1. Load MySQL JDBC driver (optional for modern versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);

            // 3. Create statement
            Statement stmt = conn.createStatement();

            // 4. Execute SELECT query
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            // 5. Iterate through results and print
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }

            // 6. Close resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

