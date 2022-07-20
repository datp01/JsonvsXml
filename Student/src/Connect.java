import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection getMssqlConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/studentinfo";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(dbURL,username,password);
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        if (getMssqlConnection()!=null){
            System.out.println("Ket noi thanh cong");
        }
    }
}
