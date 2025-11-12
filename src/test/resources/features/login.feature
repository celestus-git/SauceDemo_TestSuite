Feature: Login Functionality

  Scenario Outline: Successful login with valid credentials for different users

    Given user is on the LoginPage
    When user enters valid credentials "<username>" and "<password>"
    And user clicks the login button
    Then the user is directed to Inventory page

    Examples:
      | username         | password     |
      | standard_user    | secret_sauce |
      | problem_user     | secret_sauce |



  Scenario Outline: Failed login attempt with clear credentials

    Given   user is on the LoginPage
    When user enters valid credentials "<username>" and "<password>"
    And user clears input credentials
    When user tries to login
    Then an error message is displayed "<errorMessage>"

    Examples:
      | username         | password     | errorMessage
      | standard_user    | secret_sauce | Epic sadface: Username is required
      | problem_user     | secret_sauce | Epic sadface: Username is required


  Scenario Outline: Failed login attempt with clear password

    Given   user is on the LoginPage
    When user enters valid credentials "<username>" and "<password>"
    And user clears password credential
    When user tries to login
    Then an error message is displayed "<errorMessage>"

    Examples:
      | username         | password     | errorMessage
      | standard_user    | secret_sauce | Epic sadface: password is required
      | problem_user     | secret_sauce | Epic sadface: password is required

