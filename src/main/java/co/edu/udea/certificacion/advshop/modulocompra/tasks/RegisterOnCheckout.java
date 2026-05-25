package co.edu.udea.certificacion.advshop.modulocompra.tasks;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.AuthenticateThe;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.GoFromCheckoutTo;
import co.edu.udea.certificacion.advshop.modulocompra.models.User;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class RegisterOnCheckout implements Task{
    private final User user;

    public RegisterOnCheckout(User user) {
        this.user = user;
    }

    public static RegisterOnCheckout withData(User user) {
        return Tasks.instrumented(RegisterOnCheckout.class, user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(GoFromCheckoutTo.register());
        actor.attemptsTo(AuthenticateThe.userWithCredentials(user));
    }
}
