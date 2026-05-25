package co.edu.udea.certificacion.advshop.modulocompra.questions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ConfirmationPageElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class OrderSummary implements Question<Boolean> {

    public static OrderSummary containsSelectedProducts() {
        return new OrderSummary();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return ConfirmationPageElements.ORDER_SUMMARY_TABLE
                    .resolveFor(actor).isVisible();
        } catch (Exception e) {
            return false;
        }
    }
}