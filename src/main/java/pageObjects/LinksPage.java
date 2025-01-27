package pageObjects;

import basePages.BasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.network.model.Request;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.Optional;

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

    public LinksPage() {
        this.devTools = ((ChromeDriver) driver).getDevTools();
        PageFactory.initElements(driver, this);
    }

    public void enableNetworkInterceptor() {
        try {
            // Создаём сессию DevTools
            devTools.createSession();

            // Включаем перехват сетевых запросов
            devTools.send(Network.enable(
                    Optional.of(1000000), // Максимальный размер буфера запросов (1 МБ)
                    Optional.of(1000000), // Максимальный размер ответа
                    Optional.empty()));  // Без фильтров
            System.out.println("Network interceptor enabled.");
        } catch (Exception e) {
            System.err.println("Ошибка при включении перехвата сетевых запросов: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addRequestListener(String targetUrl) {
        // Перехват исходящих запросов
        devTools.addListener(Network.requestWillBeSent(), request -> {
            Request req = request.getRequest();
            if (req.getUrl().contains(targetUrl)) {
                System.out.println("Перехвачен запрос:");
                System.out.println("URL: " + req.getUrl());
                System.out.println("Метод: " + req.getMethod());
                System.out.println("Заголовки: " + req.getHeaders());
                System.out.println("Тело: " + req.getPostData().orElse("Отсутствует тело запроса"));
            }
        });

        // Перехват ответов
        devTools.addListener(Network.responseReceived(), response -> {
            String responseUrl = response.getResponse().getUrl();
            if (responseUrl.contains(targetUrl)) {
                System.out.println("Перехвачен ответ:");
                System.out.println("URL: " + responseUrl);
                System.out.println("Статус код: " + response.getResponse().getStatus());
                System.out.println("Заголовки: " + response.getResponse().getHeaders());

                // Получение тела ответа
                devTools.send(Network.getResponseBody(response.getRequestId()));
            }
        });
    }

    public void clickCreatedLink() {
        log.info("Click on created link");
        createdLink.click();
    }

    public void clickNoContentLink() {
        noContentLink.click();
    }

    public void clickMovedLink() {
        movedLink.click();
    }

    public void clickBadRequestLink() {
        badRequestLink.click();
    }

    public void clickUnauthorizedLink() {
        unauthorizedLink.click();
    }

    public void clickForbiddenLink() {
        forbiddenLink.click();
    }

    public void clickNotFound() {
        notFoundLink.click();
    }
}
