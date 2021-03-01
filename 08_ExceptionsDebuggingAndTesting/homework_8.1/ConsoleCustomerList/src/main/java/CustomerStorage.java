import java.util.HashMap;
import java.util.Map;
import IllegalExceptionAddCustomerMethod.*;
public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    private final String REGEX_EMAIL = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";
    private final String REGEX_PHONE_NUMBER = "(\\s*)?(\\+)?([- ()]?\\d+[- ()]?){10,14}(\\s*)?";
    private final String REGEX_NAME = "([a-zA-Zа-яА-ЯёЁ -]+)";
    private final int RIGHT_COMPONENTS_LENGTH = 4;

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if (components.length != RIGHT_COMPONENTS_LENGTH) {
            throw new IllegalFormatException();
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        if (!name.matches(REGEX_NAME)) {
            throw new IllegalNameException();
        }
        if (!components[INDEX_PHONE].matches(REGEX_PHONE_NUMBER)) {
            throw new IllegalPhoneException();
        }
        if (!components[INDEX_EMAIL].matches(REGEX_EMAIL)) {
            throw new IllegalEmailException();
        }
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() throws Exception {
        if (storage.isEmpty()) {
            throw new Exception("List customers is empty.");
        }
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        if (!storage.containsKey(name)) {
            throw new IllegalArgumentException("This customer does not exist in the database.");
        }
        storage.remove(name);
    }

    public Customer getCustomer(String name) {

        return storage.get(name);
    }

    public int getCount() {

        return storage.size();
    }
}