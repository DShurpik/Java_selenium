import configLoader.ConfigLoader;
import org.testng.annotations.Test;

public class Just {

    @Test
    public void justTest() {
        String url = ConfigLoader.getProperty("url");
        System.out.println(url);
    }
}
