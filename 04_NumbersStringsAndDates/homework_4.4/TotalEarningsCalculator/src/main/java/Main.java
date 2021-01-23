public class Main {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        text.trim();
        text.indexOf('5');
        text.indexOf('7');
        text.lastIndexOf('3');
        text.substring(15, 19);
        text.substring(35, 39);
        text.substring(56, 61);
        int i = Integer.parseInt(text.substring(15, 19)) + Integer.parseInt(text.substring(35, 39)) + Integer.parseInt(text.substring(56, 61));
        String expected =String.valueOf(i);
        System.out.println(i);
        //  int space = text.indexOf('');
        // text.substring(0 , space);

        //Напишите код, который считает сумму заработка всех друзей.
        //
        //Используйте методы indexOf(), lastIndexOf(), substring() и trim().
        //
        //Использование регулярных выражений в данном задании не допускается.


    }

}