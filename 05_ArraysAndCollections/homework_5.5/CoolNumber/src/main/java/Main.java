import java.util.HashSet;
import java.util.TreeSet;

public class Main {


    public static void main(String[] args) {

        CoolNumbers.generateCoolNumbers();
        boolean success = false;


        success = false;
        long startTime = System.nanoTime();
        success = CoolNumbers.binarySearchInList(CoolNumbers.generateCoolNumbers(), "Х666ОЕ50");
        System.out.println("Бинарный поиск : номер " + success + " , поиск занял " + (System.nanoTime() - startTime));

        success = false;
        startTime = System.nanoTime();
        success = CoolNumbers.searchInHashSet(new HashSet<>(CoolNumbers.generateCoolNumbers()), "Х666ОЕ50");
        System.out.println("Поиск в HashSet : номер " + success + ", поиск занял " + (System.nanoTime() - startTime));

        success = false;
        startTime = System.nanoTime();
        success = CoolNumbers.searchInTreeSet(new TreeSet<>(CoolNumbers.generateCoolNumbers()), "Х666ОЕ50");
        System.out.println("Поиск в TreeSet : номер " + success + " , поиск занял " + (System.nanoTime() - startTime));

        startTime = System.nanoTime();
        success = CoolNumbers.bruteForceSearchInList(CoolNumbers.generateCoolNumbers(), "Х666ОЕ50");
        System.out.println("Поиск перебором : номер " + success + "  , поиск занял " + (System.nanoTime() - startTime));
    }

}
// Бинарный поиск занял 11787929400 нс
// Поиск в HashSet занял 12983502000 нс
// Поиск в TreeSet  занял 13601527900 нс
// Поиск перебором после 15 мин не выдал результата
