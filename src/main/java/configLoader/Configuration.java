package configLoader;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config-dev.properties",
        "classpath:allure.properties"
})
public interface Configuration extends Config {

    @Key("allure.results.directory")
    String allureResultsDir();

    @Key("base.url")
    String baseUrl();

    @Key("browser")
    String browser();

    @Key("browser.window.width")
    int browserWindowWidth();

    @Key("browser.window.height")
    int browserWindowHeight();


}
