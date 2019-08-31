package jdbc_carshop;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LoadFromUser {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Application for user, what user want to do.
     */
    public void application(CarDao carDao) throws SQLException {
        String command = null;
        do {
            System.out.println("What do you want to do?\n1. Add new car (add)\n2. Remove car from the database (delete)" +
                    "\n3. List all database (list)\n4. Search cars in database (select)\n4. Quit (quit)");
            command = scanner.nextLine();

            switch (command) {
                case "add":
                    carDao.insertCar(createCar());
                    break;
                case "delete":
                    break;
                case "select":
                    break;
            }


        } while (!command.equalsIgnoreCase("quit"));
    }

    /**
     * Load from user informations about car to insert.
     */
    public Car createCar() {
        Car car = new Car();
        System.out.println("Fill few informations about new car.\nRegistration number is:");
        car.setRegistration_no(scanner.nextLine());

        System.out.println("Mileage is:");
        car.setMileage(Double.valueOf(scanner.nextLine()));

        System.out.println("Make and model is:");
        car.setMake_and_model(scanner.nextLine());

        System.out.println("Year is:");
        car.setYear(scanner.nextLine());

        System.out.println("Select type from the list.");
        enumValue(car);

        System.out.println("Owner's surname is:");
        car.setSurname_owner(scanner.nextLine());

        return car;
    }

    private void enumValue(Car car) {
        List<Types> types = Arrays.asList(Types.values());
        types.forEach(System.out::println);
        String type = scanner.nextLine();
        if (type.equalsIgnoreCase(String.valueOf(Types.CABRIO))) {
            car.setType(Types.CABRIO);
        } else if (type.equalsIgnoreCase(String.valueOf(Types.KOMBI))) {
            car.setType(Types.KOMBI);
        } else if (type.equalsIgnoreCase(String.valueOf(Types.HATCHBACK))) {
            car.setType(Types.HATCHBACK);
        } else if (type.equalsIgnoreCase(String.valueOf(Types.SEDAN))) {
            car.setType(Types.SEDAN);
        } else if (type.equalsIgnoreCase(String.valueOf(Types.SUV))) {
            car.setType(Types.SUV);
        } else {
            System.out.println("Wrong answer. Please select type from the list.");
            enumValue(car);
        }
    }
}
