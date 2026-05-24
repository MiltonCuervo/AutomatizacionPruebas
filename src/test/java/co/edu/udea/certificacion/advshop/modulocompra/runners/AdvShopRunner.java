package co.edu.udea.certificacion.advshop.modulocompra.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/complete_purchase.feature",
        glue = "co.edu.udea.certificacion.advshop.modulocompra.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class AdvShopRunner {}