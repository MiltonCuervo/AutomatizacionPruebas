package co.edu.udea.certificacion.advshop.modulocompra.tasks;

/*
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CategoryPage;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ProductPage;

 */
import co.edu.udea.certificacion.advshop.modulocompra.interactions.AddProduct;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.SelectQuantity;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CategoryPage;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.HomePage;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.ProductPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.annotations.Step;

public class BuyProduct implements Task {

    private final String category;
    private final String product;
    private final int quantity;

    public BuyProduct(String category, String product, int quantity) {
        this.category = category;
        this.product = product;
        this.quantity = quantity;
    }

    public static BuyProduct from(String category, String product, int quantity) {
        return new BuyProduct(category, product, quantity);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                AddProduct.toCart(category, product, quantity)
        );
    }
}