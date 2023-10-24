// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class GestorePrenotazioniAerei {
    public static void main(String[] args) {
        FlightData flightData = FlightData.getInstance();

        Passenger passenger1 = new Passenger(1, "Alice");
        Passenger passenger2 = new Passenger(2, "Bob");
        Passenger passenger3 = new Passenger(3, "Marco");
        Passenger passenger4 = new Passenger(4, "Matteo");
        Passenger passenger5 = new Passenger(5, "Matteo");
        Passenger passenger6 = new Passenger(6, "Matteo");
        Passenger passenger7 = new Passenger(7, "Matteo");
        Passenger passenger8 = new Passenger(8, "Matteo");
        Passenger passenger9 = new Passenger(9, "Matteo");

        flightData.addAircraft("A123", "Boeing 737", 150);
        flightData.addAircraft("B456", "Airbus A320", 120);

        flightData.addFlight("F101", "A123", "JFK", "LAX", "10:00");
        flightData.addFlight("F102", "B456", "LAX", "JFK", "14:00");
        flightData.addFlight("F103", "A123", "SFO", "ORD", "08:00");



        List<Flight> flights = flightData.getFlightsSortedByDepartureTime();
        System.out.println("Flights sorted by departure time:");
        for (Flight flight : flights) {
            System.out.println(flight.getCode() + " - " + flight.getDepartureTime());
        }


        passenger1.makeReservation(flights.get(0), 1);
        passenger2.makeReservation(flights.get(2), 1);
        passenger3.makeReservation(flights.get(1), 1);
        passenger4.makeReservation(flights.get(1), 7);
        passenger5.makeReservation(flights.get(2), 6);
        passenger6.makeReservation(flights.get(0), 6);
        passenger7.makeReservation(flights.get(1), 3);
        passenger8.makeReservation(flights.get(0), 4);


        Flight flightF101 = flightData.getFlightByCode("F101");
        System.out.println("Reservations for Flight F101:");
        for (int day = 1; day <= 7; day++) {
            int passengerCount = flightF101.getPassengerCountForDay(day);
            System.out.println("Day " + day + ": " + passengerCount + " passengers.");
        }

        Flight flightF102 = flightData.getFlightByCode("F102");
        System.out.println("Reservations for Flight F102:");
        for (int day = 1; day <= 7; day++) {
            int passengerCount = flightF102.getPassengerCountForDay(day);
            System.out.println("Day " + day + ": " + passengerCount + " passengers.");
        }

        Flight flightF103 = flightData.getFlightByCode("F103");
        System.out.println("Reservations for Flight F103:");
        for (int day = 1; day <= 7; day++) {
            int passengerCount = flightF103.getPassengerCountForDay(day);
            System.out.println("Day " + day + ": " + passengerCount + " passengers.");
        }


    }
}