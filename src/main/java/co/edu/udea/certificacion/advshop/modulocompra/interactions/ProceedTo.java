package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CartPageElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ProceedTo implements Interaction {

    public ProceedTo() {
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(CartPageElements.CART_ICON),
            WaitUntil.the(CartPageElements.CHECKOUT_BUTTON, isVisible())
             .forNoMoreThan(8).seconds(),
            Click.on(CartPageElements.CHECKOUT_BUTTON)
        );
    }

    public static ProceedTo checkout() {
        return Tasks.instrumented(ProceedTo.class);
    }
    
}
