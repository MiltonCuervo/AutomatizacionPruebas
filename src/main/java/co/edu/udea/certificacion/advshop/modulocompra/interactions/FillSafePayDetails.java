package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillSafePayDetails implements Interaction {

    private final String username;
    private final String password;

    public FillSafePayDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static FillSafePayDetails withCredentials(String username, String password) {
        return Tasks.instrumented(FillSafePayDetails.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.INPUT_SAFEPAY_USERNAME, isVisible())
                        .forNoMoreThan(10).seconds(),
                Enter.theValue(username).into(CheckoutPage.INPUT_SAFEPAY_USERNAME),
                Enter.theValue(password).into(CheckoutPage.INPUT_SAFEPAY_PASSWORD)
        );
    }
}