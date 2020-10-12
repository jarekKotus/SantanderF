package testarmy.steps;

import io.cucumber.java.bs.A;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import testarmy.pages.*;
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

    @Autowired
    ContactUsPage contactUsPage;

    @Given("^user proceed to registration page$")
    public void userProceedToRegistrationPage() {
        mainPage
                .goToSignInPage();
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
        accountPage
                .assertThatAccountPageIsDisplayed(expectedTextInLabel);
    }

    @Given("^user proceed to login page$")
    public void userProceedToLoginPage() {
        mainPage
                .goToSignInPage();
    }

    @When("^he fill up and submit login form$")
    public void userFillUpAndSubmitLoginForm() {
        loginPage
                .insertAccountCredentials()
                .clickSubmitBtn();
    }

    @Given("^user proceed to contact page$")
    public void userProceedToContactUsPage() {
        mainPage
                .goToSignInPage();
        loginPage
                .insertAccountCredentials()
                .clickSubmitBtn();
        accountPage
                .clickContactBtn();
    }

    @When("^he fill up and submit send a message form$")
    public void heFillUpAndSubmitSendAMessageForm() {
        contactUsPage
                .fillSendMessageForm()
                .clickSendButton();
    }

    @Then("^message has been sent successfully$")
    public void assertThatMessageWasSent() {
        String expectedTextInLabel = "Your message has been successfully sent to our team.";
        contactUsPage
                .assertThatMessageHasBeenSendSuccessfully(expectedTextInLabel);
    }
}
