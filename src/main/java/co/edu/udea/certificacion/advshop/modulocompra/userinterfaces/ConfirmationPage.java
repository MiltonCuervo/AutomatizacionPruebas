package co.edu.udea.certificacion.advshop.modulocompra.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class ConfirmationPage {
    public static final Target USER= Target.the("user confirmation").locatedBy("//*[@id=\"orderPaymentSuccess\"]/div/div[1]/div/div[1]/label");
    public static final Target PAYMENT_METHOD = Target.the("payment method confirmation").locatedBy("//*[@id=\"orderPaymentSuccess\"]/div/div[2]/div[1]/label");
    // public static final Target SUBTOTAL = Target.the("subtotal confirmation").locatedBy("//*[@id=\"orderPaymentSuccess\"]/div/div[3]/div[1]/label/a");
    public static final Target ORDER_NUMBER = Target.the("order number confirmation").locatedBy("//*[@id=\"orderNumberLabel\"]");
    public static final Target ORDER_CONFIRMATION_MESSAGE = Target.the("order confirmation message").locatedBy("//*[@id=\"orderPaymentSuccess\"]/h2/span");
}
