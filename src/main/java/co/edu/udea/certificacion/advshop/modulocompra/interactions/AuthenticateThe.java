package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.AdvantagePageElement.*;

public class AuthenticateThe implements Interaction {
    
    private static final String USERNAME = generarUsuarioAleatorio();
    private static final String PASSWORD = "Robinson123";
    private static final String CONFIRM_PASSWORD = "Robinson123";
    private static final String EMAIL = "robinson123@gmail.com";

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(BTN_USER_PROFILE));
        actor.attemptsTo(
            WaitUntil.the(BTN_CREATE_NEW_ACCOUNT, WebElementStateMatchers.isVisible())
            .forNoMoreThan(10).seconds(), Click.on(BTN_CREATE_NEW_ACCOUNT));
        actor.attemptsTo(
            Enter.theValue(USERNAME).into(INPUT_USERNAME),
            Enter.theValue(EMAIL).into(INPUT_EMAIL),
            Enter.theValue(PASSWORD).into(INPUT_PASSWORD),
            Enter.theValue(CONFIRM_PASSWORD).into(INPUT_CONFIRM_PASSWORD),
            Click.on(CHECKBOX_TERMS_AND_CONDITIONS),
            Click.on(BTN_REGISTER));
    }

    public static AuthenticateThe user(){
        return Tasks.instrumented(AuthenticateThe.class);
    }    

    private static String generarUsuarioAleatorio() {
    String time = String.valueOf(System.currentTimeMillis());
    // últimos 7 dígitos del tiempo (cambian cada milisegundo)
    String shortTime = time.substring(time.length() - 7); 
    
    return "Rob_" + shortTime; // 11 caracteres en total
    }
}
