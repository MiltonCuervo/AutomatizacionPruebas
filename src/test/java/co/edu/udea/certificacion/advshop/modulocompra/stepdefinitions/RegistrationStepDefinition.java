package co.edu.udea.certificacion.advshop.modulocompra.stepdefinitions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.AdvantagePageElement;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.EnterThe;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.OpenThe;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.screenplay.questions.Text;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class RegistrationStepDefinition {

    @CastMember(name = "Robinson")
    private Actor robinson;


    @Given("the user is on the Advantage Online Shopping store")
    public void theUserIsOnTheAdvantageOnlineShoppingStore() {
        robinson.attemptsTo(OpenThe.browser());
    }

    @When("the user creates an account with username {string}, email {string} and password {string}")
    public void theUserCreatesAnAccount(String username, String email, String password) {
        robinson.attemptsTo(EnterThe.information(username, email, password));
    }

    @Then("the user {string} should be logged in to the store")
    public void theUserShouldBeLoggedInToTheStore(String username) {
        robinson.attemptsTo(WaitUntil.the(AdvantagePageElement.USERNAME_TEXT, isVisible()).forNoMoreThan(5).seconds());
        robinson.should(seeThat("The displayed username", Text.of(AdvantagePageElement.USERNAME_TEXT), containsString(username)));
    }
}