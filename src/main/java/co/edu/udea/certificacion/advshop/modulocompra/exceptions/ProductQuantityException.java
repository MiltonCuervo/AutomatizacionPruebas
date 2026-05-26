package co.edu.udea.certificacion.advshop.modulocompra.exceptions;

public class ProductQuantityException extends AssertionError {
    public static final String QUANTITY_BELOW_MINIMUM_FAILED = "Error de negocio: El contador de unidades en la página del producto disminuyó por debajo del mínimo permitido (1).";

    public ProductQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}