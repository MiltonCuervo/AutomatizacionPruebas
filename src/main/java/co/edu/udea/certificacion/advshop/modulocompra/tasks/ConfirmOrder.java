package co.edu.udea.certificacion.advshop.modulocompra.tasks;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CheckoutPageElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ConfirmOrder implements Task {

    public static ConfirmOrder purchase() {
        return Tasks.instrumented(ConfirmOrder.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutPageElements.BTN_PAY_NOW, isVisible())
                        .forNoMoreThan(10).seconds(),
                Click.on(CheckoutPageElements.BTN_PAY_NOW)
        );
    }
}