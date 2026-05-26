package co.edu.udea.certificacion.advshop.modulocompra.exceptions;

public class PasswordValidationErrorException extends AssertionError {
    public static final String PASSWORD_TOO_SHORT_FAILED = "El sistema permitió avanzar o no mostró la alerta de validación para contraseñas inseguras/cortas.";

    public PasswordValidationErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}