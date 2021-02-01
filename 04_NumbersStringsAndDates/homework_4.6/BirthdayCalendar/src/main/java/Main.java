import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {


    public static void main(String[] args) {

        int day = 19;
        int month = 10;
        int year = 1990;


    }

    public static String collectBirthdays(int year, int month, int day) {
        String collectBirthdays = " ";
        Calendar calendar = Calendar.getInstance();

        Calendar currentDay = new GregorianCalendar(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        Calendar birthDay = new GregorianCalendar(year, month - 1, day);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - E", Locale.US);


        if (currentDay.getTime().before(birthDay.getTime())) {
            collectBirthdays = "";
        }
        if (currentDay.getTime().equals(birthDay.getTime())) {
            collectBirthdays = 0 + " - " + dateFormat.format(birthDay.getTime());
        }
        if (currentDay.getTime().after(birthDay.getTime())) {
            String collectBirthdays1 = "";
            collectBirthdays = 0 + " - " + dateFormat.format(birthDay.getTime());
            birthDay.add(Calendar.YEAR, 1);
            if (currentDay.getTime().equals(birthDay.getTime())) {
                collectBirthdays1 = 1 + " - " + dateFormat.format(birthDay.getTime());
                collectBirthdays = collectBirthdays + System.lineSeparator() + collectBirthdays1;
            }

        }

        return collectBirthdays;
    }
}



