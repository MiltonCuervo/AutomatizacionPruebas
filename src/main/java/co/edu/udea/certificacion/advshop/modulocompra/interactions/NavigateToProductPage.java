package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CategoryPage;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ProductPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavigateToProductPage implements Interaction {

    private final String product;
    private final String category;

    public NavigateToProductPage(String product, String category) {
        this.product = product;
        this.category = category;
    }

    public static NavigateToProductPage named(String product, String category) {
        return Tasks.instrumented(NavigateToProductPage.class, product, category);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(HomePage.categoryImage(category), isVisible()).forNoMoreThan(10).seconds(),
                WaitTime.of(1),
                Click.on(HomePage.categoryImage(category)),
                WaitUntil.the(CategoryPage.productNamed(product), isPresent()).forNoMoreThan(10).seconds(),
                WaitTime.of(1),
                JavaScriptClick.on(CategoryPage.productNamed(product)),
                WaitUntil.the(ProductPage.ADD_TO_CART_BUTTON, isEnabled()).forNoMoreThan(7).seconds(),
                WaitTime.of(1)
        );
    }
}