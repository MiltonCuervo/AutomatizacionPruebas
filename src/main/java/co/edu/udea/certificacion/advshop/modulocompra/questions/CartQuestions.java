package co.edu.udea.certificacion.advshop.modulocompra.questions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CartPageElements;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Presence;
import net.serenitybdd.screenplay.questions.Text;

public class CartQuestions {

    // Comprueba si la fila del producto está pintada en la tabla
    public static Question<Boolean> isProductPresent(String productName) {
        return actor -> Presence.of(CartPageElements.productInCart(productName))
                .answeredBy(actor);
    }

    // Obtiene el número exacto de la columna de cantidad de ese producto
    public static Question<String> quantityOf(String productName) {
        return actor -> CartPageElements.quantityForProduct(productName)
                .resolveFor(actor)
                .getAttribute("textContent")
                .trim()
                .replaceAll("[^0-9]", "");
    }
}