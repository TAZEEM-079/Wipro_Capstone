@LoginFeature
Feature: User Login on Demoblaze

@ValidLogin
Scenario Outline: Valid User Login
    Given User is on the Login page
    When User enters the following credentials:
      | Username   | Password       |
      | wiprouser  | wiprouser@123  |
    And Clicks on the Login button
    Then User should be logged in successfully

@InvalidLogin
Scenario Outline: Invalid User Login
    Given User is on the Login page
    When User enters the following credentials:
      | Username     | Password     |
      | invalidUser  | wrongPass   |
    And Clicks on the Login button
    Then User should see an error alert

