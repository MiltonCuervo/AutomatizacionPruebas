package co.edu.udea.certificacion.advshop.modulocompra.tasks.exceptions;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.GoFromHomeTo;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.WaitTime; // Asumiendo que tu WaitTime es una interacción
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class NavigateToRegistrationForm implements Task {

    public static NavigateToRegistrationForm now() {
        return Tasks.instrumented(NavigateToRegistrationForm.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitTime.of(1),
                GoFromHomeTo.register(),
                WaitTime.of(2)
        );
    }
}