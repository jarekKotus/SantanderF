package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static Data.TestData.BASEURL;

public class AccountPage extends BasePage {


    @FindBy(css = "p.info-account")
    WebElement welcomeLabel;

    public AccountPage() {
        super();
    }


    public void assertThatAccountPageIsDisplayed(String expectedText) {
        waitForElement(welcomeLabel);
        Assert.assertEquals(welcomeLabel.getText(), expectedText, "Incorrect expected text");
    }

}
