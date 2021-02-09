
import java.util.*;

public class PhoneBook {
    TreeMap<String, String> phoneBookLists = new TreeMap<>();

    public void addContact(String phone, String name) {

        if (name.matches("^[А-Яа-я]+") & (phone.matches("^[7][9][0-9]{9}"))) {

            if (phoneBookLists.containsValue(phone)) {

                for (Map.Entry<String, String> entry : phoneBookLists.entrySet()) {
                    if (Objects.equals(phone, entry.getValue())) {
                   //     phoneBookLists.replace;
                      phoneBookLists.remove((entry.getKey()));
                    }
                }
              phoneBookLists.put(name, phone);
                 System.out.println("Контакт добавлен");
            } else {
                 phoneBookLists.put(name, phone);
                 System.out.println("Контакт добавлен");
            }
        }
    }

    public String getNameByPhone(String phone) {
        //   получить ключ от значения
        //  one-to-one
        for (Map.Entry<String, String> entry : phoneBookLists.entrySet()) {
            if (Objects.equals(phone, entry.getValue())) {
                return entry.getKey() + " - " + phone;
            }
        }
        return null;
    }

    public Set<String> getPhonesByName(String name) {
        //   получить значение по ключу
        //  one-to-one
        Set<String> phonesByName = null;
        for (String key : phoneBookLists.keySet()) {
            if (phoneBookLists.containsKey(name)) {
            //   phonesByName = Collections.singleton(name + " - " + phoneBookLists.get(name));
                phonesByName= Set.of(name + " - " + phoneBookLists.get(name));
            } else {
                return phonesByName;
            }
        }
        return phonesByName;
    }


    public Set<String> getAllContacts() {

        Set<String> allContacts= Collections.EMPTY_SET;

        String a = "";


         for (String key : phoneBookLists.keySet()) {

             if (a.equals("")) {
                  allContacts = Set.of((a = a + key + " - " + phoneBookLists.get(key)));

               } else {
                 allContacts = Set.of(a = a + ", " + key + " - " + phoneBookLists.get(key));
               }
          }


        return allContacts;
    }
    public static void printMap(TreeMap<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println(key + " - " + map.get(key));
        }
    }
}