package co.edu.udea.certificacion.advshop.modulocompra.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class ProductPage {

    public static final Target INCREASE_QUANTITY_BUTTON =
            Target.the("increase quantity button")
                    .locatedBy("//div[@class='plus']");

    public static final Target DECREASE_QUANTITY_BUTTON =
            Target.the("decrease quantity button")
                    .locatedBy("//div[contains(@class,'minus')]");

    public static final Target QUANTITY_FIELD =
            Target.the("quantity field")
                    .locatedBy("//input[@name='quantity']");

    public static final Target ADD_TO_CART_BUTTON =
            Target.the("add to cart button")
                    .locatedBy("//*[@id='productProperties']/div[4]/button");

    public static final Target HOME_BUTTON =
            Target.the("home button")
                    .locatedBy("//a[@href='#/' and @ng-click='go_up()']");
}