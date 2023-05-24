package business;

/**
 * Esta clase servira para representar los mensajes de error
 */
public class ErrorMessage extends Exception {

    /**
     * Este es el constructor de esta clase
     * @param mensaje El mensaje que queremos mostrar
     */
    public ErrorMessage(String mensaje) {
        super(mensaje);
    }
}