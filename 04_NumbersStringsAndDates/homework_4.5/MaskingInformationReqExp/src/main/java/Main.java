public class Main {

    public static void main(String[] args) {

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {

  if (text.matches("(.+<.+> ?.+)") || text.matches("(.+<.+>)")) {
          text =text.replaceAll("<.*?>",placeholder); //   "<.+>"-жадный
                                                            // <.*?> - ленивый
            return text;
        }

   //  if (text.matches("(.+<.+> ?.+? <.+> ?.+)")){
     //       text =text.replaceAll("<.*?>",placeholder);
     //       return text;
      //  }


        return text;
    }

}