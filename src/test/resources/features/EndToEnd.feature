Feature: Flink Task

  @end2end
  Scenario: WeatherShopper End2End
    Given user is on the main page
    When user checks the temperature and makes a choice accordingly
    And user adds products considering the statements
    And user navigates to cart and pays with credit card
    Then user sees a success message is displayed