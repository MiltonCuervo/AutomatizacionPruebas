package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillMasterCreditDetails implements Interaction {

    private final String cardNumber;
    private final String cvv;
    private final String cardholderName;
    private final String expiryMonth;
    private final String expiryYear;

    public FillMasterCreditDetails(String cardNumber, String cvv,
                                   String cardholderName,
                                   String expiryMonth, String expiryYear) {
        this.cardNumber     = cardNumber;
        this.cvv            = cvv;
        this.cardholderName = cardholderName;
        this.expiryMonth    = expiryMonth;
        this.expiryYear     = expiryYear;
    }

    public static FillMasterCreditDetails withDetails(String cardNumber, String cvv,
                                                      String cardholderName,
                                                      String expiryMonth, String expiryYear) {
        return Tasks.instrumented(FillMasterCreditDetails.class,
                cardNumber, cvv, cardholderName, expiryMonth, expiryYear);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                // isPresent() — fields may not be "visible" until MC radio is selected
                WaitUntil.the(CheckoutPage.INPUT_CARD_NUMBER, isPresent())
                        .forNoMoreThan(10).seconds(),

                Enter.theValue(cardNumber)
                        .into(CheckoutPage.INPUT_CARD_NUMBER),

                Enter.theValue(cvv)
                        .into(CheckoutPage.INPUT_CVV),

                SelectFromOptions.byVisibleText(expiryMonth)
                        .from(CheckoutPage.SELECT_EXPIRY_MONTH),

                SelectFromOptions.byVisibleText(expiryYear)
                        .from(CheckoutPage.SELECT_EXPIRY_YEAR),

                Enter.theValue(cardholderName)
                        .into(CheckoutPage.INPUT_CARDHOLDER_NAME)
        );
    }
}