package configLoader;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ConfigLoader {
    private final Properties properties = new Properties();

    public ConfigLoader() {
        this(System.getProperty("profile", "dev"));
    }

    public ConfigLoader(String profile) {
        if (profile == null) {
            profile = System.getProperty("profile", "dev");
        }
        String configFile = "c-" + profile + ".properties";
        log.info("Loading system configuration for profile: {}", profile);
        try {
            FileInputStream input = new FileInputStream(
                    ConfigLoader.class.getClassLoader().getResource(configFile).getFile());
            properties.load(input);
            input.close();
            log.info("System configuration loaded from: {}", configFile);
        } catch (IOException | NullPointerException e) {
            log.error("Failed to load system configuration file: {}", configFile, e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return System.getProperty(key, properties.getProperty(key, defaultValue));
    }
}
