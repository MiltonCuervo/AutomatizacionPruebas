package co.edu.udea.certificacion.advshop.modulocompra.tasks.exceptions;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.AttemptRegistration;
import co.edu.udea.certificacion.advshop.modulocompra.interactions.WaitTime;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class TryInvalidRegistration implements Task {

    private final String username;
    private final String email;
    private final String password;

    public TryInvalidRegistration(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static TryInvalidRegistration withData(String username, String email, String password) {
        return Tasks.instrumented(TryInvalidRegistration.class, username, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitTime.of(1),
                AttemptRegistration.withUsername(username)
                        .email(email)
                        .password(password)
                        .confirmPassword(password)
                        .acceptingTerms()
                        .andSubmit()
        );
    }
}