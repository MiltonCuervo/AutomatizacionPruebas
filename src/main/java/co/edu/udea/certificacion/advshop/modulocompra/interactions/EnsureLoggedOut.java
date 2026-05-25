package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.By;

/**
 * Verifica si hay sesión activa y la cierra antes de continuar.
 * Usada al inicio de cada escenario negativo de registro.
 */
public class EnsureLoggedOut implements Interaction {

    public static EnsureLoggedOut beforeProceeding() {
        return Tasks.instrumented(EnsureLoggedOut.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            var driver = BrowseTheWeb.as(actor).getDriver();

            // Verificar si el menú de usuario está presente en el DOM
            var profileButtons = driver.findElements(
                    By.xpath("//*[@id='menuUser']")
            );

            if (profileButtons.isEmpty()) return;

            // Abrir el menú para inspeccionar su contenido
            actor.attemptsTo(
                    Click.on(HomePage.BTN_USER_PROFILE),
                    WaitTime.of(1)
            );

            // Verificar si aparece "Sign out" (sesión activa)
            var signOutLinks = driver.findElements(
                    By.xpath("//a[contains(text(),'Sign out')]")
            );

            if (!signOutLinks.isEmpty() && signOutLinks.get(0).isDisplayed()) {
                // Hay sesión activa → cerrar sesión
                actor.attemptsTo(
                        Click.on(HomePage.BTN_SIGN_OUT),
                        WaitTime.of(2)
                );
            } else {
                // No hay sesión → cerrar el menú con clic en el mismo botón
                actor.attemptsTo(
                        Click.on(HomePage.BTN_USER_PROFILE),
                        WaitTime.of(1)
                );
            }
        } catch (Exception e) {
            // Sin sesión activa, continuar normalmente
        }
    }
}