package co.edu.udea.certificacion.advshop.modulocompra.questions;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import static co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ConfirmationPage.*;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class UserIs implements Question<String> {

    @Override
    public String answeredBy(net.serenitybdd.screenplay.Actor actor) {
        
        actor.attemptsTo(WaitUntil.the(USER, isVisible()).forNoMoreThan(10).seconds());
        String user = USER.resolveFor(actor).getText();

        return user.trim();
    }

    public static UserIs value() {
        return new UserIs();
    }
}