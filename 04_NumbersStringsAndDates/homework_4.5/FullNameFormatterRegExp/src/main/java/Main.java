import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            if (input.matches("^[А-Яа-я-]+ [А-Яа-я]+ [А-Яа-я]+")) {
                String[] word = input.split("\\s");
                System.out.println("Фамилия: " + word[0]);
                System.out.println("Имя: " + word[1]);
                System.out.println("Отчество: " + word[2]);

            } else {
                System.out.println("Введенная строка не является ФИО");
            }
        }
    }

}