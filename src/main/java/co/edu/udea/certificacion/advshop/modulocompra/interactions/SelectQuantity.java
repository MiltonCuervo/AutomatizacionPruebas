package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ProductPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;

public class SelectQuantity implements Interaction {

    private final int quantity;

    public SelectQuantity (int quantity) {
        this.quantity = quantity;
    }

    public static SelectQuantity of(int quantity) {
        return new SelectQuantity(quantity);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (int i = 1; i < quantity; i++) {
            actor.attemptsTo(
                    Click.on(ProductPage.INCREASE_QUANTITY_BUTTON)
            );
        }
    }
}
