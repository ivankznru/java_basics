import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {

        Stream<Terminal> streamTerminal = airport.getTerminals().stream();
        Stream<Flight> streamFlight = streamTerminal.flatMap(terminal -> terminal.getFlights().stream())
        //map(Function mapper) , flatMap(Function<T, Stream<R>> mapper) /Промежуточные операторы
        //  Специальные операторы для преобразования объектного стрима в примитивный,
        //  примитивного в объектный, либо примитивного стрима одного типа в примитивный стрим другого
                .filter(flight -> toLocalTime(flight.getDate()).isBefore(LocalTime.now().plusHours(2)))
                .filter(flight -> toLocalTime(flight.getDate()).isAfter(LocalTime.now()))
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE);
        //  .filter(flight -> flight.getType() == Flight.Type.ARRIVAL);
        List<Flight> listFlight = streamFlight.collect(Collectors.toList());
        //toList() Собирает элементы в List.
        //toSet() Собирает элементы в множество.
        return listFlight;

    }

    private static LocalTime toLocalTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

}