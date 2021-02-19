
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

    private static String nameString = "^[А-Яа-яёЁ]+";
    private static String phoneString = "^[7][9][0-9]{9}";
    TreeMap<String, String> phoneBookLists = new TreeMap<>();
    TreeSet<String> phonesByName = new TreeSet<>();
   // HashSet<String> allContacts = new HashSet<>();
    Set<String> allContacts= new TreeSet<>();

    public PhoneBook (){};


    public void addContact(String phone, String name) {

        if (isCorrectName(name) & isCorrectPhone(phone) ) {

            if (isPhoneExist(phoneBookLists,phone)) {

                for (Map.Entry<String, String> entry : phoneBookLists.entrySet()) {
                    if (Objects.equals(phone, entry.getValue())) {

                        phoneBookLists.remove((entry.getKey()));
                    }
                }
                phoneBookLists.put(name, phone);
                System.out.println("Контакт ПЕРЕЗАПИСАН");
            } else {
                phoneBookLists.put(name, phone);
                System.out.println("Контакт добавлен");

            }
        }
        else {System.out.println ("Контакт не добавлен");}
    }


    public String getNameByPhone(String phone) {
        String nameByPhone ="";
        //   получить ключ от значения
        //  one-to-one
        Pattern phoneValue = Pattern.compile(phone);
        for (String key : phoneBookLists.keySet()) {

             System.out.println(key + " - " + phoneBookLists.get(key));
            Matcher matcherPhone = phoneValue.matcher(phoneBookLists.get(key));
            if (matcherPhone.find()){
           System.out.println ("Соответствие найдено "+ key + " - " + phoneBookLists.get(key));
                return key + " - " + phoneBookLists.get(key);
            }

        }
        return  nameByPhone;
    }


    public Set<String> getPhonesByName(String name) {
        //   получить значение по ключу
        //  one-to-one

        for (String key : phoneBookLists.keySet()) {
            if (phoneBookLists.containsKey(name)) {
                //   phonesByName = Collections.singleton(name + " - " + phoneBookLists.get(name));
                phonesByName= new TreeSet(Set.of(name + " - " + phoneBookLists.get(name)));
            } else {
                return null;
            }
        }
        return phonesByName;
    }


    public Set<String> getAllContacts() {

        String a = "";


        for (String key : phoneBookLists.keySet()) {


            if (a.equals("")) {
               allContacts = Set.of((a = a + key + " - " + phoneBookLists.get(key)));;
             //   allContacts  =Set.of( String.format("%s -  %d", key,phoneBookLists.get(key)));
        } else {
              allContacts = Set.of(a = a + ", " + key + " - " + phoneBookLists.get(key));
           }

        }

        return allContacts;
    }
    public static boolean isCorrectPhone(String phone) {
        if (phone.length() == 11 & Pattern.matches(phoneString,phone)) {
            return true;
        } else {
         return false;
        }
    }
    public static boolean isCorrectName (String name) {
        if ( Pattern.matches(nameString,name)) {
            return true;
        } else {
          return false;
        }
    }

    public static void printMap(TreeMap<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println(key + " - " + map.get(key));
        }
    }
    public static boolean isPhoneExist (TreeMap<String, String> map, String phone){

        // (phone-) 79000000001
        //matcherPhone.find()----------
        //                            |
        // (Миша-)   79000000000,79000000001,79000000002
       boolean isPhoneExist = false;
        Pattern phoneValue = Pattern.compile(phone);
        for (String key : map.keySet()) {
            //   System.out.println(key + " - " + phoneBook.phoneBookLists.get(key));
            Matcher matcherPhone = phoneValue.matcher(map.get(key));
            if (matcherPhone.find()){
                System.out.println ("Соответствие найдено "+ key + " - " + map.get(key));
                isPhoneExist =true;
            }
            else{isPhoneExist =false;}
        }
      return isPhoneExist;
    }

}