package co.edu.udea.certificacion.advshop.modulocompra.stepdefinitions;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers;

import co.edu.udea.certificacion.advshop.modulocompra.exceptions.PasswordValidationErrorException;
import co.edu.udea.certificacion.advshop.modulocompra.exceptions.ProductQuantityException;
import co.edu.udea.certificacion.advshop.modulocompra.exceptions.RegistrationErrorException;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.NavigateToProductPage;
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
import co.edu.udea.certificacion.advshop.modulocompra.tasks.Logout;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.OpenThe;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.ProcessPayment;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.RegisterOnCheckout;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.RegisterOnHome;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.exceptions.DecreaseQuantityBelowMinimum;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;

public class PurchaseStepDefinition {

    private Actor buyer;
    private Scenario currentScenario;

    @Before
    public void setTheStage(Scenario scenario) {
        OnStage.setTheStage(new OnlineCast());
        buyer = OnStage.theActorCalled("Robinson");
        currentScenario = scenario;
    }

    @Given("the user is on the Advantage Online Shopping store")
    public void theUserIsOnTheStore() {
        buyer.wasAbleTo(OpenThe.browser());
    }

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

    @When("the user buys {int} units of {string} from the {string} section")
    public void theUserBuysProducts(int quantity, String product, String category) {
        Product productToBuy = new Product(product, category, quantity);
        buyer.attemptsTo(BuyProduct.from(productToBuy));
    }


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

    // EXCEPCIONALES 

    // Escenario: El usuario intenta registrarse con un nombre de usuario ya tomado
    @And("the user logs out from the store")
    public void theUserLogsOutFromTheStore() {
        buyer.attemptsTo(Logout.fromStore());
    }

    @And("the user tries to register again with username {string}, email {string} and password {string}")
    public void theUserTriesToRegisterAgain(String ignoredUsername, String email, String password) {

        String userFromMemory = buyer.recall("REGISTERED_USERNAME");

        User sameUser = new User(userFromMemory, email, password);
        buyer.attemptsTo(RegisterOnHome.withData(sameUser));
    }

    @Then("the system should display the registration error message {string}")
    public void theSystemShouldDisplayRegistrationError(String expectedErrorMessage) {
        buyer.should(
                GivenWhenThen.seeThat("Username taken error is visible",
                        RegistrationQuestions.errorMessageIs(expectedErrorMessage))
                        .orComplainWith(RegistrationErrorException.class, RegistrationErrorException.USERNAME_ALREADY_EXISTS_FAILED)
        );
    }

    // Escenario: El usuario intenta registrarse con una contraseña inválida
    @When("the user tries to register with username {string}, email {string} and password {string}")
    public void theUserTriesToRegisterWith(String username, String email, String password) {
        User userWithInvalidPassword = new User(username, email, password);
        buyer.attemptsTo(RegisterOnHome.withData(userWithInvalidPassword));
    }

    @Then("the system should prevent the registration and show a password error")
    public void theSystemShouldShowPasswordError() {
        buyer.should(
                GivenWhenThen.seeThat("Password too short error is visible",
                        RegistrationQuestions.passwordErrorIsVisible(),
                        is(true))
                        .orComplainWith(PasswordValidationErrorException.class, PasswordValidationErrorException.PASSWORD_TOO_SHORT_FAILED)
        );
    }

    // Escenario: El usuario intenta disminuir la cantidad de un producto por debajo de 1
    @When("the user navigates to the {string} product in {string}")
    public void theUserNavigatesToProduct(String product, String category) {
        buyer.attemptsTo(NavigateToProductPage.named(product, category));
    }

    @And("the user tries to decrease the quantity below 1")
    public void theUserTriesToDecreaseQuantityBelowOne() {
        buyer.attemptsTo(DecreaseQuantityBelowMinimum.multipleTimes());
    }

    @Then("the quantity should not go below 1")
    public void theQuantityShouldNotGoBelowOne() {
        buyer.should(
                GivenWhenThen.seeThat("Quantity stays at minimum 1",
                        RegistrationQuestions.currentQuantityOnProductPage(),
                        equalTo("1"))
                        .orComplainWith(ProductQuantityException.class, ProductQuantityException.QUANTITY_BELOW_MINIMUM_FAILED)
        );
    }

    @After
    public void closeBrowser() {
        try {
            ThucydidesWebDriverSupport.getDriver().quit();
        } catch (Exception e) {
            System.out.println("El navegador ya estaba cerrado: " + e.getMessage());
        }
    }
}