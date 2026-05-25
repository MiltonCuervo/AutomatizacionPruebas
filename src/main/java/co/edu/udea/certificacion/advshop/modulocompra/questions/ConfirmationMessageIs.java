package co.edu.udea.certificacion.advshop.modulocompra.questions;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import static co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ConfirmationPage.*;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class ConfirmationMessageIs implements Question<String> {

    @Override
    public String answeredBy(net.serenitybdd.screenplay.Actor actor) {
        
        actor.attemptsTo(WaitUntil.the(ORDER_CONFIRMATION_MESSAGE, isVisible()).forNoMoreThan(10).seconds());
        String confirmationMessage = ORDER_CONFIRMATION_MESSAGE.resolveFor(actor).getText();

        return confirmationMessage.trim();
    }

    public static ConfirmationMessageIs value() {
        return new ConfirmationMessageIs();
    }
}