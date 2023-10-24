import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class FlightData {
    private Map<String, Aircraft> aircraftMap = new HashMap<>();
    private Map<String, Flight> flightMap = new HashMap<>();
    private Map<String, List<Reservation>> reservationsMap = new HashMap();


    private static FlightData instance = null;

    private FlightData() {
    }

    public static FlightData getInstance() {
        if (instance == null) {
            instance = new FlightData();
        }
        return instance;
    }

    public void addAircraft(String code, String model, int totalSeats) {
        Aircraft aircraft = new Aircraft(code, model, totalSeats);
        aircraftMap.put(code, aircraft);
    }

    public void addFlight(String flightCode, String aircraftCode, String departureAirport, String arrivalAirport, String departureTime) {
        if (!aircraftMap.containsKey(aircraftCode)) {
            System.out.println("Aircraft with code " + aircraftCode + " not found.");
            return;
        }
        Aircraft aircraft = aircraftMap.get(aircraftCode);
        int totalSeats = aircraft.getTotalSeats();
        Flight flight = new Flight(flightCode, aircraft, departureAirport, arrivalAirport, departureTime, totalSeats);
        flightMap.put(flightCode, flight);
        List<Reservation> reservations = new ArrayList<>();
        for (int day = 1; day <= 7; day++) {
            Passenger passenger = new Passenger(day, "PassengerName" + day);
            Reservation reservation = new Reservation(flight, passenger, day);
            reservations.add(reservation);
        }
        reservationsMap.put(flightCode, reservations);
    }



    public List<Flight> getFlightsSortedByDepartureTime() {
        return flightMap.values()
                .stream()
                .sorted(Comparator.comparing(Flight::getDepartureTime))
                .collect(Collectors.toList());
    }

    public List<Flight> getFlightsByAircraft(String aircraftCode) {
        return flightMap.values()
                .stream()
                .filter(flight -> flight.getAircraft().getCode().equals(aircraftCode))
                .collect(Collectors.toList());
    }

    public List<Reservation> getReservationsForFlight(String flightCode) {
        if (reservationsMap.containsKey(flightCode)) {
            return reservationsMap.get(flightCode);
        } else {
            return new ArrayList<>();
        }
    }

    public void initializeReservations(String flightCode) {
        if (!reservationsMap.containsKey(flightCode)) {
            reservationsMap.put(flightCode, new ArrayList<>());
        }
    }

    public Flight getFlightByCode(String flightCode) {
        if (flightMap.containsKey(flightCode)) {
            return flightMap.get(flightCode);
        } else {
            // Se il volo non esiste, puoi restituire null o gestire l'errore in un altro modo, come lanciare un'eccezione.
            return null;
        }
    }

}


