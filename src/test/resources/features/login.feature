Feature: Login Functionality

Feature: Login smoke

  Scenario: Simple login on chrome
    Given user is on the LoginPage on "chrome" browser
    When user enters valid credentials "standard_user" and "secret_sauce"
    And user clicks the login button
    Then the user is directed to Inventory page

  Scenario Outline: Successful login with valid credentials on <browser> with "<username>"

    Given user is on the LoginPage on "<browser>" browser
    When user enters valid credentials "<username>" and "<password>"
    And user clicks the login button
    Then the user is directed to Inventory page

    Examples:
      | browser | username      | password     |
      | chrome  | standard_user | secret_sauce |
      | firefox | standard_user | secret_sauce |
      | edge    | standard_user | secret_sauce |
      | chrome  | problem_user  | secret_sauce |
      | firefox | problem_user  | secret_sauce |
      | edge    | problem_user  | secret_sauce |
      | chrome  | visual_user   | secret_sauce |
      | firefox | visual_user   | secret_sauce |
      | edge    | visual_user   | secret_sauce |



  Scenario Outline: Failed login attempt with clear credentials on <browser>

    Given   user is on the LoginPage on "<browser>" browser
    When user enters valid credentials "<username>" and "<password>"
    And user clears input credentials
    When user tries to login
    Then an error message is displayed "<errorMessage>"

    Examples:
      | browser | username      | password     |  errorMessage                        |
      | chrome  | standard_user | secret_sauce |  Epic sadface: Username is required  |
      | firefox | standard_user | secret_sauce |  Epic sadface: Username is required  |
      | edge    | standard_user | secret_sauce |  Epic sadface: Username is required  |
      | chrome  | problem_user  | secret_sauce |  Epic sadface: Username is required  |
      | firefox | problem_user  | secret_sauce |  Epic sadface: Username is required  |
      | edge    | problem_user  | secret_sauce |  Epic sadface: Username is required  |
      | chrome  | visual_user   | secret_sauce |  Epic sadface: Username is required  |
      | firefox | visual_user   | secret_sauce |  Epic sadface: Username is required  |
      | edge    | visual_user   | secret_sauce |  Epic sadface: Username is required  |



  Scenario Outline: Failed login attempt with clear password on <browser>

    Given   user is on the LoginPage on "<browser>" browser
    When user enters valid credentials "<username>" and "<password>"
    And user clears password credential
    When user tries to login
    Then an error message is displayed "<errorMessage>"

    Examples:
      | browser | username      | password     |  errorMessage                        |
      | chrome  | standard_user | secret_sauce |  Epic sadface: Password is required  |
      | firefox | standard_user | secret_sauce |  Epic sadface: Password is required  |
      | edge    | standard_user | secret_sauce |  Epic sadface: Password is required  |
      | chrome  | problem_user  | secret_sauce |  Epic sadface: Password is required  |
      | firefox | problem_user  | secret_sauce |  Epic sadface: Password is required  |
      | edge    | problem_user  | secret_sauce |  Epic sadface: Password is required  |
      | chrome  | visual_user   | secret_sauce |  Epic sadface: Password is required  |
      | firefox | visual_user   | secret_sauce |  Epic sadface: Password is required  |
      | edge    | visual_user   | secret_sauce |  Epic sadface: Password is required  |

