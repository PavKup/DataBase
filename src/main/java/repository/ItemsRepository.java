package repository;

import Entity.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsRepository {
    private Map <String, Item> items;



    public ItemsRepository() {
        this.items = new HashMap<>();
    }

    public List<Item> findAll(){
        List<Item> itemsList = new ArrayList<>(items.values());
        return itemsList;
    }

    public void save(Item item){
        items.put(item.getId(), item);
    }
}
