package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;

public class LogoutFromHome implements Interaction {

    public static LogoutFromHome now() {
        return new LogoutFromHome();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                // 1. Clic en el botón de perfil usando el localizador de tu equipo
                Click.on(HomePage.BTN_USER_PROFILE),

                // 2. Esperamos 1 segundo para que la animación del menú despliegue completamente
                WaitTime.of(1),

                // 3. Clic forzado por JavaScript en Sign Out usando el localizador de tu equipo
                JavaScriptClick.on(HomePage.BTN_SIGN_OUT),

                // 4. Esperamos 2 segundos para dar tiempo a que la página procese el cierre de sesión
                WaitTime.of(2)
        );
    }
}