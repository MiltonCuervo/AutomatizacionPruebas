package co.edu.udea.certificacion.advshop.modulocompra.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CategoryPage {

    public static Target productNamed(String productName) {
        return Target.the(productName + " product")
                .locatedBy("//a[contains(@class, 'productName') and (text()='" + productName + "' or translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + productName.toLowerCase() + "')]");
    }
}