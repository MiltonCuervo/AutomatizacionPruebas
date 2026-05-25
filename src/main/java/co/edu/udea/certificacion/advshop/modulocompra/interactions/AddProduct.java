package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CategoryPage;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ProductPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class AddProduct implements Interaction {

    private final String category;
    private final String product;
    private final int quantity;

    public AddProduct(String category, String product, int quantity) {
        this.category = category;
        this.product = product;
        this.quantity = quantity;
    }

    public static AddProduct toCart(String category, String product, int quantity) {
        return Tasks.instrumented(AddProduct.class, category, product, quantity);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(HomePage.categoryImage(category), isVisible()).forNoMoreThan(10).seconds(),
                Click.on(HomePage.categoryImage(category)),

                WaitUntil.the(CategoryPage.productNamed(product), isPresent()).forNoMoreThan(10).seconds(),
                JavaScriptClick.on(CategoryPage.productNamed(product)),

                WaitUntil.the(ProductPage.ADD_TO_CART_BUTTON, isEnabled()).forNoMoreThan(7).seconds(),
                SelectQuantity.of(quantity),
                Click.on(ProductPage.ADD_TO_CART_BUTTON),

                Click.on(ProductPage.HOME_BUTTON),

                WaitUntil.the(HomePage.categoryImage(category), isVisible()).forNoMoreThan(5).seconds()
        );
    }
}