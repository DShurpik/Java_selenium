package configLoader;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ConfigLoader {
    private static Properties properties = new Properties();

    static {
        String profile = System.getProperty("profile", "dev");
        try {
            log.info("Loading configuration for profile: " + profile);
            String configFile = "c-" + profile + ".properties";
            FileInputStream input = new FileInputStream(
                    ConfigLoader.class.getClassLoader().getResource(configFile).getFile());
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return System.getProperty(key, properties.getProperty(key, defaultValue));
    }
}
