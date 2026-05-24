package co.edu.udea.certificacion.advshop.modulocompra.tasks;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class EnterThe implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

    }

    public static EnterThe information(){
        return Tasks.instrumented(EnterThe.class);
    }
}
