package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;
import co.edu.udea.certificacion.advshop.modulocompra.models.Product;
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

    private final Product product;

    public AddProduct(Product product) {
        this.product = product;
    }

    public static AddProduct toCart(Product product) {
        return Tasks.instrumented(AddProduct.class, product);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(HomePage.categoryImage(product.getCategory()), isVisible()).forNoMoreThan(10).seconds(),
                Click.on(HomePage.categoryImage(product.getCategory())),

                WaitUntil.the(CategoryPage.productNamed(product.getName()), isPresent()).forNoMoreThan(10).seconds(),
                JavaScriptClick.on(CategoryPage.productNamed(product.getName())),

                WaitUntil.the(ProductPage.ADD_TO_CART_BUTTON, isEnabled()).forNoMoreThan(7).seconds(),
                SelectQuantity.of(product.getQuantity()),
                Click.on(ProductPage.ADD_TO_CART_BUTTON),

                Click.on(ProductPage.HOME_BUTTON),

                WaitUntil.the(HomePage.categoryImage(product.getCategory()), isVisible()).forNoMoreThan(5).seconds()
        );
    }
}