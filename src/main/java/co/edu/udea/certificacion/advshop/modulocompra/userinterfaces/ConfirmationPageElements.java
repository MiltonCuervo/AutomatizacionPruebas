package co.edu.udea.certificacion.advshop.modulocompra.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class ConfirmationPageElements {

    public static final Target ORDER_CONFIRMATION_HEADER = Target.the("order confirmation header")
            .locatedBy("//*[@id='orderPaymentSuccess']/h2/span");

    public static final Target ORDER_NUMBER_LABEL = Target.the("order number label")
            .locatedBy("//*[@id='orderPaymentSuccess']//label[contains(@class,'ng-binding')]");

    public static final Target ORDER_SUMMARY_TABLE = Target.the("order summary table")
            .locatedBy("//*[@id='orderPaymentSuccess']//table");
}