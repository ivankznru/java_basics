public class Main {

    public static void main(String[] args) {

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {

    if (text.matches("(.+<.+>?.?+)") && !text.matches("(.+<.+>?.?+<.+>?.?+)")) {
            text =text.replaceAll("<.+>",placeholder);
            return text;
        }
        //     boolean b = text.matches("(.+<.+>.+?<.+>?.?+)");

     if (text.matches("(.+<.+>?.?+<.+>?.?+)")){
            text =text.replaceAll("<d+>",placeholder);
            return text;
        }
   String[] a =text.split(">");

        return text;
    }

}