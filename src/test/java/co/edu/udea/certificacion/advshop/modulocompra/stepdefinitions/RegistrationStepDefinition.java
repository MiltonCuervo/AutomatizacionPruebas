package co.edu.udea.certificacion.advshop.modulocompra.stepdefinitions;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.AttemptRegistration;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.EnsureLoggedOut;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.WaitTime;
import co.edu.udea.certificacion.advshop.modulocompra.models.User;
import co.edu.udea.certificacion.advshop.modulocompra.questions.RegistrationQuestions;
import co.edu.udea.certificacion.advshop.modulocompra.questions.ValidateThe;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.EnterThe;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.OpenThe;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.waits.WaitUntil;

import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;

public class RegistrationStepDefinition {

    @CastMember(name = "Robinson")
    private Actor buyer;

    // ─── Camino feliz ─────────────────────────────────────────────────────────

    @Given("the user is on the Advantage Online Shopping store")
    public void theUserIsOnTheAdvantageOnlineShoppingStore() {
        buyer.attemptsTo(
                OpenThe.browser(),
                WaitTime.of(2)
        );
    }

    @When("the user creates an account with username {string}, email {string} and password {string}")
    public void theUserCreatesAnAccount(String username, String email, String password) {
        String time = String.valueOf(System.currentTimeMillis());
        String dynamicUsername = username + time.substring(time.length() - 5);

        buyer.remember("registered_user", dynamicUsername);

        User user = new User(dynamicUsername, email, password);
        buyer.attemptsTo(
                WaitTime.of(1),
                EnterThe.registerInformation(user),
                WaitTime.of(2)
        );
    }

    // ✅ Bug corregido: sin parámetro String username
    @Then("the user should be logged in to the store")
    public void theUserShouldBeLoggedInToTheStore() {
        String dynamicUsername = buyer.recall("registered_user");
        buyer.attemptsTo(WaitTime.of(1));
        GivenWhenThen.then(buyer).should(
                seeThat("The displayed username",
                        ValidateThe.userIsLoggedIn(),
                        Matchers.equalTo(dynamicUsername))
        );
    }

    // ─── Caminos excepcionales ────────────────────────────────────────────────

    /**
     * Escenario username ya existe:
     * Antes de registrar fixed_user, garantizar que no haya sesión activa
     */
    @When("the user registers successfully with username {string}, email {string} and password {string}")
    public void theUserRegistersWithFixedUsername(String username, String email, String password) {
        buyer.remember("fixed_username", username);

        // Garantizar sesión cerrada antes de intentar registrar
        buyer.attemptsTo(EnsureLoggedOut.beforeProceeding());

        User user = new User(username, email, password);
        buyer.attemptsTo(
                WaitTime.of(1),
                EnterThe.registerInformation(user),
                WaitTime.of(2)
        );
    }


    @And("the user logs out")
    public void theUserLogsOut() {
        buyer.attemptsTo(
                EnsureLoggedOut.beforeProceeding()
        );
    }

    @And("the user tries to register again with the same username {string}, email {string} and password {string}")
    public void theUserTriesToRegisterWithSameUsername(String username, String email, String password) {
        buyer.attemptsTo(
                // Navegar al formulario primero (viene del home tras logout)
                Click.on(HomePage.BTN_USER_PROFILE),
                WaitTime.of(1),
                Click.on(HomePage.BTN_CREATE_NEW_ACCOUNT),
                WaitTime.of(2),
                // Ahora sí intentar el registro
                AttemptRegistration.withUsername(username)
                        .email(email)
                        .password(password)
                        .confirmPassword(password)
                        .acceptingTerms()
                        .validForm()
                        .andSubmit()
        );
    }

    @Then("an error should indicate that the username is already taken")
    public void anErrorShouldIndicateThatTheUsernameIsAlreadyTaken() {
        buyer.attemptsTo(WaitTime.of(1));
        GivenWhenThen.then(buyer).should(
                seeThat("Username already taken error is visible",
                        RegistrationQuestions.usernameTakenErrorIsVisible(),
                        is(true))
        );
    }

    /**
     * Escenario: campos vacíos
     */
    @When("the user navigates to the registration form")
    public void theUserNavigatesToTheRegistrationForm() {
        buyer.attemptsTo(
                // 1. Cerrar sesión si hay una activa
                EnsureLoggedOut.beforeProceeding(),
                WaitTime.of(1),
                // 2. Abrir menú y navegar al registro
                Click.on(HomePage.BTN_USER_PROFILE),
                WaitTime.of(1),
                Click.on(HomePage.BTN_CREATE_NEW_ACCOUNT),
                WaitTime.of(2)
        );
    }

    @And("the user submits the registration form without filling any field")
    public void theUserSubmitsTheFormWithoutFillingAnyField() {
        buyer.attemptsTo(
                WaitTime.of(1),
                AttemptRegistration.withEmptyFields().andSubmit() // formIsValid=false por defecto
        );
    }

    @Then("required field errors should be displayed on the form")
    public void requiredFieldErrorsShouldBeDisplayed() {
        buyer.attemptsTo(WaitTime.of(1));
        GivenWhenThen.then(buyer).should(
                seeThat("Username required error is visible",
                        RegistrationQuestions.usernameRequiredErrorIsVisible(),
                        is(true)),
                seeThat("Email required error is visible",
                        RegistrationQuestions.emailRequiredErrorIsVisible(),
                        is(true)),
                seeThat("Password required error is visible",
                        RegistrationQuestions.passwordRequiredErrorIsVisible(),
                        is(true))
        );
    }

    /**
     * Escenario: contraseñas no coinciden
     */
    @And("the user tries to register with password {string} but confirms with {string}")
    public void theUserTriesToRegisterWithMismatchedPasswords(String password, String confirmPassword) {
        String time = String.valueOf(System.currentTimeMillis());
        buyer.attemptsTo(
                WaitTime.of(1),
                AttemptRegistration
                        .withUsername("user_mismatch_" + time.substring(time.length() - 4))
                        .email("mismatch@test.com")
                        .password(password)
                        .confirmPassword(confirmPassword) // ← distinto a password
                        .acceptingTerms()
                        // sin .validForm() → usará JavaScriptClick
                        .andSubmit()
        );
    }

    @Then("a password mismatch error should be displayed")
    public void aPasswordMismatchErrorShouldBeDisplayed() {
        buyer.attemptsTo(WaitTime.of(1));
        GivenWhenThen.then(buyer).should(
                seeThat("Password mismatch error is visible",
                        RegistrationQuestions.passwordMismatchErrorIsVisible(),
                        is(true))
        );
    }

    /**
     * Escenario: email inválido
     */
    @And("the user tries to register with an invalid email {string}")
    public void theUserTriesToRegisterWithInvalidEmail(String invalidEmail) {
        String time = String.valueOf(System.currentTimeMillis());
        buyer.attemptsTo(
                WaitTime.of(1),
                AttemptRegistration
                        .withUsername("user_invalid_" + time.substring(time.length() - 4))
                        .email(invalidEmail) // ← email sin formato válido
                        .password("Test@1234")
                        .confirmPassword("Test@1234")
                        .acceptingTerms()
                        .andSubmit()
        );
    }

    @Then("an invalid email format error should be displayed")
    public void anInvalidEmailFormatErrorShouldBeDisplayed() {
        buyer.attemptsTo(WaitTime.of(1));
        GivenWhenThen.then(buyer).should(
                seeThat("Invalid email error is visible",
                        RegistrationQuestions.emailInvalidErrorIsVisible(),
                        is(true))
        );
    }
}