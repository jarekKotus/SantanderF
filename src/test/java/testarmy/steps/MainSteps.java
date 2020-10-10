package testarmy.steps;

import io.cucumber.java.bs.A;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import testarmy.pages.AccountPage;
import testarmy.pages.LoginPage;
import testarmy.pages.MainPage;
import testarmy.pages.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MainSteps {

    @Autowired
    MainPage mainPage;

    @Autowired
    LoginPage loginPage;

    @Autowired
    RegistrationPage registrationPage;

    @Autowired
    AccountPage accountPage;

    @Given("^user proceed to registration page$")
    public void userProceedToRegistrationPage() {
        mainPage.goToSignInPage();
        loginPage
                .goToRegistrationForm();
    }

    @When("^he fill up and submit registration form$")
    public void userFillUpAndSubmitRegistrationForm() {
        registrationPage
                .fillUpRegistrationFrom()
                .clickRegisterButton();
    }

    @Then("^account page is displayed$")
    public void accountPageIsDisplayed() {
        String expectedTextInLabel = "Welcome to your account. Here you can manage all of your personal information and orders.";
        accountPage.assertThatAccountPageIsDisplayed(expectedTextInLabel);
    }

}
