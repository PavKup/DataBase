import entity.Item;
import entity.Shipping;
import entity.Town;
import repository.ItemsRepository;
import repository.ShippingsRepository;
import repository.TownsRepository;
import service.ItemsService;
import service.ShippingsService;
import service.TownsService;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HelloWorld {
    public static void main(String[] args) {
        final ItemsRepository itemsRepository = new ItemsRepository();
        final TownsRepository townsRepository = new TownsRepository();
        final ShippingsRepository shippingsRepository = new ShippingsRepository();
        final ItemsService itemsService = new ItemsService(itemsRepository);
        final TownsService townsService = new TownsService(townsRepository);
        final ShippingsService shippingsService = new ShippingsService(shippingsRepository, itemsService, townsService);
        createAndFill(itemsService, townsService);

        CommandHandler handler = new CommandHandler(itemsService, townsService, shippingsService);

        for (; ; ) {
            Scanner sc = new Scanner(System.in);

            try {
                handler.handle(sc);
            } catch (Throwable t) {
                System.out.println("Unknown command");
            }
        }
    }

    private static void createAndFill(ItemsService itemsService, TownsService townsService) {
        Set<Map.Entry<Object, Object>> items = SampleDataLoader.readFile("items.txt");
        Set<Map.Entry<Object, Object>> towns = SampleDataLoader.readFile("towns.txt");

        for (Map.Entry<Object, Object> entry : items) {
            itemsService.createItem( (String) entry.getKey(), (String) entry.getValue());
        }

        for (Map.Entry<Object, Object> entry : towns) {
            townsService.createTown( (String) entry.getKey(), (String) entry.getValue());
        }
    }
}

