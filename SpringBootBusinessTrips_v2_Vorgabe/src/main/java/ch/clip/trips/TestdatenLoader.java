package ch.clip.trips.init;

import ch.clip.trips.model.*;
import ch.clip.trips.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class TestdatenLoader {

    @Bean
    public CommandLineRunner loadData(
            HotelRepository hotelRepo,
            KundeRepository kundeRepo,
            ReiseRepository reiseRepo,
            LeistungRepository leistungRepo,
            BuchungRepository buchungRepo
    ) {
        return args -> {
            if (hotelRepo.count() > 0) return;

            // Hotels
            List<Hotel> hotels = List.of(
                    new Hotel(null, "The Plaza Hotel", "New York", 5, "reservations@theplazany.com", BigDecimal.valueOf(450.00)),
                    new Hotel(null, "Marina Bay Sands", "Singapore", 5, "info@marinabaysands.com", BigDecimal.valueOf(300.00)),
                    new Hotel(null, "Ritz-Carlton", "Tokyo", 5, "rc.tokyo@ritzcarlton.com", BigDecimal.valueOf(600.00)),
                    new Hotel(null, "Four Seasons", "Paris", 5, "paris@fourseasons.com", BigDecimal.valueOf(550.00)),
                    new Hotel(null, "Hotel Adlon Kempinski", "Berlin", 5, "info@hotel-adlon.de", BigDecimal.valueOf(400.00)),
                    new Hotel(null, "Waldorf Astoria", "Beverly Hills", 5, "beverly@waldorfastoria.com", BigDecimal.valueOf(700.00)),
                    new Hotel(null, "Mandarin Oriental", "Bangkok", 5, "contact@mandarinoriental.com", BigDecimal.valueOf(350.00)),
                    new Hotel(null, "The Fullerton Hotel", "Singapore", 5, "stay@fullertonhotels.com", BigDecimal.valueOf(320.00)),
                    new Hotel(null, "Park Hyatt", "Sydney", 5, "sydney@parkhyatt.com", BigDecimal.valueOf(380.00))
            );
            hotelRepo.saveAll(hotels);

            // Kunden
            List<Kunde> kunden = List.of(
                    new Kunde(null, "Anna", "Meier", "anna.meier@mail.com", "0791000001"),
                    new Kunde(null, "Tom", "Müller", "tom.mueller@mail.com", "0791000002"),
                    new Kunde(null, "Julia", "Weber", "julia.weber@mail.com", "0791000003"),
                    new Kunde(null, "Lukas", "Schneider", "lukas.schneider@mail.com", "0791000004"),
                    new Kunde(null, "Nina", "Keller", "nina.keller@mail.com", "0791000005"),
                    new Kunde(null, "David", "Huber", "david.huber@mail.com", "0791000006"),
                    new Kunde(null, "Lea", "Baumann", "lea.baumann@mail.com", "0791000007"),
                    new Kunde(null, "Simon", "Brunner", "simon.brunner@mail.com", "0791000008"),
                    new Kunde(null, "Laura", "Frei", "laura.frei@mail.com", "0791000009"),
                    new Kunde(null, "Jan", "Graf", "jan.graf@mail.com", "0791000010")
            );
            kundeRepo.saveAll(kunden);

            // Leistungen
            Hotel hotel = hotelRepo.findAll().get(0);
            List<Leistung> leistungen = leistungRepo.saveAll(List.of(
                    new Leistung(null, "Flughafen-Transfer mit Taxi", "Service", BigDecimal.valueOf(50.00), hotel),
                    new Leistung(null, "Flugticket 1. Klasse", "Flugzeug", BigDecimal.valueOf(800.00), hotel),
                    new Leistung(null, "Zugticket Business Class", "Zug", BigDecimal.valueOf(150.00), hotel),
                    new Leistung(null, "Private Limousine", "Transport", BigDecimal.valueOf(300.00), hotel),
                    new Leistung(null, "Flughafen-Transfer mit Privatjet", "Flugzeug", BigDecimal.valueOf(5000.00), hotel),
                    new Leistung(null, "Zugfahrt 1. Klasse", "Zug", BigDecimal.valueOf(250.00), hotel),
                    new Leistung(null, "VIP Transfer", "Transport", BigDecimal.valueOf(1000.00), hotel),
                    new Leistung(null, "Charterflug", "Flugzeug", BigDecimal.valueOf(1500.00), hotel),
                    new Leistung(null, "Schnellzugtickets", "Zug", BigDecimal.valueOf(120.00), hotel),
                    new Leistung(null, "Privater Transfer", "Transport", BigDecimal.valueOf(600.00), hotel)
            ));

            // Reisen
            for (int i = 0; i < 10; i++) {
                Reise reise = new Reise();
                reise.setZielort("Zielort " + (i + 1));
                reise.setBeschreibung("Geschäftsreise nach Zielort " + (i + 1));
                reise.setStartdatum(LocalDate.now().plusDays(i));
                reise.setEnddatum(LocalDate.now().plusDays(i + 3));
                reise.setHotel(hotels.get(i % hotels.size()));
                reise.setHauptkunde(kunden.get(i));
                reise.setLeistungen(List.of(leistungen.get(i % leistungen.size())));
                reiseRepo.save(reise);

                // Buchung für die Reise
                Buchung buchung = new Buchung();
                buchung.setKunde(kunden.get(i));
                buchung.setReise(reise);
                buchung.setBuchungsdatum(LocalDate.now().minusDays(2));
                buchung.setGesamtpreis(reise.getGesamtpreis());
                buchungRepo.save(buchung);
            }
        };
    }
}
