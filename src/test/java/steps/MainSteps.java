package steps;

import Pages.AccountPage;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.RegistrationPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class MainSteps {

    @Given("^user proceed to registration page$")
    public void userProceedToRegistrationPage() {
        new MainPage()
                .goToSignInPage();
        new LoginPage()
                .goToRegistrationForm();
    }

    @When("^he fill up and submit registration form$")
    public void userFillUpAndSubmitRegistrationForm() {
        new RegistrationPage()
                .fillUpRegistrationFrom()
                .clickRegisterButton();
    }

    @Then("^account page is displayed$")
    public void accountPageIsDisplayed() {
        String expectedTextInLabel = "Welcome to your account. Here you can manage all of your personal information and orders.";
        new AccountPage().assertThatAccountPageIsDisplayed(expectedTextInLabel);
    }

}
