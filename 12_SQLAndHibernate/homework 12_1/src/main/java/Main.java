import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DBHelper;

public class Main {
    static final Logger logger = LogManager.getLogger(Main.class);
    private static final Connection CONNECTION_SKILLBOX = DBHelper.connect();
    private static final String SQL_QUERY = DBHelper.SQL_QUERY();
    public static void main(String[] args) throws SQLException {
        try {
            if (CONNECTION_SKILLBOX != null) {
                Statement statement = CONNECTION_SKILLBOX.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_QUERY);
                System.out.println("Среднее количество покупок по курсам за 2018 год:");

                while (resultSet.next()) {
                    DBHelper.getAvrAmount(resultSet, logger);
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