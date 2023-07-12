package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://swp391-project.cx57c7iwoiqu.ap-southeast-2.rds.amazonaws.com:1433;databaseName=SWP391_PROJECT";
        conn = DriverManager.getConnection(url, "admin", "123456789");
        return conn;
    }
}
