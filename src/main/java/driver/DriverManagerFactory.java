package driver;

public class DriverManagerFactory {
    public static DriverManager getManager(String driverManagerType) {
        return switch (driverManagerType) {
            case "CHROME" -> new ChromeDriverManager();
            case "FIREFOX" -> new FireFoxDriverManager();
            default -> throw new IllegalArgumentException("Unsupported browser: " + driverManagerType);
        };
    }
}
