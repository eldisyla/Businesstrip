package ch.clip.trips.init;

import ch.clip.trips.model.*;
import ch.clip.trips.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class TestdatenLoader {

    @Bean
    public CommandLineRunner loadData(
            HotelRepository hotelRepo,
            MitarbeiterRepository mitarbeiterRepo,
            KundeRepository kundeRepo,
            ReiseRepository reiseRepo,
            LeistungRepository leistungRepo,
            BuchungRepository buchungRepo,
            BuchungLeistungRepository buchungLeistungRepo
    ) {
        return args -> {
            // Hotels
            List<Hotel> hotels = List.of(
                    new Hotel(null, "Burj Al Arab", "Dubai", 7, "contact@burjalarab.com"),
                    new Hotel(null, "The Plaza Hotel", "New York", 5, "reservations@theplazany.com"),
                    new Hotel(null, "Marina Bay Sands", "Singapore", 5, "info@marinabaysands.com"),
                    new Hotel(null, "Ritz-Carlton", "Tokyo", 5, "rc.tokyo@ritzcarlton.com"),
                    new Hotel(null, "Four Seasons", "Paris", 5, "paris@fourseasons.com"),
                    new Hotel(null, "Hotel Adlon Kempinski", "Berlin", 5, "info@hotel-adlon.de"),
                    new Hotel(null, "Waldorf Astoria", "Beverly Hills", 5, "beverly@waldorfastoria.com"),
                    new Hotel(null, "Mandarin Oriental", "Bangkok", 5, "contact@mandarinoriental.com"),
                    new Hotel(null, "The Fullerton Hotel", "Singapore", 5, "stay@fullertonhotels.com"),
                    new Hotel(null, "Park Hyatt", "Sydney", 5, "sydney@parkhyatt.com")
            );
            hotelRepo.saveAll(hotels);

            // Mitarbeiter
            List<Mitarbeiter> mitarbeiter = List.of(
                    new Mitarbeiter(null, "Elena Keller", "HR Managerin"),
                    new Mitarbeiter(null, "Thomas Baumann", "Finanzleiter"),
                    new Mitarbeiter(null, "Daniel Meier", "Projektleiter"),
                    new Mitarbeiter(null, "Nina Hofmann", "Marketing Expertin"),
                    new Mitarbeiter(null, "David Gerber", "IT Architekt"),
                    new Mitarbeiter(null, "Laura Steiner", "Product Owner"),
                    new Mitarbeiter(null, "Sandro Weber", "Teamleiter Sales"),
                    new Mitarbeiter(null, "Claudia Winkler", "Event Managerin"),
                    new Mitarbeiter(null, "Lukas Furrer", "Support Spezialist"),
                    new Mitarbeiter(null, "Sophie Arnold", "Assistenz der GL")
            );
            mitarbeiterRepo.saveAll(mitarbeiter);

            // Kunden
            for (int i = 1; i <= 10; i++) {
                kundeRepo.save(new Kunde(null, "Vorname" + i, "Nachname" + i, "kunde" + i + "@mail.com", "07900000" + i));
            }

            // Reisen
            for (int i = 1; i <= 10; i++) {
                Reise r = new Reise();
                r.setZielort("Business-Stadt " + i);
                r.setBeschreibung("Strategiemeeting in Stadt " + i);
                r.setStartdatum(LocalDate.now().plusDays(i));
                r.setEnddatum(LocalDate.now().plusDays(i + 5));
                reiseRepo.save(r);
            }

            // Leistungen
            Hotel hotel = hotelRepo.findAll().get(0);
            Mitarbeiter mitarb = mitarbeiterRepo.findAll().get(0);
            List<Leistung> gespeicherteLeistungen = leistungRepo.saveAll(List.of(
                    new Leistung(null, "Konferenzraum Level A", "Business", hotel, mitarb),
                    new Leistung(null, "Penthouse Suite", "Luxus", hotel, mitarb),
                    new Leistung(null, "Transfer Flughafen-Hotel", "Service", hotel, mitarb),
                    new Leistung(null, "24h Room Service", "Service", hotel, mitarb),
                    new Leistung(null, "Executive Lounge Zugang", "Business", hotel, mitarb),
                    new Leistung(null, "Highspeed WiFi", "Technik", hotel, mitarb),
                    new Leistung(null, "Übersetzungsdienst", "Business", hotel, mitarb),
                    new Leistung(null, "Wellnessbereich Zugang", "Entspannung", hotel, mitarb),
                    new Leistung(null, "VIP Betreuung", "Premium", hotel, mitarb),
                    new Leistung(null, "Tagesbüro mit Ausstattung", "Business", hotel, mitarb)
            ));

            // Buchungen + BuchungLeistungen
            List<Kunde> kunden = kundeRepo.findAll();
            List<Reise> reisen = reiseRepo.findAll();
            List<Leistung> alleLeistungen = leistungRepo.findAll();

            for (int i = 0; i < 10; i++) {
                Buchung buchung = new Buchung(null, kunden.get(i), reisen.get(i),
                        LocalDate.now().minusDays(i * 3),
                        BigDecimal.valueOf(1000 + i * 250));
                buchung = buchungRepo.save(buchung); // zuerst speichern

                List<BuchungLeistung> buchungLeistungen = new ArrayList<>();

                Leistung l1 = leistungRepo.findById(alleLeistungen.get(i % alleLeistungen.size()).getLeistungId()).orElseThrow();
                BuchungLeistung bl1 = new BuchungLeistung(buchung, l1, 1, new BigDecimal("199.90"));
                buchungLeistungen.add(bl1);

                if (i % 2 == 0) {
                    Leistung l2 = leistungRepo.findById(alleLeistungen.get((i + 1) % alleLeistungen.size()).getLeistungId()).orElseThrow();
                    BuchungLeistung bl2 = new BuchungLeistung(buchung, l2, 1, new BigDecimal("299.90"));
                    buchungLeistungen.add(bl2);
                }

                buchungLeistungRepo.saveAll(buchungLeistungen); // Leistungen speichern
                buchung.setLeistungen(buchungLeistungen); // optional setzen, falls du sie im Objekt brauchst
            }
        };
    }
}
