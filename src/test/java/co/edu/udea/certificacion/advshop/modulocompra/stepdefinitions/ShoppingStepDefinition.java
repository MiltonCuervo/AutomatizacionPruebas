package co.edu.udea.certificacion.advshop.modulocompra.stepdefinitions;

import co.edu.udea.certificacion.advshop.modulocompra.tasks.BuyProduct;
//import co.edu.udea.certificacion.advshop.modulocompra.tasks.CompletePurchase;
//import co.edu.udea.certificacion.advshop.modulocompra.tasks.Login;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.AdvantagePageElement;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.model.time.InternalSystemClock;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class ShoppingStepDefinition {

    @CastMember(name = "Robinson")
    private Actor robinson;

    @Given("the user is logged in as {string}")
    public void theUserIsLoggedInAs(String username) {
        robinson.attemptsTo(
                WaitUntil.the(AdvantagePageElement.USERNAME_TEXT, isVisible()).forNoMoreThan(7).seconds()
        );
        robinson.should(seeThat("The session is still active for user", Text.of(AdvantagePageElement.USERNAME_TEXT), containsString(username)));
    }

    @When("the user buys {int} units of {string} from the {string} section")
    public void theUserBuysProductFromCategory(int quantity, String product, String category) {
        robinson.attemptsTo(BuyProduct.from(category, product, quantity));
    }

    @And("the user pays with {string}")
    public void theUserPaysWith(String paymentMethod) {
        //actor().attemptsTo(CompletePurchase.with(paymentMethod));
    }

    @Then("the purchase should be completed successfully")
    public void thePurchaseShouldBeCompletedSuccessfully() {
        new InternalSystemClock().pauseFor(60000);
    }

    @Then("an order number should be visible on the confirmation page")
    public void anOrderNumberShouldBeVisibleOnTheConfirmationPage() {
        // Question va aquí
    }
}