package co.edu.udea.certificacion.advshop.modulocompra.stepdefinitions;

import co.edu.udea.certificacion.advshop.modulocompra.tasks.EnterThe;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.OpenThe;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.WebDriver;

public class AdvShopStepDefinition {

    public final Actor Buyer = Actor.named("Robinson");

    @Managed(driver = "chrome", uniqueSession = true)
    public WebDriver theDriver;

    @Before
    public void config(){
        Buyer.can(BrowseTheWeb.with(theDriver));
        //OnStage.setTheStage(new OnlineCast());
        //OnStage.theActorCalled("user");
    }

    @Given("a new user is on the Advantage Online Shopping home page")
    public void aNewUserIsOnTheAdvantageOnlineShoppingHomePage() {
        Buyer.attemptsTo(OpenThe.browser());
    }

    @When("the user registers with valid credentials")
    public void theUserRegistersWithValidCredentials() {
        Buyer.attemptsTo(EnterThe.information());
    }
    @When("the user adds a product with quantity {int} to the cart")
    public void theUserAddsAProductWithQuantityToTheCart(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
    @When("the user adds a different product with quantity {int} to the cart")
    public void theUserAddsADifferentProductWithQuantityToTheCart(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
    @When("the user completes the payment with valid payment details")
    public void theUserCompletesThePaymentWithValidPaymentDetails() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
    @Then("the order should be confirmed successfully")
    public void theOrderShouldBeConfirmedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
}
