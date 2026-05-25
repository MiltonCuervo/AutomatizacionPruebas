package co.edu.udea.certificacion.advshop.modulocompra.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before("@negative")
    public void logScenarioStart(Scenario scenario) {
        System.out.println(">>> Iniciando escenario negativo: " + scenario.getName());
    }
}