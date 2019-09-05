package jdbc_carshop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarOrder {
    private Long id;
    private LocalDateTime addDate;
    private Boolean isDone;
    private LocalDateTime doneDate;
    private String orderContent;
    private Long carId;

    /**
     * This constructor contains just orderContent and carId, because this objects are required
     * in create table query.
     */
    public CarOrder(String orderContent, Long carId) {
        this.orderContent = orderContent;
        this.carId = carId;
    }
}
