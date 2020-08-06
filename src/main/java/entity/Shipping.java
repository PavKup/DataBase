package entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter

public class Shipping {
    private String id;
    private String items_id;
    private String towns_id;

    private LocalDate start_date;
    private LocalDate end_date;

    public Shipping(String items_id, String towns_id) {
        this.id = UUID.randomUUID().toString();
        this.items_id = items_id;
        this.towns_id = towns_id;
    }
}
