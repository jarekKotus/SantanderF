package testarmy.pages;

import com.github.javafaker.Faker;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;
import testarmy.utils.DriverProvider;

@Component
@ScenarioScope
public class RegistrationPage extends BasePage {

    @FindBy(css = "#id_gender1")
    WebElement mrGender;

    @FindBy(css = "#customer_firstname")
    WebElement firstNameInput;

    @FindBy(css = "#customer_lastname")
    WebElement lastNameInput;

    @FindBy(css = "#passwd")
    WebElement passwordInput;

    @FindBy(css = "#days")
    WebElement daysSelectElement;

    @FindBy(css = "#months")
    WebElement monthsSelectElement;

    @FindBy(css = "#years")
    WebElement yearsSelectElement;

    @FindBy(css = "#city")
    WebElement addressInput;

    @FindBy(css = "#address1")
    WebElement cityInput;

    @FindBy(css = "#id_state")
    WebElement stateSelectElement;

    @FindBy(css = "#postcode")
    WebElement zipCodeInput;

    @FindBy(css = "#id_country")
    WebElement countrySelectElement;

    @FindBy(css = "#phone_mobile")
    WebElement mobilePhoneInput;

    @FindBy(css = "#alias")
    WebElement aliasInput;

    @FindBy(css = "#submitAccount")
    WebElement registerButton;

    Faker faker = new Faker();

    public RegistrationPage(DriverProvider driverProvider) {
        super(driverProvider);
    }

    public RegistrationPage fillUpRegistrationFrom() {
        selectMrGender();
        setFirstAndLastName();
        setPassword();
        setDateOfBirth();
        setAddresse();
        setMobilePhone();
        setalias();
        return this;
    }

    private void selectMrGender() {
        clickVisibleElement(mrGender);
    }

    private void setFirstAndLastName() {
        sendKeysToVisibleElement(firstNameInput, faker.name().firstName());
        sendKeysToVisibleElement(lastNameInput, faker.name().lastName());
    }

    private void setPassword() {
        sendKeysToVisibleElement(passwordInput, "12345");
    }

    private void setDateOfBirth() {
        Select daySelect = new Select(daysSelectElement);
        Select monthSelect = new Select(monthsSelectElement);
        Select yearsSelect = new Select(yearsSelectElement);
        daySelect.selectByValue("4");
        monthSelect.selectByValue("9");
        yearsSelect.selectByValue("1992");
    }

    private void setAddresse() {
        sendKeysToVisibleElement(addressInput, faker.address().streetAddress());
        sendKeysToVisibleElement(cityInput, faker.address().city());
        Select stateSelect = new Select(stateSelectElement);
        stateSelect.selectByVisibleText("Alaska");
        sendKeysToVisibleElement(zipCodeInput, faker.address().zipCode().substring(0, 5));
        Select countrySelect = new Select(countrySelectElement);
        countrySelect.selectByVisibleText("United States");
    }

    private void setMobilePhone() {
        sendKeysToVisibleElement(mobilePhoneInput, faker.phoneNumber().subscriberNumber(14));
    }

    private void setalias() {
        sendKeysToVisibleElement(aliasInput, faker.name().username());
    }

    public void clickRegisterButton(){
        clickVisibleElement(registerButton);
    }

}
