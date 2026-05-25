package co.edu.udea.certificacion.advshop.modulocompra.questions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ProductPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;

public class RegistrationQuestions {

    // 1. Mapeamos los elementos usando Target (mucho más estable en Screenplay)
    // Usamos el punto (.) en el xpath en lugar de text() para ignorar los saltos de línea y espacios del HTML
    private static final Target LBL_USERNAME_ERROR = Target.the("Username taken error")
            .locatedBy("//label[contains(@class,'invalid') and @data-ng-show='!registerSuccess']");

    private static final Target LBL_PASSWORD_ERROR = Target.the("Password error")
            .locatedBy("//label[contains(@class,'invalid') and contains(.,'character')]");

    /** Error "User name already exists" — label con clase invalid */
    public static Question<Boolean> usernameTakenErrorIsVisible() {
        return actor -> {
            try {
                // 2. Aquí está la magia: obligamos al actor a ESPERAR hasta 10 segundos a que el mensaje aparezca
                actor.attemptsTo(
                        WaitUntil.the(LBL_USERNAME_ERROR, WebElementStateMatchers.isVisible())
                                .forNoMoreThan(10).seconds()
                );
                return LBL_USERNAME_ERROR.resolveFor(actor).isVisible();
            } catch (Exception e) {
                // Si pasan los 10 segundos y no aparece, caerá aquí y devolverá false
                return false;
            }
        };
    }

    /** Error de password corto — "Use 4 character or longer" */
    public static Question<Boolean> passwordErrorIsVisible() {
        return actor -> {
            try {
                // Aplicamos la misma protección para el error de contraseña
                actor.attemptsTo(
                        WaitUntil.the(LBL_PASSWORD_ERROR, WebElementStateMatchers.isVisible())
                                .forNoMoreThan(10).seconds()
                );
                return LBL_PASSWORD_ERROR.resolveFor(actor).isVisible();
            } catch (Exception e) {
                return false;
            }
        };
    }

    /** Cantidad actual en la página del producto */
    public static Question<String> currentQuantityOnProductPage() {
        return actor -> {
            try {
                return ProductPage.QUANTITY_FIELD
                        .resolveFor(actor)
                        .getAttribute("value")
                        .trim();
            } catch (Exception e) {
                return "0";
            }
        };
    }

    /** Valida un mensaje de error dinámico que llega desde el .feature */
    public static Question<Boolean> errorMessageIs(String expectedMessage) {
        return actor -> {
            try {
                // Esperamos a que aparezca el label de error
                actor.attemptsTo(
                        WaitUntil.the(LBL_USERNAME_ERROR, WebElementStateMatchers.isVisible())
                                .forNoMoreThan(10).seconds()
                );
                // Extraemos el texto y validamos que contenga el mensaje esperado
                String actualMessage = LBL_USERNAME_ERROR.resolveFor(actor).getText();
                return actualMessage.contains(expectedMessage);
            } catch (Exception e) {
                return false;
            }
        };
    }

}