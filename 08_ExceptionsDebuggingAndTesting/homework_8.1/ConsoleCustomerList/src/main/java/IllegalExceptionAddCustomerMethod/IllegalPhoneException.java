package IllegalExceptionAddCustomerMethod;

public class IllegalPhoneException extends RuntimeException {
    public IllegalPhoneException() {
        super("Некорректный номер телефона");
    }
}
