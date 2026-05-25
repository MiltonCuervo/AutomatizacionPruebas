package co.edu.udea.certificacion.advshop.modulocompra.tasks;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.ProceedTo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class GoToCheckout implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ProceedTo.checkout());
    }

    public static GoToCheckout page() {
        return Tasks.instrumented(GoToCheckout.class);
    }
    
}
