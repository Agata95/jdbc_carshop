package jdbc_carshop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LoadFromUser {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Application for user, what user want to do.
     */
    public void application(CarDao carDao, CarOrderDao carOrderDao) throws SQLException, IOException {
        String command = null;
        do {
            System.out.println("What do you want to do?\n1. Add new (add)\n2. Remove from the database (delete)" +
                    "\n3. List all cars database (list)\n4. Search in database (select)\n4. Quit (quit)");
            command = scanner.nextLine();

            switch (command) {
                case "add":
                    add(carDao, carOrderDao);
                    break;
                case "delete":
                    delete(carDao, carOrderDao);
                    break;
                case "list":
                    carDao.listAllCars();
                    break;
                case "select":
                    select(carDao, carOrderDao);
                    break;
            }
        } while (!command.equalsIgnoreCase("quit"));
    }

    private void select(CarDao carDao, CarOrderDao carOrderDao) throws SQLException {
        System.out.println("Car or Order? (car) / (order)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("car")) {
            carDao.selectCars(howSelect());
        } else if (choice.equalsIgnoreCase("order")) {
            carOrderDao.selectOrders(howSelectOrders());
        } else select(carDao, carOrderDao);
    }

    private void delete(CarDao carDao, CarOrderDao carOrderDao) throws SQLException {
        System.out.println("Car or Order? (car) / (order)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("car")) {
            carDao.removeCar(howRemoveCar());
        } else if (choice.equalsIgnoreCase("order")) {

        } else delete(carDao, carOrderDao);
    }

    private void add(CarDao carDao, CarOrderDao carOrderDao) throws SQLException, IOException {
        System.out.println("Car or Order? (car) / (order)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("car")) {
            carDao.insertCar(createCar());
        } else if (choice.equalsIgnoreCase("order")) {
            carOrderDao.insertOrders(createOrder());
        } else add(carDao, carOrderDao);
    }

    private CarOrder createOrder() {
        CarOrder carOrder = new CarOrder();
        System.out.println("Fill few informations about new order.\nWrite order content:");
        carOrder.setOrderContent(scanner.nextLine());
        String carId = whichId();

        carOrder.setCarId(Long.valueOf(carId));

        return carOrder;
    }

    private String howSelectOrders() {
        System.out.println("Select orders:\n1. by car's id (id)\n" +
                "2. which are done (done)\n3. which are not done (not)\n" +
                "4. by few days age (days)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("id")) {
            String id = whichId();
            return "id" + id;
        } else if (choice.equalsIgnoreCase("done")) {
            return "done";
        } else if (choice.equalsIgnoreCase("not")) {
            return "not";
        } else if (choice.equalsIgnoreCase("days")) {
            String days = howManyDays();
            return "days" + days;
        } else {
            System.out.println("Wrong answer. Try again.");
            return howSelectOrders();
        }
    }

    private String howManyDays() {
        System.out.println("Write how many days ago:");
        return scanner.nextLine();
    }

    /**
     * This method ask user how select list of cars.
     */
    private String howSelect() {
        System.out.println("How want to select cars:\n1. by id (id)\n" +
                "2. by registration number (no)\n3. by owner's surname (surname)\n" +
                "4. by make and model (mm)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("id")) {
            String id = whichId();
            return "id" + id;
        } else if (choice.equalsIgnoreCase("no")) {
            String no = whichNumber();
            return "no" + no;
        } else if (choice.equalsIgnoreCase("surname")) {
            String surname = whichSurname();
            return "surname" + surname;
        } else if (choice.equalsIgnoreCase("mm")) {
            String mm = whichMakeModel();
            return "mm" + mm;
        } else {
            System.out.println("Wrong answer. Try again.");
            return howSelect();
        }
    }

    private String whichMakeModel() {
        System.out.println("Write car's make and model:");
        return scanner.nextLine();
    }

    private String whichSurname() {
        System.out.println("Write owner's surname:");
        return scanner.nextLine();
    }

    /**
     * This method ask user how delete the car (by id ora by registration number).
     */
    public String howRemoveCar() {
        System.out.println("Select how want to delete the car: by id (id) / by registration number (no).");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("id")) {
            String id = whichId();
            return "id" + id;
        } else if (choice.equalsIgnoreCase("no")) {
            String no = whichNumber();
            return "no" + no;
        } else {
            System.out.println("Wrong answer. Try again.");
            return howRemoveCar();
        }
    }

    public String whichId() {
        System.out.println("Write car's id:");
        return scanner.nextLine();
    }

    public String whichNumber() {
        System.out.println("Write car's registration number:");
        return scanner.nextLine();
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
