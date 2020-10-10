package testarmy.pages;

import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import testarmy.utils.DriverProvider;
import testarmy.utils.PropertyService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ScenarioScope
public class BasePage {

    protected final Logger log = Logger.getLogger(getClass().getName());
    public WebDriver driver;
    int DEFAULT_TIMEOUT = 10;

    public BasePage(DriverProvider driverProvider) {
        this.driver = driverProvider.getDriver();
        PageFactory.initElements(this.driver, this);
    }

    public BasePage waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
        log.log(Level.INFO, String.format("Waiting for visibility '%s' ", element));
        return this;
    }

    protected BasePage clickVisibleElement(WebElement element) {
        waitForElement(element);
        clickElement(element);
        return this;
    }

    protected BasePage sendKeysToVisivleElement(WebElement element, String inputText) {
        waitForElement(element);
        sendKeysToElement(element, inputText);
        return this;
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void sendKeysToElement(WebElement element, String inputText) {
        element.clear();
        element.sendKeys(inputText);
    }

}
