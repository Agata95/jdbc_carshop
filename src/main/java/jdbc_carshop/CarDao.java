package jdbc_carshop;

import java.io.IOException;
import java.sql.*;

import static jdbc_carshop.CarQueries.CREATE_TABLE_QUERY;
import static jdbc_carshop.CarQueries.INSERT_QUERY;

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

    public void insertCar(Car car) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, car.getRegistration_no());
                statement.setDouble(2, car.getMileage());
                statement.setString(3, car.getMake_and_model());
                statement.setString(4, car.getYear());
                statement.setString(5, String.valueOf(car.getType()));
                statement.setString(6, car.getSurname_owner());

                int affectedRecords = statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    Long generatedId = resultSet.getLong(1);
                    System.out.println("Car (id: " + generatedId + " ) was insert successfully.");
                }
            }
        }
    }
}
