package jdbc_carshop;

public interface CarOrderQueries {
    String CREATE_TABLE_ORDER_QUERY = "create table if not exists `orders`(\n" +
            "`id` int auto_increment,\n" +
            "`addDate` DATETIME  not null,\n" +
            "`isDone` tinyint default 0,\n" +
            "`doneDate` DATETIME default null,\n" +
            "`orderContent` varchar(300) not null,\n" +
            "`carId` int not null,\n" +
            "primary key(id), \n" +
            "foreign key (carId) references `cars` (id)" +
            ");";

    String INSERT_QUERY = "insert into `orders`(`addDate`, `orderContent`, " +
            "`carId`) values (now(), ?, ?);";

    String UPDATE_ORDER_IS_DONE_QUERY = "update `orders` set `isDone` = 1, `doneDate` = now()" +
            "where (id = ? and carId = ?)";

    String SELECT_BY_ID_QUERY = "select * from `orders` where `carId` = ?;";

    String SELECT_IS_NOT_DONE_QUERY = "select * from `orders` where `isDone` = 0;";

    String SELECT_IS_DONE_QUERY = "select * from `orders` where `isDone` = 1;";

    String SELECT_ORDERS_BY_DAYS_QUERY = "select * from `orders` where " +
            "`addDate` >= now() - interval ? day;";
}
