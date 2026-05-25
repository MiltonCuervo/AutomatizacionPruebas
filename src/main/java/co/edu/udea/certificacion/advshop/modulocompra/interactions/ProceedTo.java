package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CartPageElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class ProceedTo implements Interaction {

    public ProceedTo() {
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(CartPageElements.CART_ICON),
            Click.on(CartPageElements.CHECKOUT_BUTTON)
        );
    }

    public static ProceedTo checkout() {
        return Tasks.instrumented(ProceedTo.class);
    }
    
}
