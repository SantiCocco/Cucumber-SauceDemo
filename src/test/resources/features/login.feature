Feature: Login

  Scenario Outline: Login attempt with different credentials
    Given The user opens the login page
    When They enter username "<username>" and password "<password>"
    And They click the login button
    Then The login result should be "<result>"

    Examples:
      | username         | password      | result         |
      | standard_user    | secret_sauce  | success        |
      | locked_out_user  | secret_sauce  | error          |
      | invalid_user     | wrong_pass    | error          |
