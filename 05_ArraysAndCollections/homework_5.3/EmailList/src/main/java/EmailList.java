import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class EmailList {
    TreeSet<String> emailList = new TreeSet<>();

    public void add(String email) {
        if (email.matches("[^@]*@[^@.]*\\.[^@]*$")) {

            emailList.add(email.toLowerCase());//.... к строчному виду
        }
        else
        {
            System.out.println(Main.WRONG_EMAIL_ANSWER);// константа
        }

    }

    public List<String> getSortedEmails() {
// Согласно видео (5.8 Наборы уникальных элементов на 3.35 минуте ) если нам нужно упорядочить элементы
      //  в алфавитном порядке то мы используем TreeSet а не HashSet.
        //TreeSet<String> emailList;

        ArrayList<String>emails = new ArrayList<>(emailList);

        return emails;
    }

}

