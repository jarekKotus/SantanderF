package testarmy.pages;

import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import testarmy.utils.DriverProvider;

import static testarmy.data.TestData.BASEURL;

@Component
@ScenarioScope
public class MainPage extends BasePage {

    @Autowired
    private DriverProvider driverProvider;

    @FindBy(css = ".header_user_info a")
    WebElement signIn;

    public MainPage(DriverProvider driverProvider) {
        super(driverProvider);
    }

    private MainPage openMainPage() {
        driverProvider.getDriver().get(BASEURL);
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
