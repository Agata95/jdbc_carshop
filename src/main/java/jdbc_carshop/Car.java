package jdbc_carshop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Car {
    private Long id;
    private String nr_rejestracyjny;
    private Double przebieg;
    private String marka_model;
    private String rocznik;
    private Types typ;
    private String nazwisko_wlasiciela;

    public Car(String nr_rejestracyjny, Double przebieg, String marka_model,
               String rocznik, Types typ, String nazwisko_wlasiciela) {
        this.nr_rejestracyjny = nr_rejestracyjny;
        this.przebieg = przebieg;
        this.marka_model = marka_model;
        this.rocznik = rocznik;
        this.typ = typ;
        this.nazwisko_wlasiciela = nazwisko_wlasiciela;
    }
}
