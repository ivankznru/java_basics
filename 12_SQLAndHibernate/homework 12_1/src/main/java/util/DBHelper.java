package util;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/skillbox?" +
            "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException sqle) {
            return null;
        }
    }
    public static String SQL_QUERY (){
        return  "SELECT Courses.name as course_name, \n" +
                "month(MAX(Subscriptions.subscription_date)) - month(MIN(Subscriptions.subscription_date)) + 1 as months, \n" +
                "COUNT(*) as subs_count FROM Subscriptions\n" +
                "JOIN Courses ON Courses.id = Subscriptions.course_id\n" +
                "GROUP BY course_name";
    }
    public static void getAvrAmount (ResultSet resultSet, Logger logger) throws SQLException {
        final String SPECIFIERS = "%-40s %.2f%n";
        String courseName = resultSet.getString("course_name");
        int monthsCount = Integer.parseInt(resultSet.getString("months"));
        double subsCount = Double.parseDouble(resultSet.getString("subs_count"));
        logger.info(courseName + " - " + (int) subsCount + " покупок и " + monthsCount + " месяцев");
        System.out.printf(SPECIFIERS, courseName, subsCount / monthsCount);
    };
}
