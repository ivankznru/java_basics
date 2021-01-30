import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class Main {

    public static void main(String[] args) {

        int day = 29;
        int month = 01;
        int year = 2021;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {
        String collectBirthdays=" ";
        Calendar currentDay = new GregorianCalendar();
        Calendar birthDay = new GregorianCalendar(year, month, day);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.-E");

        while (dateFormat.format(birthDay.getTime()).equals(dateFormat.format(currentDay.getTime()))) {

            birthDay.add(Calendar.DAY_OF_MONTH, 1);
            collectBirthdays= dateFormat.format(birthDay.getTime());
        }
        return collectBirthdays;

    }
}
