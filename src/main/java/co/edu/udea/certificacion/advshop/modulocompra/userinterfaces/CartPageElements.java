package co.edu.udea.certificacion.advshop.modulocompra.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CartPageElements {

    public static final Target CART_ICON = Target.the("cart icon").locatedBy("//*[@id=\"shoppingCartLink\"]");


    public static Target productInCart(String productName) {
        return Target.the("Product '" + productName + "' in cart table")
                .locatedBy("//tr[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + productName.toLowerCase() + "')]//label[contains(@class, 'productName')]");
    }

    public static Target quantityForProduct(String productName) {
        return Target.the("Quantity label for '" + productName + "'")
                .locatedBy("//tr[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + productName.toLowerCase() + "')]//td[contains(@class, 'smollCell')]//label[not(contains(@class, 'quantityMobile')) and @class='ng-binding']");
    }
}