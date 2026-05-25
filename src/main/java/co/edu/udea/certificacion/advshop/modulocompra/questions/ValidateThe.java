package co.edu.udea.certificacion.advshop.modulocompra.questions;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class ValidateThe implements Question<String> {

    @Override
    public String answeredBy(net.serenitybdd.screenplay.Actor actor) {
        actor.attemptsTo(WaitUntil.the(HomePage.USERNAME_TEXT, isVisible()).forNoMoreThan(10).seconds());
        
        String usernameText = HomePage.USERNAME_TEXT.resolveFor(actor).getText();
        return usernameText.trim();
    }  

    public static ValidateThe userIsLoggedIn() {
        return new ValidateThe();
    }
}