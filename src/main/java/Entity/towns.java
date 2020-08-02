package Entity;

import java.util.UUID;

public class towns {
    private String id;
    private String name;
    private  int distance;

    public Towns() {
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int distance) {
        this.distance = distance;
    }
}
