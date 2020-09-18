package by.kvach.lightBase.service.dbangine;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

import static org.testng.Assert.fail;

public class DBConnect {

    private Statement connect() {
        Statement statement = null;
        var sql = new SQLServerDriver();
        var props = new Properties();
        props.put("user", "");
        props.put("password", "");
        props.put("database", "");
        try {
            statement = sql.connect(String.format(""), props).createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return statement;
    }

    private void disconnect(Statement statement) {
        try {
            if (statement != null) {
                statement.getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void execute(String query) {
        Statement statement = connect();
        try {
            statement.execute(query);
        } catch (Exception e) {
            fail("Error executing query: " + query + "\n" + e.getMessage());
        } finally {
            disconnect(statement);
        }
    }

    public HashMap<String, String> getDBDData(String query) {
        Statement statement = connect();

        var resultMap = new HashMap<String, String>();
        try {
            var rs = statement.executeQuery(query);
            var rsmd = rs.getMetaData();
            if (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    resultMap.put(rsmd.getColumnName(i), rs.getString(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect(statement);
        }
        return resultMap;
    }

    public String getStringDBDData(String query) {
        Statement statement = connect();
        String result = null;

        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result = resultSet.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect(statement);
        }
        return result;
    }
}