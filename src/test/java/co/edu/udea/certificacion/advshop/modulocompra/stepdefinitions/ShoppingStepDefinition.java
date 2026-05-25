package co.edu.udea.certificacion.advshop.modulocompra.stepdefinitions;

import co.edu.udea.certificacion.advshop.modulocompra.questions.CartQuestions;
import co.edu.udea.certificacion.advshop.modulocompra.questions.OrderNumber;
import co.edu.udea.certificacion.advshop.modulocompra.questions.OrderSummary;
import co.edu.udea.certificacion.advshop.modulocompra.questions.PurchaseConfirmation;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.BuyProduct;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.ConfirmOrder;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.ProceedToCheckout;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.SelectPaymentMethod;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.AdvantagePageElement;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CartPageElements;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.*;

public class ShoppingStepDefinition {

    @CastMember(name = "Robinson")
    private Actor actor;

    // ─────────────────────────────────────────────
    //  Session verification
    // ─────────────────────────────────────────────

    @Given("the user is logged in as {string}")
    public void theUserIsLoggedInAs(String username) {
        actor.attemptsTo(
                WaitUntil.the(AdvantagePageElement.USERNAME_TEXT, isVisible())
                        .forNoMoreThan(7).seconds()
        );
        actor.should(seeThat("The session is still active for user",
                Text.of(AdvantagePageElement.USERNAME_TEXT), containsString(username)));
    }

    // ─────────────────────────────────────────────
    //  Product selection
    // ─────────────────────────────────────────────

    @When("the user buys {int} units of {string} from the {string} section")
    public void theUserBuysProductFromCategory(int quantity, String product, String category) {
        actor.attemptsTo(BuyProduct.from(category, product, quantity));
    }

    // ─────────────────────────────────────────────
    //  Cart verification
    // ─────────────────────────────────────────────

    @And("the user verifies that the cart contains {int} units of {string} and {int} units of {string}")
    public void theUserVerifiesTheCartContents(int q1, String p1, int q2, String p2) {
        actor.attemptsTo(
                Click.on(CartPageElements.CART_ICON)
        );

        actor.attemptsTo(
                WaitUntil.the(CartPageElements.productInCart(p1), isPresent()).forNoMoreThan(10).seconds(),
                WaitUntil.the(CartPageElements.productInCart(p2), isPresent()).forNoMoreThan(10).seconds()
        );

        actor.should(
                seeThat("First product is in cart",
                        CartQuestions.isProductPresent(p1), is(true)),
                seeThat("First product quantity is correct",
                        CartQuestions.quantityOf(p1), equalTo(String.valueOf(q1))),
                seeThat("Second product is in cart",
                        CartQuestions.isProductPresent(p2), is(true)),
                seeThat("Second product quantity is correct",
                        CartQuestions.quantityOf(p2), equalTo(String.valueOf(q2)))
        );
    }

    // ─────────────────────────────────────────────
    //  Checkout
    // ─────────────────────────────────────────────

    @And("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        actor.attemptsTo(
                ProceedToCheckout.fromTheCart()
        );
    }

    @And("the user pays with {string}")
    public void theUserPaysWith(String paymentMethod) {
        actor.attemptsTo(
                SelectPaymentMethod.named(paymentMethod),
                ConfirmOrder.purchase()
        );
    }

    // ─────────────────────────────────────────────
    //  Assertions
    // ─────────────────────────────────────────────

    @Then("the purchase should be completed successfully")
    public void thePurchaseShouldBeCompletedSuccessfully() {
        actor.should(
                seeThat("The purchase confirmation is visible",
                        PurchaseConfirmation.isVisible(), is(true))
        );
    }

    @Then("an order number should be visible on the confirmation page")
    public void anOrderNumberShouldBeVisibleOnTheConfirmationPage() {
        actor.should(
                seeThat("An order number is generated and visible",
                        OrderNumber.isVisible(), is(true))
        );
    }

    @And("the order summary should contain the selected products and quantities")
    public void theOrderSummaryShouldContainTheSelectedProductsAndQuantities() {
        actor.should(
                seeThat("The order summary table is visible with purchased products",
                        OrderSummary.containsSelectedProducts(), is(true))
        );
    }
}