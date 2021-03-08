import core.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@DisplayName("М8 — тест класса StationIndex")
class StationIndexTest {
    StationIndex stationIndex;

    Map<Integer, Line> expectedNumber2line;
    TreeSet<Station> expectedStations;
    Map<Station, TreeSet<Station>> expectedConnections;

    Line line1 = new Line(1, "Red");
    Line line2 = new Line(2, "Blue");
    Line line3 = new Line(3, "Green");
    Station aRed = new Station("ARed", line1);
    Station bRed= new Station("BRed", line1);
    Station cRed = new Station("CRed", line1);
    Station aBlue = new Station("ABlue", line2);
    Station bBlue = new Station("BBlue", line2);
    Station cBlue = new Station("CBlue", line2);
    Station aGreen = new Station("AGreen", line3);
    Station bGreen = new Station("BGreen", line3);
    Station cGreen = new Station("CGreen", line3);

    @BeforeEach
    void setUp()throws Exception {

    /*               Test lines scheme
        Red            Blue          Green
        [a]            [a]            [a]
        [b]-[transfer]-[b]            [b]
        [c]            [c]-[transfer]-[c]
*/
        stationIndex = new StationIndex();
        expectedStations = Stream.of(this.aRed,bRed,cRed).collect(Collectors.toCollection(TreeSet::new));
        List<Line> expectedlinesList  = Stream.of(line1, line2, line3).collect(Collectors.toList());
        expectedNumber2line =expectedlinesList.stream().collect( Collectors.toMap(Line::getNumber, Function.identity()));



    }

    @AfterEach
    void tearDown() {
    }
    @DisplayName("Добавить станцию")
    @Test
    void addStation() {

        stationIndex.addStation(aRed);
        stationIndex.addStation(bRed);
        stationIndex.addStation(cRed);
     TreeSet<Station> actualStations= Stream.of(stationIndex.getStation("aRed"),stationIndex.getStation("bRed"),stationIndex.getStation("cRed")).collect(Collectors.toCollection(TreeSet::new)); ;
        assertEquals(expectedStations,actualStations);
    }
    @DisplayName("Добавить линию")
    @Test
    void addLine() {

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        List<Line> actuallinesList=Stream.of(stationIndex.getLine(1),stationIndex.getLine(2),stationIndex.getLine(3)).collect(Collectors.toList());
        Map<Integer, Line> actualNumber2line= actuallinesList.stream().collect( Collectors.toMap(Line::getNumber, Function.identity()));
        assertEquals(expectedNumber2line,actualNumber2line);
    }
    @DisplayName("Добавить переход")
    @Test
    void addConnection() {
        TreeSet<Station> expectedStationsOne = Stream.of(this.bBlue).collect(Collectors.toCollection(TreeSet::new));
        TreeSet<Station> expectedStationsTwo = Stream.of(this.cGreen).collect(Collectors.toCollection(TreeSet::new));
        stationIndex.addConnection(Stream.of(bRed, bBlue).collect(Collectors.toList()));
        stationIndex.addConnection(Stream.of(cBlue, cGreen).collect(Collectors.toList()));
        assertEquals(expectedStationsOne, stationIndex.getConnectedStations(bRed));
        assertEquals(expectedStationsTwo, stationIndex.getConnectedStations(cBlue));
    }
    @DisplayName("Найти линию по номеру")
    @Test
    void getLine() {
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        assertEquals(expectedNumber2line.get(2),stationIndex.getLine(2));
    }
    @DisplayName("Находим станцию по имени")
    @Test
    void getStation() {

        Station expectedStation = new Station("bGreen", line3);
        stationIndex.addStation(aRed);
        stationIndex.addStation(bRed);
        stationIndex.addStation(cRed);
        stationIndex.addStation(aBlue);
        stationIndex.addStation(bBlue);
        stationIndex.addStation(cBlue);
        stationIndex.addStation(aGreen);
        stationIndex.addStation(bGreen);
        Station actualStation = stationIndex.getStation("bGreen");
        assertEquals(expectedStation,actualStation);
    }
    @DisplayName("Находим станцию по имени и номеру линии")
    @Test
    void testGetStation() {

        Station expectedStation = new Station("bBlue", line2);
        stationIndex.addStation(aRed);
        stationIndex.addStation(bRed);
        stationIndex.addStation(cRed);
        stationIndex.addStation(aBlue);
        stationIndex.addStation(bBlue);
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        Station actualStation = stationIndex.getStation("bBlue", 2);
        assertEquals(expectedStation,actualStation);
    }
    @DisplayName("Находим пару станции перехода")
    @Test
    void getConnectedStations() {
        TreeSet<Station> expectedStationsOne = Stream.of(this.cGreen).collect(Collectors.toCollection(TreeSet::new));
        stationIndex.addConnection(Stream.of(bRed, bBlue).collect(Collectors.toList()));
        stationIndex.addConnection(Stream.of(cBlue, cGreen).collect(Collectors.toList()));
        assertEquals(expectedStationsOne, stationIndex.getConnectedStations(cBlue));

    }
}