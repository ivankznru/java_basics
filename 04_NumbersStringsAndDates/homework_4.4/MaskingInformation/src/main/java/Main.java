public class Main {

    public static void main(String[] args) {

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {

        int indexFirst = text.indexOf('<');
        int indexSecond = text.indexOf('>');


        if (indexFirst > 0 && indexSecond > 0 && text.length() - text.lastIndexOf('<') == text.length() - indexFirst) {
            text = text.substring(0, indexFirst) + placeholder + text.substring(indexSecond + 1, text.length());
            return text;
        } else if (indexFirst > 0 && indexSecond > 0 && text.length() - text.lastIndexOf('<') != text.length() - indexFirst) {
            text = text.substring(0, indexFirst) + placeholder + text.substring(indexSecond + 1, text.lastIndexOf('<')) + placeholder + text.substring(text.lastIndexOf('>')+1, text.length());
            return text;
        } else {
            return text;
        }
    }
}

//String safe = searchAndReplaceDiamonds(«Номер кредитной карты <4008 1234 5678> 8912», «***»);
//В safe должна содержаться строка «Номер кредитной карты *** 8912».
