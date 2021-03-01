package IllegalExceptionAddCustomerMethod;

public class IllegalNameException extends RuntimeException {


    public IllegalNameException() {
        super("Некорректное имя");
    }
}
