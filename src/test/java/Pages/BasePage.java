package Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;
import utils.Actions;
import utils.PropertyService;
import utils.Waits;

public class BasePage {
    public static WebDriver driver;
    public Waits wait;
    public Actions actions;
    private static PropertyService propertyService = new PropertyService();

    public BasePage() {
        this.wait = new Waits();
        this.actions = new Actions();
        PageFactory.initElements(getDriver(), this);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            setNewDriver();
        }
        return driver;
    }

    public static void setNewDriver() {
        String browserName = propertyService.getProperty("browser");
       if (browserName==null){ browserName = "CHROME";};
        WebDriverManager.getInstance(DriverManagerType.valueOf(browserName)).setup();
        if(browserName.equals("CHROME")){
            driver = setChromeDriver();
        }
        else if(browserName.contains("FIREFOX")) {
            driver = setFirefoxDriver();
        }
        maximizeWindow();
    }

    private static WebDriver setChromeDriver(){
        ChromeOptions options = new ChromeOptions();
        if(checkHeadlessProperty()){
            options.addArguments("--headless");
        }
        driver = new ChromeDriver(options);

        return driver;
    }
    private static WebDriver setFirefoxDriver(){
        FirefoxOptions options = new FirefoxOptions();
        if(checkHeadlessProperty()){
            options.addArguments("-headless");
        }
        driver = new FirefoxDriver(options);

        return driver;
    }

    private static boolean checkHeadlessProperty(){
        String headless = propertyService.getProperty("headless");
        boolean ret = false;
        if(headless != null && headless.contains("true")){
            ret = true;
        }
        return ret;
    }

    private static void maximizeWindow(){
        driver.manage().window().maximize();
    }

    public BasePage waitForElement(WebElement element){
        this.wait.waitForElementVisibility(element);
        return this;
    }

    protected BasePage clickVisibleElement(WebElement element){
      waitForElement(element);
      this.actions.clickElement(element);
      return this;
    }

    protected BasePage sendKeysToVisivleElement(WebElement element, String inputText){
        waitForElement(element);
        this.actions.sendKeysToElement(element, inputText);
        return this;
    }
}
