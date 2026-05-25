package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage.*;

public class GoFromHomeTo implements Interaction {
    public GoFromHomeTo() {}
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(BTN_USER_PROFILE));
        actor.attemptsTo(
            WaitUntil.the(BTN_CREATE_NEW_ACCOUNT, WebElementStateMatchers.isVisible())
            .forNoMoreThan(10).seconds(), Click.on(BTN_CREATE_NEW_ACCOUNT));
    }

    public static GoFromHomeTo register() {
        return new GoFromHomeTo();
    }
}
