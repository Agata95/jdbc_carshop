package jdbc_carshop;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jdbc_carshop.CarQueries.*;

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

    public List<Car> selectCars(String howSelect) throws SQLException {
        List<Car> carList = new ArrayList<>();
        try (Connection connection = mysqlConnection.getConnection()) {
            if (howSelect.contains("id")) {
                String id = howSelect.substring(2);
                try (PreparedStatement statement = connection.prepareStatement(SELECT_ID_QUERY)) {
                    statement.setLong(1, Long.parseLong(id));

                    addCarsToList(carList, statement);
                }
                printListOfCars(carList);
            } else if (howSelect.contains("no")) {

                shortMethodToSelectString(howSelect, carList, connection, 2, SELECT_NO_QUERY);
            } else if (howSelect.contains("surname")) {

                shortMethodToSelectString(howSelect, carList, connection, 7, SELECT_NAME_QUERY);
            } else if (howSelect.contains("mm")) {

                shortMethodToSelectString(howSelect, carList, connection, 2, SELECT_MAKE_MODEL_QUERY);
            }
            return carList;
        }
    }

    private void shortMethodToSelectString(String howSelect, List<Car> carList, Connection connection, int i, String selectNoQuery) throws SQLException {
        String no = howSelect.substring(i);
        try (PreparedStatement statement = connection.prepareStatement(selectNoQuery)) {
            statement.setString(1, no);

            addCarsToList(carList, statement);
        }
        printListOfCars(carList);
    }

    private void printListOfCars(List<Car> carList) {
        for (Car car : carList) {
            System.out.println(car);
        }
    }

    private void addCarsToList(List<Car> carList, PreparedStatement statement) throws SQLException {
        statement.execute();

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Car car = getCars(resultSet);
            carList.add(car);
        }
    }

    public List<Car> listAllCars() throws SQLException {
        List<Car> carList = new ArrayList<>();
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Car car = getCars(resultSet);
                    carList.add(car);
                }
            }
            printListOfCars(carList);
        }
        return carList;
    }

    private Car getCars(ResultSet resultSet) throws SQLException {
        Car car = new Car();

        car.setId(resultSet.getLong(1));
        car.setRegistration_no(resultSet.getString(2));
        car.setMileage(resultSet.getDouble(3));
        car.setMake_and_model(resultSet.getString(4));
        car.setYear(resultSet.getString(5));
        car.setType(Types.valueOf(resultSet.getString(6)));
        car.setSurname_owner(resultSet.getString(7));

        return car;
    }

    public Boolean removeCar(String howRemove) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            if (howRemove.contains("id")) {
                String id = howRemove.substring(2);

                try (PreparedStatement statement = connection.prepareStatement(REMOVE_QUERY_ID)) {
                    statement.setLong(1, Long.parseLong(id));

                    int affectedRecords = statement.executeUpdate();
                    if (affectedRecords > 0) {
                        System.out.println("Car was remove.");
                        return true;
                    }
                }
            } else if (howRemove.contains("no")) {
                String no = howRemove.substring(2);
                try (PreparedStatement statement = connection.prepareStatement(REMOVE_QUERY_NO)) {
                    statement.setString(1, no);

                    int affectedRecords = statement.executeUpdate();
                    if (affectedRecords > 0) {
                        System.out.println("Car was remove.");
                        return true;
                    }
                }
            }
        }
        System.out.println("Car wasn't remove. This id or registration number doesn't exists.");
        return false;
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

                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    Long generatedId = resultSet.getLong(1);
                    System.out.println("Car (id: " + generatedId + " ) was insert successfully.");
                }
            }
        }
    }
}
