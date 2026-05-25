package co.edu.udea.certificacion.advshop.modulocompra.questions;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import static co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ConfirmationPage.*;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class OrderNumberIs implements Question<String> {

    @Override
    public String answeredBy(net.serenitybdd.screenplay.Actor actor) {
        
        actor.attemptsTo(WaitUntil.the(ORDER_NUMBER, isVisible()).forNoMoreThan(10).seconds());
        String orderNumber = ORDER_NUMBER.resolveFor(actor).getText();

        return orderNumber.trim();
    }

    public static OrderNumberIs value() {
        return new OrderNumberIs();
    }
}