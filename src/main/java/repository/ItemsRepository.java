package repository;

import entity.Item;

import java.util.*;

public class ItemsRepository {
    private final Map<String, Item> items;

    public ItemsRepository() {
        this.items = new HashMap<>();
    }

    public List<Item> findAll() {
        return new ArrayList<>(items.values());
    }

    public void save(Item item) {
        items.put(item.getId(), item);
    }

    public void remove(String id) {
        items.remove(id);
    }

    public Item findOne(String id) {
        return items.get(id);
    }
}
