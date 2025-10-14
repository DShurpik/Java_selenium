package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.ResponseData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v140.network.Network;
import org.openqa.selenium.devtools.v140.network.model.Request;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.google.gson.Gson;

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
    private List<Request> interceptedRequests = new ArrayList<>();
    private List<ResponseData> interceptedResponses = new ArrayList<>();
    private List<Integer> interceptedStatusCodes = new ArrayList<>();
    private final Gson gson = new Gson();

    public LinksPage() {
        this.devTools = ((ChromeDriver) driver).getDevTools();
        PageFactory.initElements(driver, this);
    }

    public void enableNetworkInterceptor() {
        try {
            // Создаём сессию DevTools
            devTools.createSession();
    /**
            // Включаем перехват сетевых запросов
            devTools.send(Network.enable(
                    Optional.of(1000000), // Максимальный размер буфера запросов (1 МБ)
                    Optional.of(1000000), // Максимальный размер ответа
                    Optional.empty()));  // Без фильтров
            log.info("Network interceptor enabled.");

     */
        } catch (Exception e) {
            System.err.println("Ошибка при включении перехвата сетевых запросов: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addRequestListener(String targetUrl) {
        devTools.addListener(Network.requestWillBeSent(), request -> {
            Request req = request.getRequest();
            if (req.getUrl().contains(targetUrl)) {
                log.info("Перехвачен запрос:");
                log.info("URL: " + req.getUrl());
                log.info("Метод: " + req.getMethod());
                log.info("Заголовки: " + req.getHeaders());
                log.info("Тело: " + req.getPostData().orElse("Отсутствует тело запроса"));

                interceptedRequests.add(req);
            }
        });
    }

    public void addResponseListener(String targetUrl) {
        devTools.addListener(Network.responseReceived(), response -> {
            String responseUrl = response.getResponse().getUrl();
            if (responseUrl.contains(targetUrl)) {
                log.info("Перехвачен ответ:");
                log.info("URL: " + responseUrl);
                log.info("Статус код: " + response.getResponse().getStatus());
                log.info("Заголовки: " + response.getResponse().getHeaders());

                try {
                    String responseBody = devTools.send(Network.getResponseBody(response.getRequestId())).getBody();
                    log.info("Тело ответа: " + responseBody);

                    ResponseData responseData = new ResponseData(
                            responseUrl,
                            response.getResponse().getStatus(),
                            response.getResponse().getHeaders(),
                            responseBody
                    );
                    interceptedResponses.add(responseData);
                } catch (Exception e) {
                    System.out.println("Ошибка при получении тела ответа: " + e.getMessage());
                }
            }
        });
    }

    public List<Request> getInterceptedRequests() {
        return interceptedRequests;
    }

    public List<ResponseData> getInterceptedResponses() {
        return interceptedResponses;
    }


    @Step("Click on created link")
    public void clickCreatedLink() {
        log.info("Click on created link");
        createdLink.click();
    }

    @Step("Click on No Content link")
    public void clickNoContentLink() {
        log.info("Click on No Content link");
        noContentLink.click();
    }

    @Step("Click on Moved link")
    public void clickMovedLink() {
        log.info("Click on Moved link");
        movedLink.click();
    }

    @Step("Click on Bad Request link")
    public void clickBadRequestLink() {
        log.info("Click on Bad Request link");
        badRequestLink.click();
    }

    @Step("Click on Unauthorized link")
    public void clickUnauthorizedLink() {
        log.info("Click on Unauthorized link");
        unauthorizedLink.click();
    }

    @Step("Click on forbidden link")
    public void clickForbiddenLink() {
        log.info("Click on forbidden link");
        forbiddenLink.click();
    }

    @Step("Click on Not Found link")
    public void clickNotFound() {
        log.info("Click on Not Found link");
        notFoundLink.click();
    }
}
