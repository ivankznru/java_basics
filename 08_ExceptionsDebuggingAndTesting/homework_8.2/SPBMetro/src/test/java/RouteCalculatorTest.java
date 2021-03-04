import core.Line;
import core.Station;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@DisplayName("М8 — тест класса RouteCalculator")
class RouteCalculatorTest {
    List<Station> noTransferRoute;
    List<Station> oneTransfersRoute;
    List<Station> twoTransfersRoute;

    StationIndex stationIndex;
    RouteCalculator calculator;

    Station aRed;
    Station bRed;
    Station cRed;
    Station aBlue;
    Station bBlue;
    Station cBlue;
    Station aGreen;
    Station bGreen;
    Station cGreen;

    @BeforeEach
    void setUp() throws Exception {

/*               Test lines scheme
        Red            Blue          Green
        [a]            [a]            [a]
        [b]-[transfer]-[b]            [b]
        [c]            [c]-[transfer]-[c]
*/

        stationIndex = new StationIndex();

        Line line1 = new Line(1, "Red");
        Line line2 = new Line(2, "Blue");
        Line line3 = new Line(3, "Green");

        aRed = new Station("ARed", line1);
        bRed = new Station("BRed", line1);
        cRed = new Station("CRed", line1);
        aBlue = new Station("ABlue", line2);
        bBlue = new Station("BBlue", line2);
        cBlue = new Station("CBlue", line2);
        aGreen = new Station("AGreen", line3);
        bGreen = new Station("BGreen", line3);
        cGreen = new Station("CGreen", line3);

        Stream.of(line1, line2, line3).forEach(stationIndex::addLine);
        Stream.of(aRed, bRed, cRed, aBlue, bBlue, cBlue, aGreen, bGreen,
                cGreen).peek(s -> s.getLine().addStation(s)).forEach(stationIndex::addStation);

        stationIndex.addConnection(Stream.of(bRed, bBlue).collect(Collectors.toList()));

        stationIndex.addConnection(Stream.of(cBlue, cGreen).collect(Collectors.toList()));

        calculator = new RouteCalculator(stationIndex);


        noTransferRoute = Stream.of(aRed, bRed, cRed).collect(Collectors.toList());
        oneTransfersRoute = Stream.of(aRed, bRed, bBlue, aBlue).collect(Collectors.toList());
        twoTransfersRoute = Stream.of(aRed, bRed, bBlue, cBlue, cGreen, bGreen, aGreen).collect(Collectors.toList());
    }



    @DisplayName("Станции на одной линии")
    @Test
    void testRouteOnTheLine() {
        List<Station> actualNoTransfer = calculator.getShortestRoute(aRed, cRed);
        List<Station> expectedNoTransfers = noTransferRoute;
        assertEquals(expectedNoTransfers, actualNoTransfer);
    }

    @DisplayName("Есть один переход")
    @Test
    void testRouteWithOneConnection() {

        List<Station> actualOneTransfer = calculator.getShortestRoute(aRed, aBlue);
        List<Station> expectedOneTransfers = oneTransfersRoute;
        assertEquals(expectedOneTransfers, actualOneTransfer);
    }

    @DisplayName("Есть два перехода ")
    @Test
    void testRouteWithTwoConnection() {

        List<Station> actualTwoTransfers = calculator.getShortestRoute(aRed, aGreen);
        List<Station> expectedTwoTransfers = twoTransfersRoute;
        assertEquals(expectedTwoTransfers, actualTwoTransfers);
    }

    @DisplayName("Подсчет времени без пересадок")
    @Test
    void calculateDurationOnTheLine() {
        double actual = RouteCalculator.calculateDuration(noTransferRoute);
        double expected = 5;
        assertEquals(expected, actual, 0);
    }

    @DisplayName("Подсчет времени с одной пересадкой")
    @Test
    void calculateDurationWithOneConnection() {
        double actual = RouteCalculator.calculateDuration(oneTransfersRoute);
        double expected = 8.5;
        assertEquals(expected, actual, 0);
    }

    @DisplayName("Подсчет времени с двумя пересадами ")
    @Test
    void calculateDurationWithTwoConnection() {
        double actual = RouteCalculator.calculateDuration(twoTransfersRoute);
        double expected = 17;
        assertEquals(expected, actual, 0);
    }

    @AfterEach
    void tearDown()throws Exception {
        stationIndex = null;
        calculator = null;
        System.gc();
    }
}