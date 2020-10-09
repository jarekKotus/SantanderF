package Pages;

import gherkin.Main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Data.TestData.BASEURL;

public class MainPage extends BasePage {


    @FindBy(css = ".header_user_info a")
    WebElement signIn;

    public MainPage() {
        super();
    }

    private MainPage openMainPage() {
        driver.get(BASEURL);
        return this;
    }

    private MainPage clickSignInBtn() {
        clickVisibleElement(signIn);
        return this;
    }
    public MainPage goToSignInPage(){
        openMainPage();
        clickSignInBtn();
        return this;
    }
}
