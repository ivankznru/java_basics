public class Main {

    public static void main(String[] args) {

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        text.trim();
       text.length();
       placeholder.trim();
       placeholder.length();

       String firstPart =" ";
       String secondPart=" ";
       String thirdPart=" ";
       int firstLess =text.indexOf('<');
       int firstMore =text.indexOf('>');
       int secondLess = text.lastIndexOf('<');
       int secondMore = text.lastIndexOf('>');

       if (firstLess>0 & firstMore>0 & firstLess>(text.length()-secondLess+2) ){
            firstPart = text.substring(0,firstLess );
            secondPart = text.substring(firstMore+1, secondLess);
           thirdPart= text.substring( secondMore+1,text.length());
           text= firstPart + placeholder + secondPart + placeholder +thirdPart;

        }
       if
       (firstLess>0 & firstMore>0 & firstLess<=(text.length()-secondLess+2)) {
            firstPart = text.substring(0,firstLess );
            secondPart = text.substring(firstMore+1, text.length());
            text = firstPart + placeholder + secondPart;

        };
        return text;

    }
}

//String safe = searchAndReplaceDiamonds(«Номер кредитной карты <4008 1234 5678> 8912», «***»);
//В safe должна содержаться строка «Номер кредитной карты *** 8912».
