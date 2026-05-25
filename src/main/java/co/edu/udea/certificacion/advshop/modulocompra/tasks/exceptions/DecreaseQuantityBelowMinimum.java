package co.edu.udea.certificacion.advshop.modulocompra.tasks.exceptions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ProductPage;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.WaitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class DecreaseQuantityBelowMinimum implements Task {

    public static DecreaseQuantityBelowMinimum multipleTimes() {
        return Tasks.instrumented(DecreaseQuantityBelowMinimum.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitTime.of(1),
                Click.on(ProductPage.DECREASE_QUANTITY_BUTTON),
                WaitTime.of(1),
                Click.on(ProductPage.DECREASE_QUANTITY_BUTTON),
                WaitTime.of(1),
                Click.on(ProductPage.DECREASE_QUANTITY_BUTTON),
                WaitTime.of(1)
        );
    }
}