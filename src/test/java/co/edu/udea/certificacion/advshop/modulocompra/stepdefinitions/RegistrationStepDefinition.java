package co.edu.udea.certificacion.advshop.modulocompra.stepdefinitions;

import co.edu.udea.certificacion.advshop.modulocompra.models.User;
import co.edu.udea.certificacion.advshop.modulocompra.questions.ValidateThe;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.EnterThe;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.OpenThe;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.annotations.CastMember;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import org.hamcrest.Matchers;


public class RegistrationStepDefinition {

    @CastMember(name = "Robinson")
    private Actor buyer;


    @Given("the user is on the Advantage Online Shopping store")
    public void theUserIsOnTheAdvantageOnlineShoppingStore() {
        buyer.attemptsTo(OpenThe.browser());
    }

    @When("the user creates an account with username {string}, email {string} and password {string}")
    public void theUserCreatesAnAccount(String username, String email, String password) {
        // Usuario aleatorio para evitar que falle por "ya existe"
        String time = String.valueOf(System.currentTimeMillis());
        String dynamicUsername = username + time.substring(time.length() - 5);

        // Guardar el usuario en la memoria del actor
        buyer.remember("registered_user", dynamicUsername);

        User user = new User(dynamicUsername, email, password);
        buyer.attemptsTo(EnterThe.registerInformation(user));
    }

    @Then("the user should be logged in to the store")
    public void theUserShouldBeLoggedInToTheStore(String username) {
        String dynamicUsername = buyer.recall("registered_user");
        GivenWhenThen.then(buyer).should(seeThat( "The displayed username", ValidateThe.userIsLoggedIn(), Matchers.equalTo(dynamicUsername)));
    }
}