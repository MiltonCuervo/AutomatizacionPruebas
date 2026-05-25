package co.edu.udea.certificacion.advshop.modulocompra.tasks;

import co.edu.udea.certificacion.advshop.modulocompra.interactions.ClickLogoutBtn;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class Logout implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ClickLogoutBtn.inTheStore());
    }

    public static Logout fromStore() {
        return Tasks.instrumented(Logout.class);
    }
    
}
