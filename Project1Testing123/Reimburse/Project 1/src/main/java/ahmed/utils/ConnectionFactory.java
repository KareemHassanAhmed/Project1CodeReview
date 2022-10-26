package ahmed.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {
    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=password&currentSchema=test";
        String username = "postgres";
        String password = "password";

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
