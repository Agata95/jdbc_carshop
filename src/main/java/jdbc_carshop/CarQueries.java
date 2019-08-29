package jdbc_carshop;

public interface CarQueries {
    String CREATE_TABLE_QUERY = "create table if not exists `cars`(\n" +
            "`id` int auto_increment,\n" +
            "`registration_no` varchar(255) not null,\n" +
            "`mileage` double not null,\n" +
            "`make_and_model` varchar(255) not null,\n" +
            "`year` varchar(5) not null,\n" +
            "`type` varchar(25) not null,\n" +
            "`surname_owner` varchar(45) not null,\n" +
            "primary key(id)\n" +
            ");";

    String INSERT_QUERY = "insert into `cars`(`registration_no`, `mileage`, " +
            "`make_and_model`, `year`, `type`, `surname_owner`) values " +
            "(?, ?, ?, ?, ?, ?);";

    String REMOVE_QUERY_ID = "delete from `cars` " +
            "where `id` = ?;";

    String REMOVE_QUERY_NO = "delete from `cars` " +
            "where `registration_no` = ?;";

    String SELECT_ALL_QUERY = "select * from `cars`;";

    String SELECT_ID_QUERY = "select * from `cars` where `id` = ?;";

    String SELECT_NO_QUERY = "select * from `cars` where `registration_no` like ?;";

    String SELECT_NAME_QUERY = "select * from `cars` where `surname_owner` like ?;";

    String SELECT_MAKE_MODEL_QUERY = "select * from `cars` where `make_and_model` like ?;";

}
