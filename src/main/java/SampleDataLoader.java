import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class SampleDataLoader {
    public static Set<Map.Entry<Object, Object>> readFile(String fileName) {
        final String appConfigPath = "./"  + fileName ;
        Properties itemsProps = new Properties();
        try {
            itemsProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemsProps.entrySet();
    }

}
