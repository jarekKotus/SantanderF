package testarmy.pages;

import io.cucumber.spring.ScenarioScope;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import testarmy.utils.DriverProvider;

@Component
@ScenarioScope
public class AccountPage extends BasePage {

    @FindBy(css = "p.info-account")
    WebElement welcomeLabel;

    @FindBy(css = "#contact-link")
    WebElement contactBtn;

    @Autowired
    public AccountPage(DriverProvider driverProvider) {
        super(driverProvider);
    }

    public void assertThatAccountPageIsDisplayed(String expectedText) {
        waitForElement(welcomeLabel);
        Assertions.assertEquals(welcomeLabel.getText(), expectedText, "Incorrect expected text");
    }

    public AccountPage clickContactBtn(){
        clickVisibleElement(contactBtn);
        return this;
    }

}
