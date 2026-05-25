package co.edu.udea.certificacion.advshop.modulocompra.tasks;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.AuthenticateThe;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.GoFromHomeTo;
import co.edu.udea.certificacion.advshop.modulocompra.models.User;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class RegisterOnHome implements Task{
    private final User user;

    public RegisterOnHome(User user) {
        this.user = user;
    }

    public static RegisterOnHome withData(User user) {
        return Tasks.instrumented(RegisterOnHome.class, user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(GoFromHomeTo.register());
        actor.attemptsTo(AuthenticateThe.userWithCredentials(user));
    }
}