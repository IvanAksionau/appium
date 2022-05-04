package ivan.demo.com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropsUtil {
    private static Properties properties;

    private PropsUtil() {
    }

    public static Properties getProperties() {
        if (properties == null) {
            properties = readProperties();
        }
        return properties;
    }

    private static Properties readProperties() {
        properties = new Properties();
        FileInputStream file;
        try {
            file = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/global.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
