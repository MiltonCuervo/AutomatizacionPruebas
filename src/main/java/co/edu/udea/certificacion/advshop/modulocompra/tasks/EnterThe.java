package co.edu.udea.certificacion.advshop.modulocompra.tasks;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.AuthenticateThe;
import co.edu.udea.certificacion.advshop.modulocompra.models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class EnterThe implements Task {

    private final User user;

    public EnterThe(User user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(AuthenticateThe.userWithCredentials(user));
    }

    public static EnterThe registerInformation(User user) {
        return Tasks.instrumented(EnterThe.class, user);
    }
}
