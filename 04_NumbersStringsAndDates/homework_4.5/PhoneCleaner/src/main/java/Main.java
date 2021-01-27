import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            String phoneNumber = input.replaceAll("[^0-9]", "");

            if (phoneNumber.matches("^[7]+\\d{10}")) {
                System.out.println(phoneNumber);
            } else if (phoneNumber.matches("^[8]+\\d{10}")) {
                System.out.println(phoneNumber.replaceFirst("^[8]", "7"));
            } else if (phoneNumber.matches("^[9]+\\d{9}")) {
                System.out.println(phoneNumber.replaceFirst("^[9]", "79"));
            } else {
                System.out.println("Неверный формат номера\n");
            }
        }

    }
}
