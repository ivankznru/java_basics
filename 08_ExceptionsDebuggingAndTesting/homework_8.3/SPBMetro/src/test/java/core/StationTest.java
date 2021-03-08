package core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("М8 — тест класса core.Station")
class StationTest {
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
    }

    @AfterEach
    void tearDown() {
    }
    @DisplayName("Одна и таже станция")
    @Test
    void compareTo() {
        int actualStation = stationARed.compareTo(stationARed);
        assertEquals(0,actualStation);
    }
    @DisplayName("Разные станции")
    @Test
    void notCompareTo() {
        int actualStation = stationARed.compareTo(stationBRed);
        assertEquals(-1,actualStation);
    }
    @DisplayName("Cтанции равны - TRUE")
    @Test
    void testEquals() {
      boolean stationEquals = stationARed.equals(stationARed);
        assertEquals(true,stationEquals);
    }
    @DisplayName("Cтанции не равны - FALSE")
    @Test
    void testNotEquals() {
        boolean stationEquals = stationARed.equals(stationCRed);
        assertEquals(false,stationEquals);
    }
    @DisplayName("Получить линию")
    @Test
    void testGetLine() {
        Line actualLine = stationARed.getLine();
        assertEquals(line1,actualLine);
    }
    @DisplayName("Получить имя")
    @Test
    void testGetName() {
        String actualName = stationARed.getName();
        assertEquals("aRed",actualName);
    }
    @DisplayName("to String")
    @Test
    void testToString() {
        String actualName = stationARed.toString();
        assertEquals("aRed",actualName);
    }
}