package testarmy.pages;

import com.github.javafaker.Faker;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import testarmy.utils.DriverProvider;

@Component
@ScenarioScope
public class LoginPage extends BasePage {

    @FindBy(css = "#email_create")
    WebElement createEmailInput;

    @FindBy(css = "#SubmitCreate")
    WebElement createAccountBtn;

    Faker faker = new Faker();

    public LoginPage(DriverProvider driverProvider) {
        super(driverProvider);
    }

    private LoginPage insertEmailCreate() {
        sendKeysToVisivleElement(createEmailInput, "jaroslaw.kotus+"+faker.random().nextInt(1,99999)+"@testarmy.com");
        return this;
    }

    private LoginPage clickCreateAccountButton(){
        clickVisibleElement(createAccountBtn);
        return this;
    }
    public LoginPage goToRegistrationForm(){
        insertEmailCreate();
        clickCreateAccountButton();
        return this;
    }
}
