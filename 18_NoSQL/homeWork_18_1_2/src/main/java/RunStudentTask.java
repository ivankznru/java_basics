/**
 * Класс RunStudentTask.
 * Домашнее задание 18.1.2
 *
 *
 */
public class RunStudentTask {

    public static void main(String[] args) {
        RedisStorage rs = new RedisStorage();
        //подключаемся к docker контейнеру Redis
        rs.init();
        //добавляем данные
        rs.initData();
        //выводим все данные о студентах в консоль
        rs.hgettall();
        //увеличиваем количество пройденных заданий по заданному курсу.

        if (rs.increment("Ivanov I.I.", "Data Science", 1)) {
            System.out.println("OK");
            rs.hgettall();
        } else {
            System.out.println("System error.");
        }

    }
}


