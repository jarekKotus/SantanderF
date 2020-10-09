package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

import static Pages.BasePage.getDriver;

public class Waits {
    private WebDriver driver;
    public WebDriverWait wait;
    int timeout = 10;
    protected final Logger log = Logger.getLogger(getClass().getName());

    public Waits() {
        this.driver = getDriver();
    }

    public void waitForElementVisibility(WebElement element) {
        this.wait = new WebDriverWait(this.driver, timeout);
        this.wait.until(ExpectedConditions.visibilityOf(element));
        log.log(Level.INFO, String.format("Waiting for visibility '%s' ", element));
    }
}
