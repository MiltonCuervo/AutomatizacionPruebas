package co.edu.udea.certificacion.advshop.modulocompra.tasks;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.AddProduct;
import co.edu.udea.certificacion.advshop.modulocompra.models.Product;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.Actor;

public class BuyProduct implements Task {

    private final Product product;

    public BuyProduct(Product product) {
        this.product = product;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(AddProduct.toCart(product));
    }

    public static BuyProduct from(Product product) {
        return Tasks.instrumented(BuyProduct.class, product);
    }
}