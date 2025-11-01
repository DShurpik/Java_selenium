package pageObjects;

import basePages.BasePage;
import driver.DriverManager;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.ResponseData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v141.network.Network;
import org.openqa.selenium.devtools.v141.network.model.Request;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

@Log4j2
public class LinksPage extends BasePage {

    @FindBy(id = "created")
    private WebElement createdLink;

    @FindBy(id = "no-content")
    private WebElement noContentLink;

    @FindBy(id = "moved")
    private WebElement movedLink;

    @FindBy(id = "bad-request")
    private WebElement badRequestLink;

    @FindBy(id = "unauthorized")
    private WebElement unauthorizedLink;

    @FindBy(id = "forbidden")
    private WebElement forbiddenLink;

    @FindBy(id = "invalid-url")
    private WebElement notFoundLink;

    protected DevTools devTools;
    private final List<Request> interceptedRequests = new ArrayList<>();
    private final List<ResponseData> interceptedResponses = new ArrayList<>();
    private final List<Integer> interceptedStatusCodes = new ArrayList<>();

    public LinksPage() {
        this.driver = DriverManager.getDriver();
        if (driver instanceof ChromeDriver chromeDriver) {
            this.devTools = chromeDriver.getDevTools();
            log.info("DevTools initialized for ChromeDriver.");
        }
        PageFactory.initElements(driver, this);
    }

    @Step("Clear intercepted data (without closing session)")
    public void clearInterceptedData() {
        interceptedRequests.clear();
        interceptedResponses.clear();
        interceptedStatusCodes.clear();
        log.info("Intercepted data cleared. DevTools session remains active.");
    }

    @Step("Enable network interceptor")
    public void enableNetworkInterceptor() {
        if (devTools == null) {
            throw new IllegalStateException("DevTools is not initialized");
        }
        try {
            devTools.createSession();

            devTools.send(Network.enable(
                    Optional.of(1000000),
                    Optional.of(1000000),
                    Optional.of(1000000),
                    Optional.empty(),
                    Optional.empty()
            ));
            log.info("Network interceptor enabled.");
        } catch (Exception e) {
            log.error("Error enabling interception: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to enable network interceptor", e);
        }
    }

    @Step("Add request listener for URL: {targetUrl}")
    public void addRequestListener(String targetUrl) {
        devTools.addListener(Network.requestWillBeSent(), request -> {
            Request req = request.getRequest();
            if (req.getUrl().contains(targetUrl)) {
                log.info("Request intercepted:");
                log.info("URL: {}", req.getUrl());
                log.info("Method: {}", req.getMethod());
                log.info("Headers: {}", req.getHeaders());
                log.info("Body: {}", req.getPostData().orElse("Request body is missing"));
                interceptedRequests.add(req);
            }
        });
    }

    @Step("Add response listener for URL: {targetUrl}")
    public void addResponseListener(String targetUrl) {
        devTools.addListener(Network.responseReceived(), response -> {
            String responseUrl = response.getResponse().getUrl();
            if (responseUrl.contains(targetUrl)) {
                log.info("Request intercepted:");
                log.info("URL: {}", responseUrl);
                log.info("Status code: {}", response.getResponse().getStatus());
                log.info("Headers: {}", response.getResponse().getHeaders());
                interceptedStatusCodes.add(response.getResponse().getStatus());

                try {
                    String responseBody = devTools.send(Network.getResponseBody(response.getRequestId())).getBody();
                    log.info("Body: {}", responseBody);

                    ResponseData responseData = new ResponseData(
                            responseUrl,
                            response.getResponse().getStatus(),
                            response.getResponse().getHeaders(),
                            responseBody
                    );
                    interceptedResponses.add(responseData);
                } catch (Exception e) {
                    log.error("Error getting response body: {}", e.getMessage(), e);
                }
            }
        });
    }

    @Step("Wait for response with URL: {targetUrl}")
    public void waitForResponse(String targetUrl) {
        wait.until(d -> interceptedResponses
                .stream()
                .anyMatch(r -> r.getUrl().contains(targetUrl)));
        log.info("Response with URL containing '{}' received.", targetUrl);
    }

    public void waitForStatusCode(int statusCode) {
        wait.until(d -> interceptedStatusCodes.contains(statusCode));
        log.info("Response with status code '{}' received.", statusCode);
    }

    public List<Request> getInterceptedRequests() {
        return new ArrayList<>(interceptedRequests);
    }

    public List<ResponseData> getInterceptedResponses() {
        return new ArrayList<>(interceptedResponses);
    }

    public List<Integer> getInterceptedStatusCodes() {
        return new ArrayList<>(interceptedStatusCodes);
    }

    @Step("Click on created link")
    public void clickCreatedLink() {
        log.info("Click on created link");
        click(createdLink);
    }

    @Step("Click on No Content link")
    public void clickNoContentLink() {
        log.info("Click on No Content link");
        click(noContentLink);
    }

    @Step("Click on Moved link")
    public void clickMovedLink() {
        log.info("Click on Moved link");
        click(movedLink);
    }

    @Step("Click on Bad Request link")
    public void clickBadRequestLink() {
        log.info("Click on Bad Request link");
        click(badRequestLink);
    }

    @Step("Click on Unauthorized link")
    public void clickUnauthorizedLink() {
        log.info("Click on Unauthorized link");
        click(unauthorizedLink);
    }

    @Step("Click on forbidden link")
    public void clickForbiddenLink() {
        log.info("Click on forbidden link");
        click(forbiddenLink);
    }

    @Step("Click on Not Found link")
    public void clickNotFound() {
        log.info("Click on Not Found link");
        click(notFoundLink);
    }
}
