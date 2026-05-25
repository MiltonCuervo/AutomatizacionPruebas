package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.RegisterPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class GoFromHomeTo implements Interaction {

    public GoFromHomeTo() {}

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                // 1. Esperar a que el loader de la página principal desaparezca
                WaitUntil.the(HomePage.LOADER, WebElementStateMatchers.isNotVisible())
                        .forNoMoreThan(10).seconds(),

                // 2. Abrir el menú de usuario
                Click.on(HomePage.BTN_USER_PROFILE),

                // 3. Esperar que el botón sea visible y clickeable
                WaitUntil.the(HomePage.BTN_CREATE_NEW_ACCOUNT, WebElementStateMatchers.isClickable())
                        .forNoMoreThan(10).seconds(),

                // 4. JS Click es más seguro aquí para evitar intercepciones del menú flotante
                JavaScriptClick.on(HomePage.BTN_CREATE_NEW_ACCOUNT),

                // 5. Garantizar que realmente llegamos a la página de registro
                WaitUntil.the(RegisterPage.INPUT_USERNAME, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
    }

    public static GoFromHomeTo register() {
        return new GoFromHomeTo();
    }
}