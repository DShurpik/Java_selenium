import basePages.BaseTest;
import configLoader.ConfigProvider;
import models.ResponseData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LinksPage;
import testData.TestData;

import java.util.List;

import static pageObjects.Navigation.ELEMENTS;
import static pageObjects.Navigation.LINKS;

public class LinksTests extends BaseTest {

    @Test(dataProviderClass = TestData.class, dataProvider = "linkTests")
    public void testLinkResponse(String endpoint, int expectedStatus, String clickMethod) throws Exception {
        LinksPage linksPage = new LinksPage();

        linksPage.enableNetworkInterceptor();

        linksPage.clearInterceptedData();

        String targetUrl = ConfigProvider.readConfig().getString("url.api") + endpoint;
        linksPage.addRequestListener(endpoint);
        linksPage.addResponseListener(endpoint);

        linksPage.open();
        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        LinksPage.class.getMethod(clickMethod).invoke(linksPage);
        linksPage.waitForResponse(endpoint);

        List<ResponseData> resp = linksPage.getInterceptedResponses();
        Assert.assertFalse(resp.isEmpty(), "No responses intercepted for URL: " + targetUrl);
        Assert.assertEquals(resp.get(0).getStatusCode(), expectedStatus, "Unexpected status code for " + endpoint);
    }
}
