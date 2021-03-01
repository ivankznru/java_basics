package IllegalExceptionAddCustomerMethod;

// public class IllegalEmailException extends Exception {
public class IllegalEmailException extends RuntimeException {

    public IllegalEmailException() {
        super("Некорректный E-mail");
    }

}
