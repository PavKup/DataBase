package service;

import entity.Item;
import repository.ItemsRepository;

import java.util.List;

public class ItemsService {

    ItemsRepository itemsRepository;

    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }


    public void createItem(String name, String quantity) {
        Item item = new Item();
        item.setName(name);
        try {
            int quantityInt = Integer.parseInt(quantity);
            item.setQuantity(quantityInt);
        } catch (NumberFormatException e) {
            System.out.println("Братан, этоне перевести!");
            return;
        }
        itemsRepository.save(item);
    }

    public void deleteItem(String id) {
        itemsRepository.remove(id);
    }

    public List<Item> showItems() {
        return itemsRepository.findAll();
    }

    public Item findOne(String id) {
        return itemsRepository.findOne(id);
    }
}
