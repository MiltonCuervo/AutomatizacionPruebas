Feature: Purchase flow for new users on Advantage Online Shopping
  As a new user of the online store
  I want to register, select multiple products with different quantities and pay successfully
  So that I can complete my purchase from start to finish

  Background:
    Given the user is on the Advantage Online Shopping store

# HAPPY PATHS

  # @e2e @traditional-flow @smoke
  # Scenario Outline: Successful E2E purchase with prior registration
  #   When the user creates an account with username base "<username_base>", email "<email>" and password "<password>"
  #   And the user buys <quantity> units of "<product>" from the "<category>" section
  #   And the user proceeds to checkout
  #   And the user pays with "<payment_method>"
  #   Then the purchase is completed successfully
  #   And the user sees the order confirmation with their "<username>", "<payment_method>", and order number

  #   Examples:
  #     | username_base | email       | password  | quantity | product                 | category | payment_method |
  #     | qa_user       | qa@test.com | Test@1234 | 1        | hp z3200 wireless mouse | Mice     | Master Credit  |

  # @e2e @checkout-flow
  # Scenario Outline: Successful E2E purchase registering during checkout
  #   When the user buys <quantity> units of "<product>" from the "<category>" section
  #   And the user proceeds to checkout 
  #   And the user creates an account with username base "<username_base>", email "<email>" and password "<password>"
  #   And the user pays with "<payment_method>"
  #   Then the purchase is completed successfully
  #   And the user sees the order confirmation with their "<username>", "<payment_method>", and order number

  #   Examples:
  #     | quantity | product                 | category | username_base | email       | password  | payment_method |
  #     | 1        | hp z3200 wireless mouse | Mice     | qa_user       | qa@test.com | Test@1234 | Master Credit  |

  # EXCEPTIONS

  @exceptions @registration-stage
  Scenario: Try to register with an already existing username
    When the user creates an account with username base "user_ex", email "qauser@test.com" and password "Test@1234"
    And the user logs out from the store
    And the user tries to register again with username "user_ex", email "other@test.com" and password "Test@1234"
    Then the system should display the registration error message "User name already exists"

  @exceptions @registration-stage
  Scenario: Try to register with an invalid password format
    When the user navigates to the registration form from home
    And the user tries to register with username "qa_weak", email "weak@test.com" and password "123"
    Then the system should prevent the registration and show a password error

  @exceptions @cart-stage
  Scenario: Try to add a product with minimum quantity
    When the user navigates to the "hp z3200 wireless mouse" product in "Mice"
    And the user tries to decrease the quantity below 1
    Then the quantity should not go below 1
