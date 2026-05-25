package co.edu.udea.certificacion.advshop.modulocompra.stepdefinitions;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.ProceedTo;
import co.edu.udea.certificacion.advshop.modulocompra.models.PaymentDetails;
import co.edu.udea.certificacion.advshop.modulocompra.models.Product;
import co.edu.udea.certificacion.advshop.modulocompra.models.User;
import co.edu.udea.certificacion.advshop.modulocompra.questions.ConfirmationMessageIs;
import co.edu.udea.certificacion.advshop.modulocompra.questions.OrderNumberIs;
import co.edu.udea.certificacion.advshop.modulocompra.questions.PaymentMethodWas;
import co.edu.udea.certificacion.advshop.modulocompra.questions.UserIs;
import co.edu.udea.certificacion.advshop.modulocompra.tasks.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Scenario;

import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.hamcrest.Matchers;

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

    // Autenticación y registro

    @When("the user creates an account with username base {string}, email {string} and password {string}")
    public void registerUserWithDynamicData(String usernameBase, String email, String password) {
        // Usuario dinámico combinando la base y el tiempo
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String dynamicUser = usernameBase + timeStamp.substring(timeStamp.length() - 5);
        
        // Guardar en la memoria del actor por si la Question del Then lo necesita
        buyer.remember("REGISTERED_USERNAME", dynamicUser);

        User newUser = new User(dynamicUser, email, password);
        if (currentScenario.getSourceTagNames().contains("@checkout-flow")) {
            buyer.attemptsTo(RegisterOnCheckout.withData(newUser));
        } else {
            buyer.attemptsTo(RegisterOnHome.withData(newUser));
        }
    }

    // Selección de productos y carrito

    @When("the user buys {int} units of {string} from the {string} section")
    public void theUserBuysProducts(int quantity, String product, String category) {
        Product productToBuy = new Product(product, category, quantity);
        buyer.attemptsTo(BuyProduct.from(productToBuy));
    }

    // Pago

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

    // Confirmation

    @Then("the user sees the order confirmation with their {string}, {string}, and order number")
    public void anOrderNumberShouldBeVisible(String user, String paymentMethod) {
        String buyerUsername = buyer.recall("REGISTERED_USERNAME");

        GivenWhenThen.then(buyer).should(
            GivenWhenThen.seeThat("El usuario en la confirmación", 
            UserIs.value(), Matchers.equalTo(buyerUsername)),
            
            GivenWhenThen.seeThat("El método de pago utilizado", 
            PaymentMethodWas.value(), Matchers.containsString(paymentMethod)),
        
            GivenWhenThen.seeThat("El número de orden generado", 
            OrderNumberIs.value(), Matchers.not(Matchers.emptyOrNullString())));
    }

    @After
    public void closeBrowser() {
        try {
            ThucydidesWebDriverSupport.getDriver().quit();
        } catch (Exception e) {
            System.out.println("El navegador ya estaba cerrado o no se pudo apagar: " + e.getMessage());
        }
    }
}
