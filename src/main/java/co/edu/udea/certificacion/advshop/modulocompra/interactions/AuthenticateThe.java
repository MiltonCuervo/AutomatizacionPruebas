package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.AdvantagePageElement.*;

public class AuthenticateThe implements Interaction {

    private final String username;
    private final String email;
    private final String password;

    // El constructor ahora recibe los datos dinámicos
    public AuthenticateThe(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(BTN_USER_PROFILE));
        actor.attemptsTo(
            WaitUntil.the(BTN_CREATE_NEW_ACCOUNT, WebElementStateMatchers.isVisible())
            .forNoMoreThan(10).seconds(), Click.on(BTN_CREATE_NEW_ACCOUNT));
        actor.attemptsTo(
            WaitUntil.the(INPUT_USERNAME, WebElementStateMatchers.isVisible())
                    .forNoMoreThan(10).seconds(),Enter.theValue(username).into(INPUT_USERNAME));
        actor.attemptsTo(
            Enter.theValue(email).into(INPUT_EMAIL),
            Enter.theValue(password).into(INPUT_PASSWORD),
            Enter.theValue(password).into(INPUT_CONFIRM_PASSWORD),
            Click.on(CHECKBOX_TERMS_AND_CONDITIONS),
            Click.on(BTN_REGISTER));
    }

    public static AuthenticateThe userWithCredentials(String username, String email, String password) {
        return Tasks.instrumented(AuthenticateThe.class, username, email, password);

    }
   /* private static String generarUsuarioAleatorio() {
    String time = String.valueOf(System.currentTimeMillis());
    // últimos 7 dígitos del tiempo (cambian cada milisegundo)
    String shortTime = time.substring(time.length() - 7);

    return "Rob_" + shortTime; // 11 caracteres en total
    }

    */

}
