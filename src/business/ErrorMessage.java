package business;

public class ErrorMessage extends Exception {

    //Esta clase es la clase de las exceptions solo se llama cuando queremos crear una excepcion con un mensaje personalizado
    public ErrorMessage(String mensaje) {
        super(mensaje);
    }
}