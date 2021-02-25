Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When  username "abc" and password "passu1234573" are entered
        Then  system will respond with "new user registered"

    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When  used username "pekka" and a valid password "akkep" are entered
        Then  error message "new user not registered"

    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When  new username "ab" and too short username "passu1234" are entered
        Then  error message tooShort "new user not registered"

    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When  username "pekka" and password "k" are entered
        Then  error message tooShortPW "new user not registered"

    Scenario: creation fails with valid username and password long enough but consisting of only letters
        Given command new is selected
        When  username "pekka" and password "pekkapekka" are entered
        Then  error message onlyLettersInPW "new user not registered"

    Scenario: can login with successfully generated account
        Given user "eero" with password "salainen1" is created
        And   command login is selected
        When  username eero "eero" with password salainen1 "salainen1" are entered
        Then  system will respond again with eero "logged in"
