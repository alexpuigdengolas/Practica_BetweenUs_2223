package business;

public class ErrorMessage extends Exception {
    public ErrorMessage(String mensaje) {
        super(mensaje);
    }
}