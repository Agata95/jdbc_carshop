package jdbc_carshop;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jdbc_carshop.CarOrderQueries.*;


public class CarOrderDao {
    private MysqlConnection mysqlConnection;

    public CarOrderDao() throws IOException, SQLException {
        mysqlConnection = new MysqlConnection();

        createTableIfNotExists();
    }

    private void createTableIfNotExists() throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_ORDER_QUERY)) {
                statement.execute();
            }
        }
    }

    public void insertOrders(CarOrder carOrder) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, carOrder.getOrderContent());
                statement.setLong(2, carOrder.getCarId());

                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    Long generatedId = resultSet.getLong(1);
                    System.out.println("Order (id: " + generatedId + ") was insert successfully.");
                }
            }
        }
    }

    public List<CarOrder> selectOrders(String howSelect) throws SQLException {
        List<CarOrder> carOrderList = new ArrayList<>();
        try (Connection connection = mysqlConnection.getConnection()) {
            if (howSelect.equalsIgnoreCase("id")) {
                String id = howSelect.substring(2);
                try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
                    statement.setLong(1, Long.parseLong(id));

                    addOrdersToList(carOrderList, statement);
                }
                printListOfOrders(carOrderList);
            } else if (howSelect.equalsIgnoreCase("done")) {
                try (PreparedStatement statement = connection.prepareStatement(SELECT_IS_DONE_QUERY)) {

                    addOrdersToList(carOrderList, statement);
                }
                printListOfOrders(carOrderList);
            } else if (howSelect.equalsIgnoreCase("not")) {
                try (PreparedStatement statement = connection.prepareStatement(SELECT_IS_NOT_DONE_QUERY)) {

                    addOrdersToList(carOrderList, statement);
                }
                printListOfOrders(carOrderList);
            } else if (howSelect.equalsIgnoreCase("days")) {
                String days = howSelect.substring(4);
                try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDERS_BY_DAYS_QUERY)) {

                    statement.setInt(1, Integer.parseInt(days));

                    addOrdersToList(carOrderList, statement);
                }
                printListOfOrders(carOrderList);
            }

        }
        return carOrderList;
    }


    private void printListOfOrders(List<CarOrder> carOrderList) {
        for (CarOrder carOrder : carOrderList) {
            System.out.println(carOrder);
        }

    }

    private void addOrdersToList(List<CarOrder> carOrderList, PreparedStatement statement) throws SQLException {
        statement.execute();

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            CarOrder carOrder = getOrders(resultSet);
            carOrderList.add(carOrder);
        }
    }

    private CarOrder getOrders(ResultSet resultSet) throws SQLException {
        CarOrder carOrder = new CarOrder();

        carOrder.setId(resultSet.getLong(1));
        carOrder.setAddDate(resultSet.getObject(2, LocalDateTime.class));
        carOrder.setIsDone(resultSet.getBoolean(3));
        carOrder.setDoneDate(resultSet.getObject(4, LocalDateTime.class));
        carOrder.setOrderContent(resultSet.getString(5));
        carOrder.setCarId(resultSet.getLong(6));
        return carOrder;
    }
}
