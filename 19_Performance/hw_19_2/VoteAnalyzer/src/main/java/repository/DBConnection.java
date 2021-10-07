package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "todolist";
    private static String dbUser = "root";
    private static String dbPass = "root";
    private static String dbUnicode = "true";
    private static String dbcharacterEncoding = "utf8";
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(
                    "dbc:mysql://localhost:3306" + dbName +
                            "?user=" + dbUser + "&password=" + dbPass);
            connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
            connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "PRIMARY KEY(id))");
        }
        return connection;
    }

}
