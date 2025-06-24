package ch.clip.trips.repo;

import ch.clip.trips.model.Buchung;
import ch.clip.trips.model.Kunde;
import ch.clip.trips.model.Reise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuchungRepository extends JpaRepository<Buchung, Long> {

    List<Buchung> findByKunde(Kunde kunde);

    List<Buchung> findByReise(Reise reise);
}
