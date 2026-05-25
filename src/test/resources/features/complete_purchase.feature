Feature: Purchase flow for new users on Advantage Online Shopping
  As a new user of the online store
  I want to register, select multiple products with different quantities and pay successfully
  So that I can complete my purchase from start to finish

  Background:
    Given the user is on the Advantage Online Shopping store

  @registration @smoke
  Scenario: New user registers successfully
    When the user creates an account with username "qa_user", email "qa_user_01@test.com" and password "Test@1234"
    Then the user should be logged in to the store

  Scenario Outline: Registered user completes a purchase with multiple products
    Given the user is logged in with the registered account
    When the user buys <quantity_1> units of "<product_1>" from the "<category_1>" section
    And the user buys <quantity_2> units of "<product_2>" from the "<category_2>" section
    And the user verifies that the cart contains <quantity_1> units of "<product_1>" and <quantity_2> units of "<product_2>"
    And the user pays with "<payment_method>"
    Then the purchase should be completed successfully
    And an order number should be visible on the confirmation page

    Examples:
      | category_1 | product_1    | quantity_1 | category_2 | product_2    | quantity_2 | payment_method |
      | Speakers   | Bose Soundlink Bluetooth Speaker III | 2          | Mice       | HP Z3200 WIRELESS MOUSE | 1          | Master Credit  |
      #| Speakers   | Bose SoundLink Wireless Speaker | 1          | Mice       | HP USB 3 BUTTON OPTICAL MOUSE | 2          | Safe Pay       |