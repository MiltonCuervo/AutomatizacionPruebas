package co.edu.udea.certificacion.advshop.modulocompra.tasks;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.AuthenticateThe;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class EnterThe implements Task {

    private final String username;
    private final String email;
    private final String password;

    public EnterThe(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Aquí le pasas los datos del feature a la interacción refactorizada
        actor.attemptsTo(AuthenticateThe.userWithCredentials(username, email, password));
    }

    public static EnterThe information(String username, String email, String password) {
        return Tasks.instrumented(EnterThe.class, username, email, password);
    }
}
