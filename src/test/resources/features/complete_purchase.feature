Feature: Purchase flow for new users on Advantage Online Shopping
  As a new user of the online store
  I want to register and buy multiple products with different quantities
  So that I can complete my purchase successfully

  Scenario: New user completes a purchase with multiple products
    Given a new user is on the Advantage Online Shopping home page
    When the user registers with valid credentials
    And the user adds a product with quantity 2 to the cart
    And the user adds a different product with quantity 1 to the cart
    And the user proceeds to checkout
    And the user completes the payment with valid payment details
    Then the order should be confirmed successfully