package IllegalExceptionAddCustomerMethod;

public class IllegalFormatException extends RuntimeException {


    public IllegalFormatException() {
        super("Неверный формат команды.");
    }
}
