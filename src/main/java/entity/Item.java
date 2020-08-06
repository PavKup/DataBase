package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class Item {
    private String id;
    private String name;
    private int quantity;

    public Item() {
        this.id = UUID.randomUUID().toString();
    }
}
