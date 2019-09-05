package jdbc_carshop;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            CarDao carDao = new CarDao();
            CarOrderDao carOrderDao = new CarOrderDao();
            LoadFromUser load = new LoadFromUser();
            load.application(carDao, carOrderDao);

        } catch (IOException e) {
            System.err.println("Configuration file error.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Mysql error.");
            e.printStackTrace();
        }
    }
}
