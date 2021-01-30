package com.company;

public class Main {

    public static void main(String[] args) {
        for(char i = '\u0430'; i <= '\u044f'; i++) {
          System.out.print((int) i + " ");
        }

        for(char i='A'; i<='Z'; i++) {
            int c = i;
            System.out.println(i + " :" + c + ".");
        }
                   for(char i='a'; i<='z'; i++){
                int c=i;
                System.out.println(i+" :"+c+".");


               }
    }
}
