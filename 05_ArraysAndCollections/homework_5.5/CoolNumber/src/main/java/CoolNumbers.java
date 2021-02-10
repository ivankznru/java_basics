import java.util.*;

public class CoolNumbers {

    static final int MIN_REGION = 1;
    static final int MAX_REGION = 199;
    public static List<String> generateCoolNumbers() {

        String[] letters = {"С", "М", "Т", "В", "А", "Р", "О", "Н", "Е", "У", "Х", "К"};

        TreeSet<String> arrNumber = new TreeSet<>();

       for (int i = 0; i < letters.length; i++) {
           for (int j = 1; j <= 10; j++) {
               for (int reg = MIN_REGION; reg <= MAX_REGION; reg++) {
                   arrNumber.add(String.format("%s%03d%s%02d\n", letters[i], j, letters[i] + letters[i], reg));
              }
           }
      }

        for (int i = 111; i <= 999; i += 111) {
            for (int j = 0; j < letters.length; j++) {
                for (int p = 0; p < letters.length; p++) {
                    for (int s = 0; s < letters.length; s++) {
                        for (int reg = MIN_REGION; reg <= MAX_REGION; reg++) {
                            arrNumber.add(String.format("%s%03d%s%02d", letters[j], i, letters[p] + letters[s], reg));
                        }
                    }
                }
            }
        }
        ArrayList<String> arrNum = new ArrayList<>(arrNumber);
        return arrNum;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        // Поиск перебором
        boolean index = false;
        List<String> inList = new ArrayList<>(list);
        for (int i = 0; i < inList.size(); i++) {
            if (inList.contains(number)) {
                index = true;
            } else {
                index = false;
            }
        }
        return index;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        // Бинарный поиск
        List<String> list = new ArrayList<>(sortedList);
        Collections.sort(list);
        int index = Collections.binarySearch(list, number);
        if (index >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        //  Поиск в HashSet
        if (hashSet.contains(number)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        //  Поиск в TreeSet
        if (treeSet.contains(number)) {
            return true;
        } else {
            return false;
        }
    }

}
