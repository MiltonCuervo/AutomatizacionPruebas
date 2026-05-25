package co.edu.udea.certificacion.advshop.modulocompra.questions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ConfirmationPageElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class OrderNumber implements Question<Boolean> {

    public static OrderNumber isVisible() {
        return new OrderNumber();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return ConfirmationPageElements.ORDER_NUMBER_LABEL
                    .resolveFor(actor).isVisible();
        } catch (Exception e) {
            return false;
        }
    }
}