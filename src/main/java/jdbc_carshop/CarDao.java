package jdbc_carshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static jdbc_carshop.CarQueries.CREATE_TABLE_QUERY;

public class CarDao {
    private MysqlConnection mysqlConnection;

    public CarDao() throws IOException, SQLException {
        mysqlConnection = new MysqlConnection();

        createTableIfNotExists();
    }

    private void createTableIfNotExists() throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_QUERY)) {
                statement.execute();
            }
        }
    }
}
