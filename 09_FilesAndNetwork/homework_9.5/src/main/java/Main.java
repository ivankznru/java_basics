import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private final static String JSON_File = "data/map.json";
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static List<Line> allLines;
    private static Map<String, Station> allStations = new TreeMap<>();
    private static List<TreeSet<Station>> allConnections = new ArrayList<>();
    private static StationIndex map = new StationIndex();

    public static void main(String[] args) {
        try {
            Parser metroParser = new Parser();
            allLines = metroParser.parseLines();
            allLines.forEach((line) -> {
                map.addLine(line);
                List<Station> stationsOfLine = metroParser.parseStations(line);
                map.addAllLineStations(line, stationsOfLine);
                stationsOfLine.forEach(el -> allStations.put(el.getName(), el));
            });
            allConnections = metroParser.parseConnections(allLines, allStations);
            allConnections.forEach(x -> map.addConnection(x));
            LOGGER.info("Всего переходов: " + allConnections.size() + ". Без дублей: " + map.getConnections().size());
            mapToFile(map, JSON_File);
            System.out.println("Всего станций прочитано: " + getStationsCount(JSON_File));
            System.out.println("Всего переходов прочитано: " + getConnectionsCount(JSON_File));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public static void mapToFile(StationIndex index, String file) throws IOException {
        String fileName = file.substring(file.lastIndexOf("/") + 1);
        String filePath = file.substring(0, file.lastIndexOf("/") + 1);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        if (new File(filePath).mkdir()) {
            LOGGER.info("Создана папка: " + Paths.get(filePath).toAbsolutePath());
        }
        FileWriter toJson = new FileWriter(filePath + fileName);
        gson.toJson(index, toJson);
        toJson.flush();
    }

    private static int getStationsCount(String jsonFile) throws IOException
    {
        BufferedReader jsonReader = new BufferedReader(new FileReader(jsonFile));
        JsonObject obj = new Gson().fromJson(jsonReader, JsonObject.class);
        JsonObject stationsObject = (JsonObject) obj.get("stations");
        int count = stationsObject.keySet().stream().mapToInt(lineNumber -> {
            JsonArray stationsArray = (JsonArray) stationsObject.get(lineNumber);
            return stationsArray.size();
        }).sum();
        jsonReader.close();
        return count;
    }

    private static int getConnectionsCount(String jsonFile) throws IOException {
        BufferedReader jsonReader = new BufferedReader(new FileReader(jsonFile));
        JsonObject obj = new Gson().fromJson(jsonReader, JsonObject.class);
        JsonArray connections = obj.getAsJsonArray("connections");
        int count = connections.size();
        jsonReader.close();
        return count;
    }
}




//Цель задания
//
//Научиться получать данные из HTML-страницы, создавать и читать JSON-файлы.
//
//Что нужно сделать
//
//Напишите программу, которая:
//
//1. Получает HTML-код страницы «Список станций Московского метрополитена» https://www.moscowmap.ru/metro.html#lines с помощью библиотеки jsoup.
//
//2. Парсит полученную страницу и получает из неё:
//
//Линии московского метро (получаете имя линии, номер линии, цвет парсить не надо).
//Станции московского метро (получаете имя станции, номер линии).
//3. Создаёт и записывает на диск JSON-файл со списком станций по линиям и списком линий по формату JSON-файла из проекта SPBMetro (файл map.json).
//
//4. Читает файл и выводит в консоль количество станций на каждой линии.
//
//Рекомендации
//
//По умолчанию Jsoup читает 2 Мб данных с запрашиваемой страницы. Чтобы снять это ограничение, необходимо добавить вызов метода maxBodySize(0), устанавливающий максимальное значение получаемых данных:
//Document doc = Jsoup.connect(URL).maxBodySize(0).get();
//значение 0 означает, что нет ограничений на принимаемый объём данных.
//
//При изучении кода страницы удобно использовать консоль разработчика в браузере. Для этого нажмите F12, перейдите во вкладку Elements и найдите тег <div id="metrodata">. В нём содержатся таблицы с линиями, станциями и пересадками. Обращайте внимание на классы, напишите селекторы на основе найденных классов. Посмотрите, как получать элементы по селекторам в документации JSoup.
//Для более быстрого поиска нужных селекторов используйте онлайн сервис jsoup https://try.jsoup.org/
//
//
//Критерии оценки
//
//«Зачёт» — программа получает данные с сайта, записывает в файл и выводит информацию о  количестве станций на линиях.
//«Незачёт» — задание не выполнено.
//
//
//
//Дополнительное задание*
//
//Цель задания
//
//Потренироваться в работе с библиотекой jsoup и формированием JSON-файлов.
//
//Что нужно сделать
//
//Пропарсите и записывайте в JSON-файл переходы между станциями в дополнение к линиям и станциям (коллекции имя станции, номер линии, между которым есть переходы).
//Выведите в консоль количество переходов в метро.
//Критерии оценки
//
//«Зачёт» — в консоль выводится количество переходов в московском метро.
//«Незачёт» — задание не выполнено.









