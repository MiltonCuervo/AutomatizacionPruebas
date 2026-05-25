package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;

public class SelectPaymentMethod implements Interaction {

    private final String paymentMethod;

    public SelectPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static SelectPaymentMethod named(String paymentMethod) {
        return Tasks.instrumented(SelectPaymentMethod.class, paymentMethod);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (paymentMethod.equalsIgnoreCase("Master Credit")) {
            actor.attemptsTo(
                    // isPresent() instead of isVisible() — radio has opacity:0
                    WaitUntil.the(CheckoutPage.RADIO_MASTER_CREDIT, isPresent())
                            .forNoMoreThan(10).seconds(),
                    // JavaScriptClick bypasses opacity:0 restriction
                    JavaScriptClick.on(CheckoutPage.RADIO_MASTER_CREDIT)
            );
        } else if (paymentMethod.equalsIgnoreCase("Safe Pay")) {
            actor.attemptsTo(
                    WaitUntil.the(CheckoutPage.RADIO_SAFE_PAY, isPresent())
                            .forNoMoreThan(10).seconds(),
                    JavaScriptClick.on(CheckoutPage.RADIO_SAFE_PAY)
            );
        }
    }
}