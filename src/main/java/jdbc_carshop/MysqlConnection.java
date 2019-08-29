package jdbc_carshop;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlConnection {
    private MysqlConnctionParameters parameters;
    private MysqlDataSource dataSource;

    /**
     * This method use MysqlConnectionParameters class to take informations from file
     * and put this information to our Database.
     */
    public MysqlConnection() throws IOException {
        parameters = new MysqlConnctionParameters();
        initialize();
    }

    /**
     * This method use dataSource to load parameters from MysqlConnectionParameters class.
     * Database must have additional parameters like ServerTimezone and UseSSL.
     */
    private void initialize() {
        dataSource = new MysqlDataSource();

        dataSource.setServerName(parameters.getDbHost());
        dataSource.setPort(Integer.parseInt(parameters.getDbPort()));
        dataSource.setDatabaseName(parameters.getDbName());
        dataSource.setUser(parameters.getDbUsername());
        dataSource.setPassword(parameters.getDbPassword());

        try {
            dataSource.setServerTimezone("Europe/Warsaw");
            dataSource.setUseSSL(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method return connection with database.
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
