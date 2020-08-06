import entity.Item;
import entity.Shipping;
import entity.Town;
import repository.ItemsRepository;
import repository.ShippingsRepository;
import repository.TownsRepository;
import service.ItemsService;
import service.ShippingsService;
import service.TownsService;

import java.sql.Date;
import java.util.Collection;
import java.util.Scanner;

public class CommandHandler {

    private final ItemsService itemsService;
    private final TownsService townsService;
    private final ShippingsService shippingsService;

    public CommandHandler(
            ItemsService itemsService,
            TownsService townsService,
            ShippingsService shippingsService
    ) {
        this.itemsService = itemsService;
        this.townsService = townsService;
        this.shippingsService = shippingsService;
    }

    public void handle(Scanner scanner) {
        final String command = scanner.next();
        System.out.println("Command: " + command);
        switch (command) {
            case "add_item":
                String name = scanner.next();
                String quantity = scanner.next();
                itemsService.createItem(name, quantity);
                System.out.printf("Вы успешно добавили %s со значением %s", name, quantity);
                break;
            case "add_town":
                String townName = scanner.next();
                String distance = scanner.next();
                townsService.createTown(townName, distance);
                System.out.printf("Вы успешно добавили %s со значением %s", townName, distance);
                break;
            case "add_shipping":
                String itemId = scanner.next();
                String townId = scanner.next();
                String startDate = scanner.next();
                String endDate = scanner.next();

                shippingsService.createShipping(townId, itemId, startDate, endDate);
                break;
            case "delete_item":
                String idToRemoveItem = scanner.next();
                itemsService.deleteItem(idToRemoveItem);
                break;
            case "delete_town":
                String idToRemoveTown = scanner.next();
                townsService.deleteTown(idToRemoveTown);
                break;
            case "delete_shipping":
                String idToRemoveShipping = scanner.next();
                shippingsService.deleteShipping(idToRemoveShipping);
                break;
            case "show_items":
                Collection<Item> items = itemsService.showItems();
                printItems(items);
                break;
            case "show_towns":
                Collection<Town> towns = townsService.showTown();
                printTowns(towns);
                break;
            case "show_shippings":
                Collection<Shipping> shippings = shippingsService.showShippings();
                printShippings(shippings);
                break;
            default:
                throw new RuntimeException();
        }
    }

    private void printItems(Collection<Item> items) {
        for (Item item : items) {
            System.out.println(item.getId() + " " + item.getName() + " " + item.getQuantity());
        }
    }

    private void printTowns(Collection<Town> towns) {
        for (Town town : towns) {
            System.out.println(town.getId() + " " + town.getName() + " " + town.getDistance());
        }
    }

    public void printShippings(Collection<Shipping> shippings){
        for (Shipping shipping : shippings){
            System.out.println(shipping.getId() + " " + shipping.getTowns_id() + " " + shipping.getItems_id() + " " + shipping.getStart_date() + " " + shipping.getEnd_date());
        }
    }
}
