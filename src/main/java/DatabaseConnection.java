import java.sql.*;

public class DatabaseConnection {

    private static Connection connection;

    public static void initializeDriver() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:./database", "pasha", "kris");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() {
        return connection;
    }

    public static boolean isDatabaseFilled() {
        try {
            DatabaseMetaData dbm = getConnection().getMetaData();
            ResultSet tables = dbm.getTables(null, null, "ITEMS", null);
            return tables.next();
        } catch (SQLException throwables) {
            System.out.println(throwables);
            return false;
        }
    }

    public static void createItems() { //заполнение таблицы items
        Statement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS ITEMS " +
                "(item_id int NOT NULL AUTO_INCREMENT," +
                " item_name VARCHAR(30), " +
                " quantity INT UNSIGNED,  " +
                " PRIMARY KEY ( item_id ))";

        try {
            stmt = getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void createTowns(){
        Statement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS TOWNS " +
                "(town_id int NOT NULL AUTO_INCREMENT," +
                " town_name VARCHAR(30), " +
                " distance INT UNSIGNED,  " +
                " PRIMARY KEY ( town_id ))";

        try {
            stmt = getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createShippings(){
        Statement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS SHIPPINGS " +
                "(shipping_id int NOT NULL AUTO_INCREMENT," +
                " item_id INT, " +
                " town_id INT, " +
                " start_date DATE , "+
                " end_date DATE ,"+
                " PRIMARY KEY ( shipping_id ), " +
                " FOREIGN KEY (item_id) REFERENCES ITEMS (item_id), " +
                " FOREIGN KEY (town_id) REFERENCES TOWNS (town_id) )";

        try {
            stmt = getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertItem(String name, String quantity){
        Statement stmt = null;
        String sql = "INSERT INTO ITEMS (item_name, quantity)" + " VALUES" + "('" + name + "','" + quantity + "')";
        try {
            stmt = getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertTown(String name, String distance) {
        String sql = "INSERT INTO TOWNS (town_name, distance)" + " VALUES" + "('" + name + "','" + distance + "')";
        try {
            final Statement stmt = getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertShipping(int itemId, int townId, Date startDate, Date endDate) {
        if (endDate != null) {
            if (startDate.after(endDate)) {
                throw new RuntimeException("Неверная дата");
            }
        }

        String sql = "INSERT INTO SHIPPINGS (item_id, town_id, start_date, end_date)" + " VALUES" + "('"
                + itemId + "','" + townId + "','" + startDate.toString() + "','" + (endDate == null ? "NULL" : endDate.toString()) + "')";
        try {
            final Statement stmt = getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printItems() {
        Statement stmt = null;
        String sql = "SELECT * FROM ITEMS";
        try {
            stmt = getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                int itemId = result.getInt("item_id");
                String itemName = result.getString("item_name");
                int quantity = result.getInt("quantity");

                System.out.println(itemId + " " + itemName + " " + quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printTowns() {
        String sql = "SELECT * FROM TOWNS";
        try {
        Statement stmt = getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                int itemId = result.getInt("town_id");
                String itemName = result.getString("town_name");
                int quantity = result.getInt("distance");

                System.out.println(itemId + " " + itemName + " " + quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printShippings() {
        String sql = "SELECT * FROM SHIPPINGS";
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                int shippingId = result.getInt("shipping_id");
                String itemId = result.getString("item_id");
                int townId = result.getInt("town_id");
                Date startDate = result.getDate("start_date");
                Date endDate = result.getDate("end_date");

                System.out.println(shippingId + " " + itemId + " " + townId + " " + startDate + " " + endDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
