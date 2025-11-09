package utils;

import configLoader.ConfigProvider;
import driver.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Capabilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

@Log4j2
public class AllureEnvironmentWriter {

    public static void writeEnvironment() {
        Properties props = new Properties();
        props.setProperty("OS", System.getProperty("os.name"));
        props.setProperty("OS.Version", System.getProperty("os.version"));
        props.setProperty("Environment", getEnv(ConfigProvider.getProfile(), "undefined"));
        props.setProperty("Java.Version", System.getProperty("java.version"));

        Capabilities caps = DriverManager.getLastCapabilities();
        if (caps != null) {
            props.setProperty("Browser", getEnv(caps.getBrowserName(), "unknown"));
            props.setProperty("Browser.Version", getEnv(caps.getBrowserVersion(), "unknown"));
        } else {
            props.setProperty("Browser", "unknown");
            props.setProperty("Browser.Version", "unknown");
        }

        String resultsDirPath = System.getProperty("user.dir") + "/target/allure-results";
        File resultsDir = new File(resultsDirPath);
        resultsDir.mkdirs();

        File file = new File(resultsDir, "environment.properties");

        try (OutputStream output = new FileOutputStream(file)) {
            props.store(output, "Allure Environment Properties");
            log.info("environment.properties created at: {}", file.getAbsolutePath());
        } catch (IOException e) {
            log.error("Failed to write environment.properties: {}", e.getMessage());
        }
    }

    private static String getEnv(String value, String defaultValue) {
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }
}
