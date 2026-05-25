Feature: Purchase flow for new users on Advantage Online Shopping
  As a new user of the online store
  I want to register, select multiple products with different quantities and pay successfully
  So that I can complete my purchase from start to finish

  Background:
    Given the user is on the Advantage Online Shopping store

  @e2e @traditional-flow @smoke
  Scenario Outline: Successful E2E purchase with prior registration
    When the user creates an account with username base "<username_base>", email "<email>" and password "<password>"
    And the user buys <quantity> units of "<product>" from the "<category>" section
    And the user pays with "<payment_method>"
    Then the purchase should be completed successfully

    Examples:
      | username_base | email          | password  | quantity |          product        |  category   | payment_method |
      | qa_user       | qa@test.com    | Test@1234 |    1     | hp z3200 wireless mouse |    Mice     | Master Credit  |

  @e2e @checkout-flow
  Scenario Outline: Successful E2E purchase registering during checkout
    When the user buys <quantity> units of "<product>" from the "<category>" section
    And the user proceeds to checkout and creates an account with username base "<username_base>", email "<email>" and password "<password>"
    And the user pays with "<payment_method>"
    Then the purchase should be completed successfully

  Examples:
    | quantity |          product        |  category   |  username_base | email          | password   | payment_method |
    |    1     | hp z3200 wireless mouse |    Mice     |  qa_user       | qa@test.com    | Test@1234  | Master Credit  |

# @exceptions @registration-stage
#   Scenario: Try to register with an already existing username
#     # Forzamos un usuario que sabemos que ya existe de verdad o que creamos antes
#     When the user tries to create an account with an existing username "admin" 
#     Then the system should display the registration error message "User already exists"

#   @exceptions @registration-stage
#   Scenario: Try to register with an invalid password format
#     When the user tries to create an account with username "qa_test" and weak password "123"
#     Then the system should prevent the registration and highlight the password field

#   @exceptions @cart-stage
#   Scenario: Try to add a product with zero or negative quantity
#     When the user tries to add -1 units of "HP Z3200 WIRELESS MOUSE" to the cart
#     Then the system should not allow the action or default the quantity to 1

#   @exceptions @payment-stage
#   Scenario: Try to pay with an invalid SafePay account
#     Given the user is logged in with a valid account
#     And the user has products in the cart
#     When the user tries to pay with SafePay using invalid credentials
#     Then the system should display the payment error message "Invalid username or password"