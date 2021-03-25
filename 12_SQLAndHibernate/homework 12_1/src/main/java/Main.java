import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static final Logger logger = LogManager.getLogger(Main.class);
    private static final Connection CONNECTION_SKILLBOX = DBMySQLSkillbox.connect();


 private static final String SQL_QUERY = " SELECT Courses.name as course_name, \n" +
         "month(MAX(Subscriptions.subscription_date)) - month(MIN(Subscriptions.subscription_date)) + 1 as months, \n" +
         "COUNT(*) as subs_count FROM Subscriptions\n" +
         "JOIN Courses ON Courses.id = Subscriptions.course_id\n" +
         "GROUP BY course_name";


    public static void main(String[] args) throws SQLException {
        try {
            if (CONNECTION_SKILLBOX != null) {
                Statement statement = CONNECTION_SKILLBOX.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_QUERY);
                System.out.println("Среднее количество покупок по курсам за 2018 год:");
                String specifiers = "%-40s %.2f%n";
                while (resultSet.next()) {
                    String courseName = resultSet.getString("course_name");
                    int monthsCount = Integer.parseInt(resultSet.getString("months"));
                    double subsCount = Double.parseDouble(resultSet.getString("subs_count"));
                    logger.info(courseName + " - " + (int) subsCount + " покупок и " + monthsCount + " месяцев");
                    System.out.printf(specifiers, courseName, subsCount / monthsCount);
                }
                resultSet.close();
                statement.close();
                CONNECTION_SKILLBOX.close();
            }
        } catch (NullPointerException | SQLException ex) {
            logger.error(ex);
            ex.printStackTrace();
        } finally {
            if (CONNECTION_SKILLBOX != null)
                CONNECTION_SKILLBOX.close();
        }
    }
}
