package testarmy.utils;

import io.cucumber.spring.ScenarioScope;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.DisposableBean;


public class DriverProvider implements DisposableBean {

    private static PropertyService propertyService = new PropertyService();
    private static WebDriver driver;

    public DriverProvider () {
        WebDriverManager.chromedriver().setup();
        setNewDriver();
    }

    public WebDriver getDriver() {
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
        driver.manage().window().maximize();
    }

    public void prepareForNewTest(){

        driver.manage().deleteAllCookies();
    }

    private static boolean checkHeadlessProperty(){
        String headless = propertyService.getProperty("headless");
        boolean ret = false;
        if(headless != null && headless.contains("true")){
            ret = true;
        }
        return ret;
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

    @Override
    public void destroy() throws Exception {
        driver.quit();
    }
}
