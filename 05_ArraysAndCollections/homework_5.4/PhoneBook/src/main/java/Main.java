import java.util.*;


public class Main {


    public static void main(String[] args) {


        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите номер, имя или команду LIST");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            input.trim();


            if (PhoneBook.isCorrectPhone(input)) {

                if (phoneBook.getAllContacts().isEmpty()) {
                    System.out.println("Телефонная книга пуста");
                    System.out.println("Введите имя абонента для номера " + input);
                    String name = scanner.nextLine();
                    phoneBook.addContact(input, name);
                    continue;
                }

                if (!phoneBook.isPhoneExist(input)) {

                    System.out.println("Такого номера в телефонной книге нет");
                    System.out.println("Введите имя абонента для номера " + input);
                    String name = scanner.nextLine();
                    if (phoneBook.isNameExist(name)) {

                        //  phoneBook.getPhoneBookLists().put(name, phoneBook.getPhoneBookLists().get(name) + "," + input);
                        phoneBook.addPhoneToName(name, input);
                        System.out.println("К абоненту " + name + ": добавлен телефон: " + input);
                        continue;
                    } else {
                        phoneBook.addContact(input, name);
                        continue;
                    }
                }
                // Телефон существует
                else {
                    System.out.println("Такой номер существует ");
                    System.out.println("Введите имя абонента для перезаписи " + input);
                    String name = scanner.nextLine();
                    phoneBook.addContact(input, name);
                    continue;
                }

            }


            if (PhoneBook.isCorrectName(input)) {
                if (phoneBook.getAllContacts().isEmpty()) {
                    System.out.println("Телефонная книга пуста");
                    System.out.println("Введите номер телефона для абонента " + input);
                    String phone = scanner.nextLine();
                    phoneBook.addContact(phone, input);
                    continue;
                } else if (!phoneBook.isNameExist(input)) {
                    System.out.println("Такого имени в телефонной книге нет.");
                    System.out.println("Введите номер телефона для абонента " + input);
                    String phone = scanner.nextLine();
                    phoneBook.addContact(phone, input);
                    continue;
                } else {
                    System.out.println("Имени " + input + " соответствует телефон " + phoneBook.getPhonesByName(input));
                    continue;
                }


            }


            if (input.matches("^LIST")) {

                System.out.println(phoneBook.getAllContacts().toString());
                continue;
            }

        }
    }
}

