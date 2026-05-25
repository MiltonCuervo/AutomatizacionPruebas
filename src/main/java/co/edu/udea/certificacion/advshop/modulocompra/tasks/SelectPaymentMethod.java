package co.edu.udea.certificacion.advshop.modulocompra.tasks;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.FillMasterCreditDetails;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.FillSafePayDetails;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.SelectThe;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class SelectPaymentMethod implements Task {

    private final String paymentMethod;

    public SelectPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static SelectPaymentMethod named(String paymentMethod) {
        return Tasks.instrumented(SelectPaymentMethod.class, paymentMethod);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SelectThe.paymentMethod(paymentMethod));

        if (paymentMethod.equalsIgnoreCase("Master Credit")) {
            actor.attemptsTo(
                    FillMasterCreditDetails.withDetails(
                            "12345678901234", // 14 chars max confirmed
                            "123",
                            "QA Buyer",
                            "12",             // numeric month
                            "2027"            // numeric year — confirmed in dropdown
                    )
            );
        } else if (paymentMethod.equalsIgnoreCase("Safe Pay")) {
            actor.attemptsTo(
                    FillSafePayDetails.withCredentials("CFSFe", "SafePay@123")
            );
        }
    }
}