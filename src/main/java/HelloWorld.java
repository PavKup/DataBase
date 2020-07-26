import java.sql.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HelloWorld {
    public static void main(String[] args) {
        DatabaseConnection.initializeDriver();

        if (!DatabaseConnection.isDatabaseFilled()) {
            createAndFill();
        }

        for (;;) {
            Scanner sc = new Scanner(System.in);

            try {
                handleCommand(sc, sc.next());
            } catch (Throwable t) {
                System.out.println("Unknown command");
            }
        }
    }

    private static void handleCommand(Scanner sc, String command) {
        System.out.println("Command: " + command);
        switch (command) {
            case "add_item":
                String name = sc.next();
                int quantity = sc.nextInt();

                System.out.println(name + " " + quantity);
                DatabaseConnection.insertItem(name, String.valueOf(quantity));
                break;
            case "add_town":
                String townName = sc.next();
                int distance = sc.nextInt();

                System.out.println(townName + " " + distance);
                DatabaseConnection.insertTown(townName, String.valueOf(distance));
                break;
            case "add_shipping":
                int itemId = sc.nextInt();
                int townId = sc.nextInt();
                Date startDate = Date.valueOf(sc.next());
                Date endDate;
                if (sc.hasNext()) {
                     endDate = Date.valueOf(sc.next());
                } else {
                    endDate = null;
                }
                DatabaseConnection.insertShipping(itemId, townId, startDate, endDate);
                break;
            case "delete_item":
                break;
            case "delete_town":
                break;
            case "delete_shipping":
                break;
            case "show_items":
                DatabaseConnection.printItems();
                break;
            case "show_towns":
                DatabaseConnection.printTowns();
                break;
            case "show_shippings":
                DatabaseConnection.printShippings();
                break;
            default:
                throw new RuntimeException();
        }
    }

    private static void createAndFill() {
        DatabaseConnection.createItems();
        DatabaseConnection.createTowns();
        DatabaseConnection.createShippings();

        Set<Map.Entry<Object, Object>> items = SampleDataLoader.readFile("items.txt");
        Set<Map.Entry<Object, Object>> towns = SampleDataLoader.readFile("towns.txt");

        for (Map.Entry<Object, Object> entry : items) {
            DatabaseConnection.insertItem((String) entry.getKey(), (String) entry.getValue());
        }

        for (Map.Entry<Object, Object> entry : towns) {
               DatabaseConnection.insertTown((String) entry.getKey(), (String) entry.getValue());
        }
    }
}

