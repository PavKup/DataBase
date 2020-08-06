package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class Town {
    private String id;
    private String name;
    private int distance;

    public Town() {
        this.id = UUID.randomUUID().toString();
    }
}
