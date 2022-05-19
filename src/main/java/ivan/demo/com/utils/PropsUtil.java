package ivan.demo.com.utils;

import com.google.common.collect.Maps;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public final class PropsUtil {
    private static final HashMap<String, String> SYSTEM_PROPS
            = Maps.newHashMap(Maps.fromProperties(System.getProperties()));
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

        properties.keySet().forEach(it -> overrideFromSystemProps(properties, it.toString()));
        return properties;
    }

    private static void overrideFromSystemProps(Properties properties, String key) {
        if (SYSTEM_PROPS.get(key) != null) {
            properties.setProperty(key, SYSTEM_PROPS.get(key));
        }
    }
}
