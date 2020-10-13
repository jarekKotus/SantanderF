Feature: CucumberJava

  Scenario: User is able to authorize an account
    Given user proceed to login page
    When he fill up and submit login form
    Then account page is displayed

  Scenario: User is able to send a message
    Given user proceed to contact page
    When he fill up and submit send a message form
    Then message has been sent successfully