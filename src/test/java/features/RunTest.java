package features;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static Pages.BasePage.getDriver;


@CucumberOptions(strict = false, features = {"src/test/java/features"}, format = {"pretty", "html:target/site/cucumber-pretty",
        "json:target/cucumber.json"}, glue = {"steps"})
public class RunTest extends AbstractTestNGCucumberTests {
    private static WebDriver driver;

    @BeforeSuite
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = getDriver();
    }

    @AfterSuite
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
