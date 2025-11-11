Feature: Login Functionality

  Scenario Outline: Successful login with valid credentials for different users

    Given user is on the LoginPage
    When user enters valid credentials "<User_Type>"
    Then the user is directed to Inventory page
    Examples:
      | User_Type |
      | standard_user |
      | problem_user |
      | performance_glitch_user |