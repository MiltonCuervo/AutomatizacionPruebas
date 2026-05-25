package co.edu.udea.certificacion.advshop.modulocompra.questions;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import static co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ConfirmationPage.*;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class SubtotalIs implements Question<String> {

    @Override
    public String answeredBy(net.serenitybdd.screenplay.Actor actor) {
        
        actor.attemptsTo(WaitUntil.the(SUBTOTAL, isVisible()).forNoMoreThan(10).seconds());
        String subtotal = SUBTOTAL.resolveFor(actor).getText();

        return subtotal.trim();
    }

    public static SubtotalIs wellCalculated() {
        return new SubtotalIs();
    }
}