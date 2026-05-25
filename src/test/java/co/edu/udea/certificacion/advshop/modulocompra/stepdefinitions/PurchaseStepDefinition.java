package co.edu.udea.certificacion.advshop.modulocompra.stepdefinitions;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.AttemptRegistration;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.GoFromHomeTo;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.LogoutFromHome;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.NavigateToProductPage;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.WaitTime;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.ProceedTo;
import co.edu.udea.certificacion.advshop.modulocompra.models.PaymentDetails;
import co.edu.udea.certificacion.advshop.modulocompra.models.Product;
import co.edu.udea.certificacion.advshop.modulocompra.models.User;
import co.edu.udea.certificacion.advshop.modulocompra.questions.ConfirmationMessageIs;
import co.edu.udea.certificacion.advshop.modulocompra.questions.OrderNumberIs;
import co.edu.udea.certificacion.advshop.modulocompra.questions.PaymentMethodWas;
import co.edu.udea.certificacion.advshop.modulocompra.questions.RegistrationQuestions;
import co.edu.udea.certificacion.advshop.modulocompra.questions.UserIs;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.BuyProduct;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.OpenThe;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.ProcessPayment;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.RegisterOnCheckout;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.RegisterOnHome;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ProductPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PurchaseStepDefinition {

    private Actor buyer;
    private Scenario currentScenario;

    @Before
    public void setTheStage(Scenario scenario) {
        OnStage.setTheStage(new OnlineCast());
        buyer = OnStage.theActorCalled("Robinson");
        currentScenario = scenario;
    }

    // ─── Setup ───────────────────────────────────────────────────────────────

    @Given("the user is on the Advantage Online Shopping store")
    public void theUserIsOnTheStore() {
        buyer.wasAbleTo(OpenThe.browser());
    }

    // ─── Registro ────────────────────────────────────────────────────────────

    @When("the user creates an account with username base {string}, email {string} and password {string}")
    public void registerUserWithDynamicData(String usernameBase, String email, String password) {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String dynamicUser = usernameBase + timeStamp.substring(timeStamp.length() - 5);
        buyer.remember("REGISTERED_USERNAME", dynamicUser);
        User newUser = new User(dynamicUser, email, password);
        if (currentScenario.getSourceTagNames().contains("@checkout-flow")) {
            buyer.attemptsTo(RegisterOnCheckout.withData(newUser));
        } else {
            buyer.attemptsTo(RegisterOnHome.withData(newUser));
        }
    }

    // ✅ Nuevo: sin timestamp para el escenario de username repetido (Tu rama)
    @When("the user creates an account with fixed username {string}, email {string} and password {string}")
    public void theUserCreatesAnAccountWithFixedUsername(String baseUsername, String email, String password) {
        // Generamos un sufijo corto de 4 números aleatorios para no superar los 15 caracteres
        String randomSuffix = String.valueOf((int)(Math.random() * 9000) + 1000);

        // Usamos una base corta (ej: "qa_") + el sufijo (ej: "qa_4821")
        String uniqueUserForThisTest = "qa_" + randomSuffix;

        // Hacemos que el actor guarde este usuario en su memoria
        OnStage.theActorInTheSpotlight().remember("DUPLICATE_USER", uniqueUserForThisTest);

        OnStage.theActorInTheSpotlight().attemptsTo(
                GoFromHomeTo.register(),
                AttemptRegistration.withUsername(uniqueUserForThisTest)
                        .email(email)
                        .password(password)
                        .confirmPassword(password)
                        .acceptingTerms()
                        .validForm() // Este será un registro exitoso real
                        .andSubmit()
        );
    }

    @When("the user proceeds to checkout and creates an account with username base {string}, email {string} and password {string}")
    public void registerUserDuringCheckout(String usernameBase, String email, String password) {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String dynamicUser = usernameBase + timeStamp.substring(timeStamp.length() - 5);
        buyer.remember("REGISTERED_USERNAME", dynamicUser);
        User newUser = new User(dynamicUser, email, password);
        buyer.attemptsTo(RegisterOnCheckout.withData(newUser));
    }

    // ─── Productos y Carrito ─────────────────────────────────────────────────

    @When("the user buys {int} units of {string} from the {string} section")
    public void theUserBuysProducts(int quantity, String product, String category) {
        Product productToBuy = new Product(product, category, quantity);
        buyer.attemptsTo(BuyProduct.from(productToBuy));
    }

    // ─── Pago (Integrado de Main) ────────────────────────────────────────────

    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        buyer.attemptsTo(ProceedTo.checkout());
    }

    @When("the user pays with {string}")
    public void theUserPays(String paymentMethod) {
        PaymentDetails details = paymentMethod.equalsIgnoreCase("Master Credit")
                ? PaymentDetails.masterCredit()
                : PaymentDetails.safePay();
        buyer.attemptsTo(ProcessPayment.with(details));
    }

    // ─── Confirmación (Integrado de Main) ────────────────────────────────────

    @Then("the purchase is completed successfully")
    public void thePurchaseShouldBeCompleted() {
        GivenWhenThen.then(buyer).should(            
            GivenWhenThen.seeThat("El mensaje de éxito", 
            ConfirmationMessageIs.value(), Matchers.containsString("Thank you for buying with Advantage")));
    }

    @Then("the user sees the order confirmation with their {string}, {string}, and order number")
    public void anOrderNumberShouldBeVisible(String user, String paymentMethod) {
        String buyerUsername = buyer.recall("REGISTERED_USERNAME");

        GivenWhenThen.then(buyer).should(
            GivenWhenThen.seeThat("El usuario en la confirmación", 
            UserIs.value(), Matchers.equalTo(buyerUsername)),
            
            GivenWhenThen.seeThat("El método de pago utilizado", 
            PaymentMethodWas.value(), Matchers.containsString(paymentMethod.replace(" ", ""))),
        
            GivenWhenThen.seeThat("El número de orden generado", 
            OrderNumberIs.value(), Matchers.not(Matchers.equalTo(""))));
    }

    // ─── Excepcionales: Registro (Tu rama) ────────────────────────────────────

    @And("the user logs out from the store")
    public void theUserLogsOutFromTheStore() {
        buyer.attemptsTo(LogoutFromHome.now());
    }

    @And("the user tries to register again with username {string}, email {string} and password {string}")
    public void theUserTriesToRegisterAgain(String ignoredUsername, String email, String password) {
        // Sacamos el usuario exacto que creamos en el paso 1
        String userFromMemory = OnStage.theActorInTheSpotlight().recall("DUPLICATE_USER");

        OnStage.theActorInTheSpotlight().attemptsTo(
                GoFromHomeTo.register(),
                AttemptRegistration.withUsername(userFromMemory)
                        .email(email)
                        .password(password)
                        .confirmPassword(password)
                        .acceptingTerms()
                        .validForm()
                        .andSubmit()
        );
    }

    @Then("the system should display the registration error message {string}")
    public void theSystemShouldDisplayRegistrationError(String expectedErrorMessage) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat("Username taken error is visible",
                        RegistrationQuestions.errorMessageIs(expectedErrorMessage))
        );
    }

    @When("the user navigates to the registration form from home")
    public void theUserNavigatesToRegistrationFormFromHome() {
        buyer.attemptsTo(
                WaitTime.of(1),
                GoFromHomeTo.register(),
                WaitTime.of(2)
        );
    }

    @And("the user tries to register with username {string}, email {string} and password {string}")
    public void theUserTriesToRegisterWith(String username, String email, String password) {
        buyer.attemptsTo(
                WaitTime.of(1),
                AttemptRegistration.withUsername(username)
                        .email(email)
                        .password(password)
                        .confirmPassword(password)
                        .acceptingTerms()
                        .andSubmit()
        );
    }

    @Then("the system should prevent the registration and show a password error")
    public void theSystemShouldShowPasswordError() {
        buyer.attemptsTo(WaitTime.of(2));
        GivenWhenThen.then(buyer).should(
                seeThat("Password too short error is visible",
                        RegistrationQuestions.passwordErrorIsVisible(),
                        is(true))
        );
    }

    // ─── Excepcionales: Carrito (Tu rama) ─────────────────────────────────────

    @When("the user navigates to the {string} product in {string}")
    public void theUserNavigatesToProduct(String product, String category) {
        buyer.attemptsTo(NavigateToProductPage.named(product, category));
    }

    @And("the user tries to decrease the quantity below 1")
    public void theUserTriesToDecreaseQuantityBelowOne() {
        buyer.attemptsTo(
                WaitTime.of(1),
                Click.on(ProductPage.DECREASE_QUANTITY_BUTTON),
                WaitTime.of(1),
                Click.on(ProductPage.DECREASE_QUANTITY_BUTTON),
                WaitTime.of(1),
                Click.on(ProductPage.DECREASE_QUANTITY_BUTTON),
                WaitTime.of(1)
        );
    }

    @Then("the quantity should not go below 1")
    public void theQuantityShouldNotGoBelowOne() {
        GivenWhenThen.then(buyer).should(
                seeThat("Quantity stays at minimum 1",
                        RegistrationQuestions.currentQuantityOnProductPage(),
                        equalTo("1"))
        );
    }

    // ─── Teardown ────────────────────────────────────────────────────────────

    @After
    public void closeBrowser() {
        try {
            ThucydidesWebDriverSupport.getDriver().quit();
        } catch (Exception e) {
            System.out.println("El navegador ya estaba cerrado: " + e.getMessage());
        }
    }
}