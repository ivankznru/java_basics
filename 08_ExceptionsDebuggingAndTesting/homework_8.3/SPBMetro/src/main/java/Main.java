import core.Line;
import core.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Logger logger;
    private final static Logger LOGGER = LogManager.getLogger(Main.class);
    private final static Marker SEARCH_EXIST_STATION_MARKER = MarkerManager.getMarker("EXIST_STATION");
    private final static Marker INPUT_ERRORS = MarkerManager.getMarker("INPUT_ERRORS");

    private static final String DATA_FILE = "src/main/resources/map.json";
    private static Scanner scanner;

    private static StationIndex stationIndex;

    public static void main(String[] args) {
        RouteCalculator calculator = getRouteCalculator();
        logger = LogManager.getLogger();
        System.out.println("Программа расчёта маршрутов метрополитена Санкт-Петербурга\n");
        scanner = new Scanner(System.in);
        for (; ; ) {
            try {
                Station from = takeStation("Введите станцию отправления:");
                LOGGER.info(SEARCH_EXIST_STATION_MARKER, "Поиск станции отправления: '{}'", from);
                Station to = takeStation("Введите станцию назначения:");
                LOGGER.info(SEARCH_EXIST_STATION_MARKER, "Поиск станции назначения: '{}'", to);
                List<Station> route = calculator.getShortestRoute(from, to);
                System.out.println("Маршрут:");
                printRoute(route);

                System.out.println("Длительность: " +
                        RouteCalculator.calculateDuration(route) + " минут");
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    private static RouteCalculator getRouteCalculator() {
        createStationIndex();
        return new RouteCalculator(stationIndex);
    }

    private static void printRoute(List<Station> route) {
        Station previousStation = null;
        for (Station station : route) {
            if (previousStation != null) {
                Line prevLine = previousStation.getLine();
                Line nextLine = station.getLine();
                if (!prevLine.equals(nextLine)) {
                    System.out.println("\tПереход на станцию " +
                            station.getName() + " (" + nextLine.getName() + " линия)");
                }
            }
            System.out.println("\t" + station.getName());
            previousStation = station;
        }
    }

    private static Station takeStation(String message) {
        for (; ; ) {
            System.out.println(message);
            String line = scanner.nextLine().trim();
            Station station = stationIndex.getStation(line);
            if (station != null) {
                return station;
            }
            LOGGER.warn(INPUT_ERRORS, "Станция '{}' не найдена", line);
            System.out.println("Станция не найдена :(");
        }
    }

    private static void createStationIndex() {
        stationIndex = new StationIndex();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject);

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void parseConnections(JSONArray connectionsArray) {
        connectionsArray.forEach(connectionObject ->
        {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                int lineNumber = ((Long) itemObject.get("line")).intValue();
                String stationName = (String) itemObject.get("station");

                Station station = stationIndex.getStation(stationName, lineNumber);
                if (station == null) {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            stationIndex.addConnection(connectionStations);
        });
    }

    private static void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            int lineNumber = Integer.parseInt((String) lineNumberObject);
            Line line = stationIndex.getLine(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                Station station = new Station((String) stationObject, line);
                stationIndex.addStation(station);
                line.addStation(station);
            });
        });
    }

    private static void parseLines(JSONArray linesArray) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    ((Long) lineJsonObject.get("number")).intValue(),
                    (String) lineJsonObject.get("name")
            );
            stationIndex.addLine(line);
        });
    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}

//Сделайте три отдельных лога в папке logs проектa SPBMetro с помощью log4j2:
//
//logs/search.log — заполнять информацией о станциях, которые ищут (существующие станции),
//logs/input_errors.log — заполнять информацией об ошибочном вводе (несуществующие станции),
//logs/exceptions.log — вносить в лог информацию об исключениях (Exception).
//Критерии оценки