package jdbc_carshop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Car {
    private Long id;
    private String registration_no;
    private Double mileage;
    private String make_and_model;
    private String year;
    private Types type;
    private String surname_owner;

    public Car(String registration_no, Double mileage, String make_and_model, String year, Types type, String surname_owner) {
        this.registration_no = registration_no;
        this.mileage = mileage;
        this.make_and_model = make_and_model;
        this.year = year;
        this.type = type;
        this.surname_owner = surname_owner;
    }
}
