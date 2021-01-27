import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      String[] input = input.split("\\''");
      for (int i = 0; i < words.length; i++) {
        final String English_WORDS = "[a-zA-Z]";
        var isEnglishWord = words.equals(English_WORDS);
        s1 = isEnglishWord ? words[i] : s;
      input.trim();
     String[] s = input.split("\\s+",3);
      System.out.println( s );
    }
  }

}