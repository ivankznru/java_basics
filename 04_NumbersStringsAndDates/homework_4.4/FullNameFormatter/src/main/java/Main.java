import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String surName = " ";
        String name = " ";
        String middleName = " ";
        boolean hasSurName = false;
        boolean hasName = false;
        boolean hasMiddleName = false;
        boolean hasWhiteSpace = false;
        boolean hasError = true;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            input.trim();
            input.length();

            char[] chars = new char[input.length()];
            for (int i = 0; i < chars.length; i++) {
                char c = input.charAt(i);
                int a = (int) c;

                if ((a >= 1040) & (a <= 1103) | (a == 32) | (a == 45)) {
                    if (!hasSurName) {
                        surName = surName + String.valueOf(c);
                        if (a == 32) {
                            hasSurName = true;
                            hasWhiteSpace = true;
                        }
                    }
                    if ((!hasName) & (hasSurName)) {
                        if (!(a == 32)) {
                            hasWhiteSpace = false;
                        }
                        name = name + String.valueOf(c);
                        if ((a == 32) & (hasWhiteSpace == false)) {
                            hasName = true;
                            hasWhiteSpace = true;
                        }
                    }
                    if ((hasName) & (hasSurName) & (!hasMiddleName)) {
                        if (!(a == 32)) {
                            hasWhiteSpace = false;
                        }
                        middleName = middleName + String.valueOf(c);
                        hasError = false;
                        if ((a == 32) & (hasWhiteSpace == false)) {
                            hasMiddleName = true;
                            hasError = false;
                            hasWhiteSpace = true;
                        }
                    }
                    if ((hasName) & (hasSurName) & (hasMiddleName)) {
                        hasError = true;
                    }
                } else {
                    hasError = true;
                }
            }
            if (!hasError) {
                System.out.println("Фамилия: " + surName.trim());
                System.out.println("Имя: " + name.trim());
                System.out.println("Отчество: " + middleName.trim());
            } else {
                System.out.println("Введенная строка не является ФИО");
            }
        }
    }
}