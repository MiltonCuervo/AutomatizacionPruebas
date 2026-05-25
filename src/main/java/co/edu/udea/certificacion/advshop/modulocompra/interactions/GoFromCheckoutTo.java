package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CartPageElements;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class GoFromCheckoutTo implements Interaction {

    public GoFromCheckoutTo() {}
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
        Click.on(CartPageElements.CART_ICON),
        Click.on(CartPageElements.CHECKOUT_BUTTON), 
        WaitUntil.the(CheckoutPage.REGISTRATION_BTN, isVisible()).forNoMoreThan(10).seconds(),
        Click.on(CheckoutPage.REGISTRATION_BTN)
    );
    }

    public static GoFromCheckoutTo register() {
        return new GoFromCheckoutTo();
    }
}
