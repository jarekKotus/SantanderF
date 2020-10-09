Feature: CucumberJava

  Scenario: User is able to create a new account
    Given user proceed to registration page
    When he fill up and submit registration form
    Then account page is displayed