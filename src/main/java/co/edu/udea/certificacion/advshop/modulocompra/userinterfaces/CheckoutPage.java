package co.edu.udea.certificacion.advshop.modulocompra.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage {

    public static final Target REGISTRATION_BTN = Target.the("registration button on checkout page").locatedBy("//button[@id='registration_btn']");
    public static final Target BTN_NEXT = Target.the("Button to proceed to payment method").locatedBy("//*[@id='next_btn']");

    public static final Target RADIO_SAFE_PAY = Target.the("Safe Pay radio button")
            .locatedBy("//*[@id='paymentMethod']/div/div[1]/div[1]/input");

    public static final Target RADIO_MASTER_CREDIT = Target.the("Master Credit radio button")
            .locatedBy("//*[@id='paymentMethod']/div/div[1]/div[2]/input");

    public static final Target INPUT_SAFEPAY_USERNAME = Target.the("SafePay username input")
            .locatedBy("//*[@id='paymentMethod']/div/div[2]/sec-form/sec-view[1]/div/input");

    public static final Target INPUT_SAFEPAY_PASSWORD = Target.the("SafePay password input")
            .locatedBy("//*[@id='paymentMethod']/div/div[2]/sec-form/sec-view[2]/div/input");

    public static final Target INPUT_CARD_NUMBER = Target.the("credit card number input")
            .locatedBy("//*[@id='creditCard']");

    // Confirmed: exists in the form (Cardholder name field)
    public static final Target INPUT_CARDHOLDER_NAME = Target.the("cardholder name input")
            .locatedBy("//*[@id='paymentMethod']/div/div[4]/sec-form/div[2]/sec-view/div/input");

    // Confirmed: options are numeric "01".."12" for month, "2026","2027".. for year
    public static final Target SELECT_EXPIRY_MONTH = Target.the("expiry month selector")
            .locatedBy("//*[@id='paymentMethod']/div/div[4]/sec-form/div[2]/div/sec-view[1]/div/select");

    public static final Target SELECT_EXPIRY_YEAR = Target.the("expiry year selector")
            .locatedBy("//*[@id='paymentMethod']/div/div[4]/sec-form/div[2]/div/sec-view[2]/div/select");

    public static final Target INPUT_CVV = Target.the("CVV input")
            .locatedBy("//*[@id='paymentMethod']/div/div[4]/sec-form/div[1]/sec-view[2]/div/input");

    // ── Pay ───────────────────────────────────────────────────────
    public static final Target BTN_PAY_NOW = Target.the("pay now button mastercard")
            .locatedBy("//*[@id='pay_now_btn_ManualPayment']");

        public static final Target BTN_PAY_NOW_SAFE_PAY = Target.the("pay now button safe pay")
            .locatedBy("//*[@id='pay_now_btn_SAFEPAY']");



}
