package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;

public class ClickLogoutBtn implements Interaction {

    public static ClickLogoutBtn inTheStore() {
        return new ClickLogoutBtn();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomePage.BTN_USER_PROFILE),
                WaitTime.of(1),
                JavaScriptClick.on(HomePage.BTN_SIGN_OUT),
                WaitTime.of(2)
        );
    }
}