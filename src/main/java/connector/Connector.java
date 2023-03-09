package connector;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private static final String FLUSH = "call flush();";

    public Connector(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
    public Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void flush(){
        try(CallableStatement callableStatement=getConnection().prepareCall(FLUSH)) {
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
