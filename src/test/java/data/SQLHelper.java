package data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLHelper {
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {

    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");

    }

    public static String getVerificationCode() {
        try(Connection connect = getConnection())
        {
            String queryVerCode = "SELECT code  FROM auth_codes ORDER by created DESC LIMIT 1";
            return QUERY_RUNNER.query(connect, queryVerCode, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
            return "Verification code not found";
        }
    }

    public static void cleanDB() {
        try(Connection connect = getConnection())
        {
            cleanVerCodeTable();
            QUERY_RUNNER.execute(connect, "DELETE FROM card_transactions");
            QUERY_RUNNER.execute(connect, "DELETE FROM cards");
            QUERY_RUNNER.execute(connect, "DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cleanVerCodeTable() {
        try(Connection connect = getConnection())
        {
            QUERY_RUNNER.execute(connect, "DELETE FROM auth_codes");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
