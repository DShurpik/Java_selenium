package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static Properties properties;

    public PropertyReader(String propertyName) {
        properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("src/main/resources/" + propertyName + ".properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getProperty(String key, String defaultValue) {
        return System.getProperty(key, properties.getProperty(key, defaultValue));
    }
}
