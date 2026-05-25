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
import static co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage.*;

public class AuthenticateThe implements Interaction {

    private final User user;

    public AuthenticateThe(User user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        // 1. Esperar que el loader desaparezca
        actor.attemptsTo(
                WaitUntil.the(LOADER, WebElementStateMatchers.isNotVisible())
                        .forNoMoreThan(15).seconds()
        );

        // 2. Abrir menú de usuario
        actor.attemptsTo(
                Click.on(BTN_USER_PROFILE),
                WaitTime.of(1) // esperar animación del modal
        );

        // 3. Navegar al formulario de registro
        actor.attemptsTo(
                WaitUntil.the(BTN_CREATE_NEW_ACCOUNT, WebElementStateMatchers.isClickable())
                        .forNoMoreThan(10).seconds(),
                Click.on(BTN_CREATE_NEW_ACCOUNT)
        );

        // 4. Llenar el formulario
        actor.attemptsTo(
                WaitUntil.the(INPUT_USERNAME, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds(),
                WaitTime.of(1),
                Enter.theValue(user.getUsername()).into(INPUT_USERNAME),
                Enter.theValue(user.getEmail()).into(INPUT_EMAIL),
                Enter.theValue(user.getPassword()).into(INPUT_PASSWORD),
                Enter.theValue(user.getPassword()).into(INPUT_CONFIRM_PASSWORD),
                WaitTime.of(1), // observar formulario completo antes de enviar
                Click.on(CHECKBOX_TERMS_AND_CONDITIONS),
                Click.on(BTN_REGISTER)
        );
    }

    public static AuthenticateThe userWithCredentials(User user) {
        return Tasks.instrumented(AuthenticateThe.class, user);
    }
}