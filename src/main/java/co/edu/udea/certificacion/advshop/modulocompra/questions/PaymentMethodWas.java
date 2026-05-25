package co.edu.udea.certificacion.advshop.modulocompra.questions;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import static co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ConfirmationPage.*;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class PaymentMethodWas implements Question<String> {

    @Override
    public String answeredBy(net.serenitybdd.screenplay.Actor actor) {
        actor.attemptsTo(WaitUntil.the(PAYMENT_METHOD, isVisible()).forNoMoreThan(10).seconds());
        
        String paymentMethod = PAYMENT_METHOD.resolveFor(actor).getText();
        
        return paymentMethod.trim();
    }  

    public static PaymentMethodWas value() {
        return new PaymentMethodWas();
    }
}