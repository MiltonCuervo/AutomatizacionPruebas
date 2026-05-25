package co.edu.udea.certificacion.advshop.modulocompra.questions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ConfirmationPageElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class PurchaseConfirmation implements Question<Boolean> {

    public static PurchaseConfirmation isVisible() {
        return new PurchaseConfirmation();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            actor.attemptsTo(
                    WaitUntil.the(ConfirmationPageElements.ORDER_CONFIRMATION_HEADER,
                                    net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible())
                            .forNoMoreThan(15).seconds()
            );
            return ConfirmationPageElements.ORDER_CONFIRMATION_HEADER
                    .resolveFor(actor).isVisible();
        } catch (Exception e) {
            return false;
        }
    }
}