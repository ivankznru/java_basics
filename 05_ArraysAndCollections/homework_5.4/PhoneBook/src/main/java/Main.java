import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private static PhoneBook phoneBookLists = new PhoneBook();
    private static boolean error = true;
    private static final String INVALID_INPUT ="Неверный формат ввода";


    public static void main(String[] args) {
        PhoneBook person = new PhoneBook();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите номер, имя или команду LIST");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            input.trim();


            if (input.matches("^[7][9][0-9]{9}")) {error=false;
                if (!phoneBookLists.equals(input)){
                System.out.println("Такого номера в телефонной книге нет");
                System.out.println("Введите имя абонента для номера " +  input);
                String name = scanner.nextLine();

                person.addContact(input,name);
                }
               else {person.getNameByPhone(input);}

            }

            // формат одного контакта "Имя - Телефон"
            // если контакт не найдены - вернуть пустую строку

            if (input.matches("^[А-Яа-я]+")) {error=false;
                if (!phoneBookLists.getAllContacts().contains(input));
                    System.out.println(phoneBookLists.getAllContacts()) ;
                System.out.println("Такого имени в телефонной книге нет.");
                System.out.println("Введите номер телефона для абонента " + input);
                String phone = scanner.nextLine();

               person.addContact(phone,input);

            }
            else {person.getPhonesByName(input);}


            if (input.matches("^LIST")) { error=false;
                System.out.println(person.getAllContacts());
            }
            if(error) {      System.out.println(INVALID_INPUT);}
        }
    }
}
