package ivan.demo.com.utils;

import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropsUtil {
    private static Properties properties;

    private PropsUtil() {
    }

    public static Properties getProps() {
        if (properties == null) {
            properties = readProperties();
        }
        return properties;
    }

    private static Properties readProperties() {
        PropertyConfigurator.configure(
                System.getProperty("user.dir") + "/src/main/resources/log4j.properties");
        properties = new Properties();
        FileInputStream file;
        try {
            file = new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/resources/global.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
