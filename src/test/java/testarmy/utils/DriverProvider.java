package testarmy.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.DisposableBean;

import java.net.MalformedURLException;
import java.net.URL;


public class DriverProvider implements DisposableBean {

    private static PropertyService propertyService = new PropertyService();
    private static WebDriver driver;

    public DriverProvider() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        setNewDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static void setNewDriver() throws MalformedURLException {
        Boolean docker = Boolean.parseBoolean(propertyService.getProperty("remoteDriver"));
        String browserName = propertyService.getProperty("browser");
        if (docker) {
            setRemoteDriver(browserName);
        } else {
            setWebManagerDriver(browserName);
        }
        driver.manage().window().maximize();
    }

    public void prepareForNewTest() {

        driver.manage().deleteAllCookies();
    }

    private static boolean checkHeadlessProperty() {
        String headless = propertyService.getProperty("headless");
        boolean ret = false;
        if (headless != null && headless.contains("true")) {
            ret = true;
        }
        return ret;
    }

    private static WebDriver setChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        if (checkHeadlessProperty()) {
            options.addArguments("--headless");
        }
        driver = new ChromeDriver(options);

        return driver;
    }

    private static WebDriver setFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        if (checkHeadlessProperty()) {
            options.addArguments("-headless");
        }
        driver = new FirefoxDriver(options);

        return driver;
    }

    private static void setRemoteDriver(String browserName) throws MalformedURLException {
        String dockerUrl = propertyService.getProperty("DOCKERURL");
        if (browserName.contains("CHROME")) {
            driver = new RemoteWebDriver(new URL(dockerUrl), DesiredCapabilities.chrome());
        } else if (browserName.contains("FIREFOX")) {
            driver = new RemoteWebDriver(new URL(dockerUrl), DesiredCapabilities.firefox());
        }
    }

    private static void setWebManagerDriver(String browserName) {
        WebDriverManager.getInstance(DriverManagerType.valueOf(browserName)).setup();
        if (browserName.equals("CHROME")) {
            driver = setChromeDriver();
        } else if (browserName.contains("FIREFOX")) {
            driver = setFirefoxDriver();
        }
    }

    @Override
    public void destroy() throws Exception {
        driver.quit();
    }
}
