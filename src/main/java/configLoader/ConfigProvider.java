package configLoader;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {

    static Config readConfig(){
        String profile = System.getProperty("env", "dev");
        String configFile = profile + ".properties";
        try {
            return ConfigFactory.load(profile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration file: " + configFile, e);
        }
    }

    // Получение профиля (env)
    static String getProfile() {
        return System.getProperty("env", "dev");
    }

    // Получение имени файла конфигурации
    static String getConfigFileName() {
        return getProfile() + ".properties";
    }

    // Методы для безопасного получения значений с дефолтными значениями
    static String getString(String path, String defaultValue) {
        try {
            Config config = readConfig();
            return config.hasPath(path) ? config.getString(path) : defaultValue;
        } catch (ConfigException e) {
            return defaultValue;
        }
    }

    static Config readConfig(String conf){
        return ConfigFactory.load(conf);
    }
}
