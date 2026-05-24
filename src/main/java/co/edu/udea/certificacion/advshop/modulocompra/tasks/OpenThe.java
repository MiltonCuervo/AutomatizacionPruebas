package co.edu.udea.certificacion.advshop.modulocompra.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class OpenThe implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn().thePageNamed("pages.advantageonlineshopping"));

    }

    public static OpenThe browser() {
        return Tasks.instrumented(OpenThe.class);
    }
}
