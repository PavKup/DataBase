package Entity;

import java.util.UUID;

public class Item {
    private String id;
     private String name;
     private  int quantity;

  public Item() {
      this.id = UUID.randomUUID().toString();
  }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
