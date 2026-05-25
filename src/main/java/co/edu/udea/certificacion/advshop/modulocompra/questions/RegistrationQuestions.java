package co.edu.udea.certificacion.advshop.modulocompra.questions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ProductPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;

public class RegistrationQuestions {


    private static final Target LBL_USERNAME_ERROR = Target.the("Username taken error")
            .locatedBy("//label[contains(@class,'invalid') and @data-ng-show='!registerSuccess']");

    private static final Target LBL_PASSWORD_ERROR = Target.the("Password error")
            .locatedBy("//label[contains(@class,'invalid') and contains(.,'character')]");

    /** Error "User name already exists" — label con clase invalid */
    public static Question<Boolean> usernameTakenErrorIsVisible() {
        return actor -> {
            try {
                actor.attemptsTo(
                        WaitUntil.the(LBL_USERNAME_ERROR, WebElementStateMatchers.isVisible())
                                .forNoMoreThan(10).seconds()
                );
                return LBL_USERNAME_ERROR.resolveFor(actor).isVisible();
            } catch (Exception e) {
                return false;
            }
        };
    }

    /** Error de password corto — "Use 4 character or longer" */
    public static Question<Boolean> passwordErrorIsVisible() {
        return actor -> {
            try {
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
                actor.attemptsTo(
                        WaitUntil.the(LBL_USERNAME_ERROR, WebElementStateMatchers.isVisible())
                                .forNoMoreThan(10).seconds()
                );
                String actualMessage = LBL_USERNAME_ERROR.resolveFor(actor).getText();
                return actualMessage.contains(expectedMessage);
            } catch (Exception e) {
                return false;
            }
        };
    }

}