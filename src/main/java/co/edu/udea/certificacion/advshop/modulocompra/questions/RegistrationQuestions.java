package co.edu.udea.certificacion.advshop.modulocompra.questions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.RegisterPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Presence;
import net.serenitybdd.screenplay.questions.Text;
import org.openqa.selenium.By;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

/**
 * Questions relacionadas con el estado del formulario de registro:
 * presencia de mensajes de error y su contenido.
 */
public class RegistrationQuestions {

    /** ¿El error de "username ya tomado" está visible? */
    public static Question<Boolean> usernameTakenErrorIsVisible() {
        return actor -> {
            try {
                var driver = BrowseTheWeb.as(actor).getDriver();
                var elements = driver.findElements(
                        By.xpath("//label[contains(@class,'invalid') and contains(text(),'User name already exists')]")
                );
                return !elements.isEmpty() && elements.get(0).isDisplayed();
            } catch (Exception e) {
                return false;
            }
        };
    }

    /** ¿El error de username requerido está visible? */
    public static Question<Boolean> usernameRequiredErrorIsVisible() {
        return actor -> Presence.of(RegisterPage.ERROR_USERNAME_REQUIRED).answeredBy(actor);
    }

    /** ¿El error de email requerido está visible? */
    public static Question<Boolean> emailRequiredErrorIsVisible() {
        return actor -> Presence.of(RegisterPage.ERROR_EMAIL_REQUIRED).answeredBy(actor);
    }

    /** ¿El error de formato de email inválido está visible? */
    public static Question<Boolean> emailInvalidErrorIsVisible() {
        return actor -> Presence.of(RegisterPage.ERROR_EMAIL_INVALID).answeredBy(actor);
    }

    /** ¿El error de password requerido está visible? */
    public static Question<Boolean> passwordRequiredErrorIsVisible() {
        return actor -> Presence.of(RegisterPage.ERROR_PASSWORD_REQUIRED).answeredBy(actor);
    }

    /** ¿El error de contraseñas no coinciden está visible? */
    public static Question<Boolean> passwordMismatchErrorIsVisible() {
        return actor -> Presence.of(RegisterPage.ERROR_PASSWORD_MISMATCH).answeredBy(actor);
    }

    /** ¿El error de términos no aceptados está visible? */
    public static Question<Boolean> termsNotAcceptedErrorIsVisible() {
        return actor -> Presence.of(RegisterPage.ERROR_TERMS_NOT_ACCEPTED).answeredBy(actor);
    }

    /** Texto del mensaje de error del campo username (útil para logs en Serenity) */
    public static Question<String> usernameTakenErrorText() {
        return actor -> Text.of(RegisterPage.ERROR_USERNAME_TAKEN).answeredBy(actor).trim();
    }
}