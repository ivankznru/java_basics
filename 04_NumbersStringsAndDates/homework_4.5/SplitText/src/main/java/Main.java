public class Main {

  public static void main(String[] args) {

  }

  public static String splitTextInToWords(String text) {
    String splitWord = "";
    text = text.replaceAll("[^a-zA-Z’]", "\r");
    text = text.replaceAll("\\s+", "\r");
     text = text.replaceAll("\\s+$", "");
    String[] words = text.split("''");
    for (int i = 0; i < words.length; i++) {
      splitWord= words[i].replaceAll("\\s", "\n");

    }
    return splitWord;
  }
}