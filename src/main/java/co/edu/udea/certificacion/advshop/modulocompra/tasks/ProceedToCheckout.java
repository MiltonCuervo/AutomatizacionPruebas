package co.edu.udea.certificacion.advshop.modulocompra.tasks;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CartPageElements;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CheckoutPageElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ProceedToCheckout implements Task {

    public static ProceedToCheckout fromTheCart() {
        return Tasks.instrumented(ProceedToCheckout.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CartPageElements.CART_ICON, isVisible())
                        .forNoMoreThan(10).seconds(),
                Click.on(CartPageElements.CART_ICON),

                WaitUntil.the(CheckoutPageElements.BTN_CHECKOUT, isVisible())
                        .forNoMoreThan(10).seconds(),
                Click.on(CheckoutPageElements.BTN_CHECKOUT)
        );
    }
}