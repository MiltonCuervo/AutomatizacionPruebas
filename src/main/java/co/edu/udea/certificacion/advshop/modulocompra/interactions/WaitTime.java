package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

public class WaitTime implements Interaction {

    private final int seconds;

    public WaitTime(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            // Detiene el hilo de ejecución por los segundos indicados multiplicados por 1000 (milisegundos)
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Método estático para llamarlo de forma fluida: WaitTime.of(5)
    public static WaitTime of(int seconds) {
        return Tasks.instrumented(WaitTime.class, seconds);
    }
}