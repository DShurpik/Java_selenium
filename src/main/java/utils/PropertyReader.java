package utils;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class PropertyReader {

    private static PropertyReader instance = null;
    private final Properties properties = new Properties();

    public PropertyReader(String propertyName) {
        String fileName = propertyName + ".properties";
        FileInputStream inputStream;
        log.info("Loading user data from: {}", fileName);
        try {
            inputStream = new FileInputStream("src/main/resources/" + propertyName + ".properties");
            properties.load(inputStream);
            inputStream.close();
            log.info("User data loaded from: {}", fileName);
        } catch (IOException | NullPointerException e) {
            log.error("Failed to load user data file: {}", fileName, e);
        }
    }

    public static PropertyReader getInstance() {
        if (instance == null) {
            String propertyName = System.getProperty("config");
            instance = new PropertyReader(propertyName);
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getProperty(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }

    public String getProperty(String key, String defaultValue) {
        return System.getProperty(key, properties.getProperty(key, defaultValue));
    }
}
