package co.edu.udea.certificacion.advshop.modulocompra.questions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ProductPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;

public class RegistrationQuestions {

    /** Error "User name already exists" — label con clase invalid */
    public static Question<Boolean> usernameTakenErrorIsVisible() {
        return actor -> {
            try {
                var elements = BrowseTheWeb.as(actor).getDriver()
                        .findElements(By.xpath(
                                "//label[contains(@class,'invalid') and contains(text(),'User name already exists')]"));
                return !elements.isEmpty() && elements.get(0).isDisplayed();
            } catch (Exception e) {
                return false;
            }
        };
    }

    /** Error de password corto — "Use 4 character or longer" */
    public static Question<Boolean> passwordErrorIsVisible() {
        return actor -> {
            try {
                var elements = BrowseTheWeb.as(actor).getDriver()
                        .findElements(By.xpath(
                                "//label[contains(@class,'invalid') and contains(text(),'character')]"));
                return !elements.isEmpty() && elements.get(0).isDisplayed();
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
}