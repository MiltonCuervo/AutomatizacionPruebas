package co.edu.udea.certificacion.advshop.modulocompra.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPageElements {

    // ── Cart → Checkout ───────────────────────────────────────────
    public static final Target BTN_CHECKOUT = Target.the("checkout button")
            .locatedBy("//*[@id='checkOutButton']");

    // ── Shipping step ─────────────────────────────────────────────
    public static final Target BTN_NEXT = Target.the("next button")
            .locatedBy("//*[@id='next_btn']");

    // ── Payment method radio buttons ──────────────────────────────
    // Confirmed: div[1] = SafePay, div[2] = Master Credit
    public static final Target RADIO_SAFE_PAY = Target.the("Safe Pay radio button")
            .locatedBy("//*[@id='paymentMethod']/div/div[1]/div[1]/input");

    public static final Target RADIO_MASTER_CREDIT = Target.the("Master Credit radio button")
            .locatedBy("//*[@id='paymentMethod']/div/div[1]/div[2]/input");

    // ── Safe Pay fields ───────────────────────────────────────────
    public static final Target INPUT_SAFEPAY_USERNAME = Target.the("SafePay username input")
            .locatedBy("//*[@id='paymentMethod']/div/div[2]/sec-form/sec-view[1]/div/input");

    public static final Target INPUT_SAFEPAY_PASSWORD = Target.the("SafePay password input")
            .locatedBy("//*[@id='paymentMethod']/div/div[2]/sec-form/sec-view[2]/div/input");

    // ── Master Credit fields ──────────────────────────────────────
    // Confirmed via console: id="creditCard", name="card_number", maxlength="14"
    public static final Target INPUT_CARD_NUMBER = Target.the("credit card number input")
            .locatedBy("//*[@id='creditCard']");

    // Confirmed: exists in the form (Cardholder name field)
    public static final Target INPUT_CARDHOLDER_NAME = Target.the("cardholder name input")
            .locatedBy("//*[@id='paymentMethod']/div/div[4]/sec-form/div[1]/sec-view[2]/div/input");

    // Confirmed: options are numeric "01".."12" for month, "2026","2027".. for year
    public static final Target SELECT_EXPIRY_MONTH = Target.the("expiry month selector")
            .locatedBy("//*[@id='paymentMethod']/div/div[4]/sec-form/div[2]/div/sec-view[1]/div/select");

    public static final Target SELECT_EXPIRY_YEAR = Target.the("expiry year selector")
            .locatedBy("//*[@id='paymentMethod']/div/div[4]/sec-form/div[2]/div/sec-view[2]/div/select");

    public static final Target INPUT_CVV = Target.the("CVV input")
            .locatedBy("//*[@id='paymentMethod']/div/div[4]/sec-form/div[2]/sec-view/div/input");

    // ── Pay ───────────────────────────────────────────────────────
    public static final Target BTN_PAY_NOW = Target.the("pay now button")
            .locatedBy("//*[@id='pay_now_btn_ManualPayment']");
}
