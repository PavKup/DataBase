package repository;

import entity.Shipping;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingsRepository {
    private final Map<String, Shipping> shippings;

    public ShippingsRepository() { this.shippings = new HashMap<>();
    }

    public List<Shipping> findAll() {
        return new ArrayList<>(shippings.values());
    }

    public void save(Shipping shipping) {
        shippings.put(shipping  .getId(), shipping);
    }

    public void remove(String id) {
        shippings.remove(id);
    }

    public Shipping findOne(String id) {
        return shippings.get(id);
    }

}
