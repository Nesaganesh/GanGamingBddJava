Feature: As a new moneygaming.com player I want to be able to register using my full details

  Background:
    Given I navigate to moneygaming website
    When I click JoinNow button


  Scenario: Register a user and check dob error message
    Given I see the registration page
    And I select 'Mr' from the title selectbox
    Then I enter firstname 'Alex' and surname 'John' in the form
    And I click terms and condition checkbox
    Then I should be able to see error message below dob selectbox as 'This field is required'

  Scenario: Register a user and validate password field
    Given I enter password 'pass'
    Then I should be able to see error message in the password field be atleast six characters
    And I enter password 'password'
    Then I should be able to see error message in the password field must contain atleast one number
    And I enter password 'password1234'
    Then I should be able to see error message in the password field must contain atleast one special character
