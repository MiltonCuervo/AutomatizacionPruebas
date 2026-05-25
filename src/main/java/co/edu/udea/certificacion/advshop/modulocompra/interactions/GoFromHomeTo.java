package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.RegisterPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class GoFromHomeTo implements Interaction {

    public GoFromHomeTo() {}

    @Override
    public <T extends Actor> void performAs(T actor) {
        // 1. Esperar a que el loader desaparezca
        actor.attemptsTo(
                WaitUntil.the(HomePage.LOADER, WebElementStateMatchers.isNotVisible())
                        .forNoMoreThan(10).seconds()
        );

        // 2. Disparar evento de clic infalible para el SVG del menú (igual que en el Logout)
        var driver = BrowseTheWeb.as(actor).getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement profileBtn = HomePage.BTN_USER_PROFILE.resolveFor(actor);

        String safeClickJs = "arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true, cancelable: true, view: window}));";
        js.executeScript(safeClickJs, profileBtn);

        // 3. Continuar con el clic en crear cuenta y validar llegada a la página
        actor.attemptsTo(
                WaitUntil.the(HomePage.BTN_CREATE_NEW_ACCOUNT, WebElementStateMatchers.isClickable())
                        .forNoMoreThan(10).seconds(),

                JavaScriptClick.on(HomePage.BTN_CREATE_NEW_ACCOUNT),

                WaitUntil.the(RegisterPage.INPUT_USERNAME, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
    }

    public static GoFromHomeTo register() {
        return new GoFromHomeTo();
    }
}