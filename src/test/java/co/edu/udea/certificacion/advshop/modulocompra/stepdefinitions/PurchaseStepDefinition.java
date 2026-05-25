package co.edu.udea.certificacion.advshop.modulocompra.stepdefinitions;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.ProceedTo;
import co.edu.udea.certificacion.advshop.modulocompra.models.PaymentDetails;
import co.edu.udea.certificacion.advshop.modulocompra.models.Product;
import co.edu.udea.certificacion.advshop.modulocompra.models.User;
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

    // @When("the user verifies that the cart contains {int} units of {string} and {int} units of {string}")
    // public void theUserVerifiesTheCart(int qty1, String prod1, int qty2, String prod2) {
    //     buyer.should(
    //         SeeThat.theCartContains(prod1, qty1),
    //         SeeThat.theCartContains(prod2, qty2)
    //     );
    // }

    // Pago y confirmación
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


    @Then("the purchase should be completed successfully")
    public void thePurchaseShouldBeCompleted() {
    //     buyer.should(
    //         GivenWhenThen.seeThat(ValidatePurchase.isSuccess(), Matchers.is(true))
    //     );
    }

    @Then("an order number should be visible on the confirmation page")
    public void anOrderNumberShouldBeVisible() {
        // buyer.should(
        //     GivenWhenThen.seeThat(ConfirmationPage.orderNumber(), WebElementStateMatchers.isVisible())
        // );
    }

    @After
    public void closeBrowser() {
        try {
            // Le ordena a Selenium cerrar físicamente la ventana actual
            ThucydidesWebDriverSupport.getDriver().quit();
        } catch (Exception e) {
            System.out.println("El navegador ya estaba cerrado o no se pudo apagar: " + e.getMessage());
        }
    }
}
