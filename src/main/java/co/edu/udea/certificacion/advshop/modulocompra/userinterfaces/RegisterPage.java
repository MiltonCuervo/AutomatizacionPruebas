package co.edu.udea.certificacion.advshop.modulocompra.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class RegisterPage {
      
    public static final Target INPUT_USERNAME = Target.the("input for username").locatedBy("//input[@name='usernameRegisterPage']");
    public static final Target INPUT_EMAIL = Target.the("input for email").locatedBy("//input[@name='emailRegisterPage']");
    public static final Target INPUT_PASSWORD = Target.the("input for password").locatedBy("//input[@name='passwordRegisterPage']");
    public static final Target INPUT_CONFIRM_PASSWORD = Target.the("input for confirming password").locatedBy("//input[@name='confirm_passwordRegisterPage']");
    public static final Target CHECKBOX_TERMS_AND_CONDITIONS = Target.the("checkbox for accepting terms and conditions").locatedBy("//input[@name='i_agree']");
    public static final Target BTN_REGISTER = Target.the("button to submit registration").locatedBy("//button[@id='register_btn']");
    
}