package co.edu.udea.certificacion.advshop.modulocompra.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class RegisterPage {

    // ── Inputs ──────────────────────────────────────────────────────────────
    public static final Target INPUT_USERNAME =
            Target.the("input for username")
                    .locatedBy("//input[@name='usernameRegisterPage']");

    public static final Target INPUT_EMAIL =
            Target.the("input for email")
                    .locatedBy("//input[@name='emailRegisterPage']");

    public static final Target INPUT_PASSWORD =
            Target.the("input for password")
                    .locatedBy("//input[@name='passwordRegisterPage']");

    public static final Target INPUT_CONFIRM_PASSWORD =
            Target.the("input for confirming password")
                    .locatedBy("//input[@name='confirm_passwordRegisterPage']");

    public static final Target CHECKBOX_TERMS_AND_CONDITIONS =
            Target.the("checkbox for accepting terms and conditions")
                    .locatedBy("//input[@name='i_agree']");

    public static final Target BTN_REGISTER =
            Target.the("button to submit registration")
                    .locatedBy("//button[@id='register_btn']");

    // ──────────────────────────────────────────────────────

    /** "Username already exists" */
    public static final Target ERROR_USERNAME_TAKEN =
            Target.the("error: username already taken")
                    .locatedBy("//label[contains(@class,'invalid') and contains(text(),'User name already exists')]");

    /** Campo username vacío y tocado */
    public static final Target ERROR_USERNAME_REQUIRED =
            Target.the("error: username is required")
                    .locatedBy("//span[@ng-show=\"registrationForm.usernameRegisterPage.$error.required\"]");

    /** Campo email vacío y tocado */
    public static final Target ERROR_EMAIL_REQUIRED =
            Target.the("error: email is required")
                    .locatedBy("//span[@ng-show=\"registrationForm.emailRegisterPage.$error.required\"]");

    /** Formato de email inválido */
    public static final Target ERROR_EMAIL_INVALID =
            Target.the("error: invalid email format")
                    .locatedBy("//span[@ng-show=\"registrationForm.emailRegisterPage.$error.email\"]");

    /** Campo password vacío y tocado */
    public static final Target ERROR_PASSWORD_REQUIRED =
            Target.the("error: password is required")
                    .locatedBy("//span[@ng-show=\"registrationForm.passwordRegisterPage.$error.required\"]");

    /** Las contraseñas no coinciden */
    public static final Target ERROR_PASSWORD_MISMATCH =
            Target.the("error: passwords do not match")
                    .locatedBy("//span[@ng-show='isPasswordMatch == false']");

    /** Términos y condiciones no aceptados */
    public static final Target ERROR_TERMS_NOT_ACCEPTED =
            Target.the("error: terms not accepted")
                    .locatedBy("//span[@ng-show=\"registrationForm.i_agree.$error.required\"]");
}