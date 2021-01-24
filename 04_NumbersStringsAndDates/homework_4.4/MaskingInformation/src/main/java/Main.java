public class Main {

    public static void main(String[] args) {

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
     //   text.trim();
       text.length();
    //   placeholder.trim();
       placeholder.length();

       String firstPart =" ";
       String secondPart=" ";
       String thirdPart=" ";
       int firstLess =text.indexOf('<');
       int firstMore =text.indexOf('>');
       int secondLess = text.lastIndexOf('<');
       int secondMore = text.lastIndexOf('>');

       String checkSecondLess = text.substring(firstLess+1, text.length());
       int checkLess =checkSecondLess.indexOf('<') ;
       String checkSecondMore = text.substring(firstMore+1, text.length());
       int checkMore =checkSecondMore.indexOf('>') ;

       if (firstLess>0 & firstMore>0 & checkLess>0 & checkMore >0 ){
            firstPart = text.substring(0,firstLess );
            secondPart = text.substring(firstMore+1, secondLess);
           thirdPart= text.substring( secondMore+1,text.length());
           text= firstPart + placeholder + secondPart + placeholder +thirdPart;

        }
       if
       (firstLess>0 & firstMore>0 & checkLess<0 & checkMore <0 ) {
            firstPart = text.substring(0,firstLess );
            secondPart = text.substring(firstMore+1, text.length());
            text = firstPart + placeholder + secondPart;

        };
        return text;

    }
}

//String safe = searchAndReplaceDiamonds(«Номер кредитной карты <4008 1234 5678> 8912», «***»);
//В safe должна содержаться строка «Номер кредитной карты *** 8912».
