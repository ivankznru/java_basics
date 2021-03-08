package core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("М8 — тест класса core.Line")
class LineTest {
    ArrayList<Station> expectedStation;
    Station stationARed;
    Station stationBRed;
    Station stationCRed;
    Station stationABlue;
    Line line1;
    Line line2;
    @BeforeEach
    void setUp()throws Exception {
         /*               Test lines scheme
        Red            Blue
        [a]            [a]
        [b]-[transfer]-[b]
        [c]            [c]
*/
        line1= new Line(1 ,"RED");
        line2= new Line(2 ,"BLUE");
        stationARed = new Station("aRed", line1);
        stationBRed = new Station("bRed", line1);
        stationCRed = new Station("cRed", line1);
        stationABlue = new Station("aBlue", line2);
        expectedStation = new ArrayList<Station>();
    }

    @AfterEach
    void tearDown() {
    }
    @DisplayName("Получить номер линии")
    @Test
    void getNumber() {
     int actualNumber  = line1.getNumber();
        assertEquals(1,actualNumber);
    }
    @DisplayName("Получить имя линии")
    @Test
    void getName() {
       String actualName = line2.getName();
       assertEquals("BLUE",actualName);
    }
    @DisplayName("Добавить станцию")
    @Test
    void addStation() {
        expectedStation.add(stationABlue);
        line2.addStation(stationABlue);
        assertEquals(expectedStation,line2.getStations());
    }
    @DisplayName("Получить станции")
    @Test
    void getStations() {
        expectedStation.add(stationARed);
        expectedStation.add(stationBRed);
        expectedStation.add(stationCRed);
        line1.addStation(stationARed);
        line1.addStation(stationBRed);
        line1.addStation(stationCRed);

        assertEquals(expectedStation,line1.getStations());
    }
    @DisplayName("Одна и тажа линия")
    @Test
    void compareTo() {
        int actualLine = line1.compareTo(line1);
        assertEquals(0,actualLine);
    }
    @DisplayName("Разные линии")
    @Test
    void notCompareTo() {
        int actualLine = line1.compareTo(line2);
        assertEquals(-1,actualLine);
    }

    @DisplayName("Линии равны TRUE")
    @Test
    void testEquals() {
        boolean lineEquals = line2.equals(line2);
        assertEquals(true,lineEquals);
    }
    @DisplayName("Линии не равны - FALSE")
    @Test
    void testNotEquals() {
        boolean lineEquals = line2.equals(line1);
        assertEquals(false,lineEquals);
    }
}