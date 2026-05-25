package co.edu.udea.certificacion.advshop.modulocompra.interactions;

import co.edu.udea.certificacion.advshop.modulocompra.userinterfaces.RegisterPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Interaction para intentar el registro con datos potencialmente inválidos.
 * A diferencia de AuthenticateThe, no navega al formulario — solo lo llena.
 * Si el formulario es inválido, usa JavaScriptClick para disparar validaciones.
 */
public class AttemptRegistration implements Interaction {

    private final String username;
    private final String email;
    private final String password;
    private final String confirmPassword;
    private final boolean acceptTerms;
    private final boolean formIsValid;

    private AttemptRegistration(Builder builder) {
        this.username        = builder.username;
        this.email           = builder.email;
        this.password        = builder.password;
        this.confirmPassword = builder.confirmPassword;
        this.acceptTerms     = builder.acceptTerms;
        this.formIsValid     = builder.formIsValid;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(RegisterPage.INPUT_USERNAME, isVisible()).forNoMoreThan(10).seconds()
        );

        if (username != null) {
            actor.attemptsTo(
                    WaitTime.of(1),
                    Enter.theValue(username).into(RegisterPage.INPUT_USERNAME)
            );
        }
        if (email != null) {
            actor.attemptsTo(
                    WaitTime.of(1),
                    Enter.theValue(email).into(RegisterPage.INPUT_EMAIL)
            );
        }
        if (password != null) {
            actor.attemptsTo(
                    WaitTime.of(1),
                    Enter.theValue(password).into(RegisterPage.INPUT_PASSWORD)
            );
        }
        if (confirmPassword != null) {
            actor.attemptsTo(
                    WaitTime.of(1),
                    Enter.theValue(confirmPassword).into(RegisterPage.INPUT_CONFIRM_PASSWORD)
            );
        }
        if (acceptTerms) {
            actor.attemptsTo(
                    WaitTime.of(1),
                    // Usamos JavaScriptClick porque los checkboxes de Advantage suelen estar ocultos
                    JavaScriptClick.on(RegisterPage.CHECKBOX_TERMS_AND_CONDITIONS)
            );
        }

        actor.attemptsTo(WaitTime.of(1));

        if (formIsValid) {
            // Formulario completo → botón habilitado → clic normal
            actor.attemptsTo(Click.on(RegisterPage.BTN_REGISTER));
        } else {
            // Formulario inválido → botón deshabilitado → JS para disparar validaciones
            actor.attemptsTo(JavaScriptClick.on(RegisterPage.BTN_REGISTER));
        }

        actor.attemptsTo(WaitTime.of(2));
    }

    // ── Builder ──────────────────────────────────────────────────────────────

    public static Builder withUsername(String username) {
        return new Builder().username(username);
    }

    public static Builder withEmptyFields() {
        return new Builder();
    }

    public static class Builder {
        private String  username        = null;
        private String  email           = null;
        private String  password        = null;
        private String  confirmPassword = null;
        private boolean acceptTerms     = false;
        private boolean formIsValid     = false;

        public Builder username(String v)        { this.username = v;        return this; }
        public Builder email(String v)           { this.email = v;           return this; }
        public Builder password(String v)        { this.password = v;        return this; }
        public Builder confirmPassword(String v) { this.confirmPassword = v; return this; }
        public Builder acceptingTerms()          { this.acceptTerms = true;  return this; }
        public Builder validForm()               { this.formIsValid = true;  return this; }

        public AttemptRegistration andSubmit() {
            return new AttemptRegistration(this);
        }
    }
}