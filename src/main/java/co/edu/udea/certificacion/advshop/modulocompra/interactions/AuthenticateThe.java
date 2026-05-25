package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.models.User;

import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.RegisterPage.*;

public class AuthenticateThe implements Interaction {

    private final User user;

    public AuthenticateThe(User user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
            WaitUntil.the(INPUT_USERNAME, WebElementStateMatchers.isVisible())
                    .forNoMoreThan(10).seconds(),
            Enter.theValue(user.getUsername()).into(INPUT_USERNAME),
            Enter.theValue(user.getEmail()).into(INPUT_EMAIL),
            Enter.theValue(user.getPassword()).into(INPUT_PASSWORD),
            Enter.theValue(user.getPassword()).into(INPUT_CONFIRM_PASSWORD),
            
            Click.on(CHECKBOX_TERMS_AND_CONDITIONS),
            Click.on(BTN_REGISTER));
    }

    public static AuthenticateThe userWithCredentials(User user) {
        return Tasks.instrumented(AuthenticateThe.class, user);
    }

}
