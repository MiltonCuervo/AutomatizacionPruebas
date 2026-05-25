package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CheckoutPageElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;

public class SelectThe implements Interaction {

    private final String paymentMethod;

    public SelectThe(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static SelectThe paymentMethod(String paymentMethod) {
        return Tasks.instrumented(SelectThe.class, paymentMethod);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (paymentMethod.equalsIgnoreCase("Master Credit")) {
            actor.attemptsTo(
                    // isPresent() instead of isVisible() — radio has opacity:0
                    WaitUntil.the(CheckoutPageElements.RADIO_MASTER_CREDIT, isPresent())
                            .forNoMoreThan(10).seconds(),
                    // JavaScriptClick bypasses opacity:0 restriction
                    JavaScriptClick.on(CheckoutPageElements.RADIO_MASTER_CREDIT)
            );
        } else if (paymentMethod.equalsIgnoreCase("Safe Pay")) {
            actor.attemptsTo(
                    WaitUntil.the(CheckoutPageElements.RADIO_SAFE_PAY, isPresent())
                            .forNoMoreThan(10).seconds(),
                    JavaScriptClick.on(CheckoutPageElements.RADIO_SAFE_PAY)
            );
        }
    }
}

