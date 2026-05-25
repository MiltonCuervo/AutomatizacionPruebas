package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class LogoutFromHome implements Interaction {

    public static LogoutFromHome now() {
        return Tasks.instrumented(LogoutFromHome.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            var driver = BrowseTheWeb.as(actor).getDriver();

            // 1. Cerrar el PopUp si está visible (aparece tras el registro)
            var popups = driver.findElements(By.xpath("//div[@class='PopUp' and @style='display: block;']"));
            if (!popups.isEmpty()) {
                // Ocultarlo via JS para que no intercepte los clics
                ((JavascriptExecutor) driver).executeScript(
                        "document.querySelector('.PopUp').style.display='none';"
                );
                actor.attemptsTo(WaitTime.of(1));
            }

            // 2. Verificar que el botón de perfil existe
            var profileBtn = driver.findElements(By.xpath("//*[@id='menuUser']"));
            if (profileBtn.isEmpty()) return;

            // 3. Clic normal en el menú de usuario
            actor.attemptsTo(
                    Click.on(HomePage.BTN_USER_PROFILE),
                    WaitTime.of(1)
            );

            // 4. Verificar si Sign out está visible
            var signOutOptions = driver.findElements(
                    By.xpath("//label[contains(text(),'Sign out')]"));

            if (!signOutOptions.isEmpty() && signOutOptions.get(0).isDisplayed()) {
                actor.attemptsTo(
                        Click.on(HomePage.BTN_SIGN_OUT),
                        WaitTime.of(2)
                );
            } else {
                // No hay sesión — cerrar el menú
                actor.attemptsTo(
                        Click.on(HomePage.BTN_USER_PROFILE),
                        WaitTime.of(1)
                );
            }
        } catch (Exception e) {
            // Sin sesión activa, continuar
        }
    }
}