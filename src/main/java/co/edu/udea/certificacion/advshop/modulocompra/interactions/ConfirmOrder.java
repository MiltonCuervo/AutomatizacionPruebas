package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ConfirmOrder implements Interaction {

    public static ConfirmOrder purchase() {
        return Tasks.instrumented(ConfirmOrder.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.BTN_PAY_NOW_SAFE_PAY, isVisible())
                        .forNoMoreThan(10).seconds(),
                Click.on(CheckoutPage.BTN_PAY_NOW_SAFE_PAY)
        );
    }

}