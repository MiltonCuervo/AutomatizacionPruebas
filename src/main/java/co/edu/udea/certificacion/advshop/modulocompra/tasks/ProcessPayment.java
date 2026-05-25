package co.edu.udea.certificacion.advshop.modulocompra.tasks;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.ConfirmOrder;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.FillMasterCreditDetails;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.FillSafePayDetails;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.SelectPaymentMethod;
import co.edu.udea.certificacion.advshop.modulocompra.models.PaymentDetails;
import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.annotations.Step;

public class ProcessPayment implements Task {

    private final PaymentDetails details;

    public ProcessPayment(PaymentDetails details) {
        this.details = details;
    }

    public static ProcessPayment with(PaymentDetails details) {
        return Tasks.instrumented(ProcessPayment.class, details);
    }

    @Override
    @Step("{0} processes payment with #details.method")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CheckoutPage.BTN_NEXT),
                SelectPaymentMethod.named(details.getMethod())
        );

        if (details.getMethod().equalsIgnoreCase("Master Credit")) {
            actor.attemptsTo(
                    FillMasterCreditDetails.withDetails(
                            details.getCardNumber(),
                            details.getCvv(),
                            details.getCardHolder(),
                            details.getExpiryMonth(),
                            details.getExpiryYear()
                    )
            );
        } else if (details.getMethod().equalsIgnoreCase("Safe Pay")) {
            actor.attemptsTo(
                    FillSafePayDetails.withCredentials(
                            details.getSafePayUsername(),
                            details.getSafePayPassword()
                    )
            );
        }

        actor.attemptsTo(ConfirmOrder.purchase());
    }
}