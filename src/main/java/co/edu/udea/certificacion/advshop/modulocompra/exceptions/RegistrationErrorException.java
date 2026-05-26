package co.edu.udea.certificacion.advshop.modulocompra.exceptions;

public class RegistrationErrorException extends AssertionError {
    public static final String USERNAME_ALREADY_EXISTS_FAILED = "El mensaje de error por nombre de usuario duplicado no apareció o el texto no coincide.";

    public RegistrationErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}