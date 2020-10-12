package testarmy.pages;

import com.github.javafaker.Faker;
import io.cucumber.spring.ScenarioScope;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.manipulation.Ordering;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;
import testarmy.utils.DriverProvider;

@Component
@ScenarioScope
public class ContactUsPage extends BasePage {


    @FindBy(css = "#id_contact")
    WebElement subjectSelectElement;

    @FindBy(css = "#message")
    WebElement messageArea;

    @FindBy(css = "#submitMessage")
    WebElement sendBtn;

    @FindBy(css = "p.alert-success")
    WebElement alertSuccess;

    Faker faker = new Faker();

    public ContactUsPage(DriverProvider driverProvider) {
        super(driverProvider);
    }

    private ContactUsPage selectSubjectHeading() {
        Select subjectSelect = new Select(subjectSelectElement);
        subjectSelect.selectByVisibleText("Webmaster");
        return this;
    }

    private ContactUsPage insertMessageInTextArea() {
        sendKeysToElement(messageArea, faker.chuckNorris().fact());
        return this;
    }

    public ContactUsPage clickSendButton() {
        clickElement(sendBtn);
        return this;
    }

    public ContactUsPage fillSendMessageForm() {
        selectSubjectHeading();
        insertMessageInTextArea();
        return this;
    }

    public void assertThatMessageHasBeenSendSuccessfully(String expectedMessage) {
        Assertions.assertEquals(alertSuccess.getText(), expectedMessage, "Incorrect expected message");
    }


}
