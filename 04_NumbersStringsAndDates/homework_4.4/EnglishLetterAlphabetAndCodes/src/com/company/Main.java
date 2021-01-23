package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("English alphabet");
        for (char i = 'A'; i <= 'Z'; i++) {
            int c = i;
            System.out.println(i + " :" + c + ".");
        }
        for (char i = 'a'; i <= 'z'; i++) {
            int c = i;
            System.out.println(i + " :" + c + ".");
        }
        System.out.println("Русский алфавмт");
        for (char i = 'А'; i <= 'Я'; i++) {
            int c = i;

            System.out.println(i + " :" + c + ".");
        }
        for (char i = 'а'; i <= 'я'; i++) {
            int c = i;
            System.out.println(i + " :" + c + ".");
        }
    }
}
