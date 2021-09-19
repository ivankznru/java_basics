/**
 * Класс RunFlightsTask.
 * Демонcтрация выполнения задания 18.2.1.
 *
 */
public class RunFlightsTask {
    public static void main(String[] args) {
        RedisStorage rs = new RedisStorage();
        //Подключение к docker-контейнеру Redis.
        rs.init();
       // Загрузка в Redis данных по условию задания.
        rs.initData();
        //Вывод в консоль всех городов и цен на билет.
        rs.readAll();
        //Вывод 3 самых дешевых билетов в порядке возрастания.
        rs.showCheapestFlights(3);
        //Вывод 3 самых дорогих билетов в порядке убывания.
        rs.showMostExpensive(3);
    }
}
