package ch.clip.trips.repo;

import ch.clip.trips.model.BuchungLeistung;
import ch.clip.trips.model.BuchungLeistungId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuchungLeistungRepo extends JpaRepository<BuchungLeistung, BuchungLeistungId> {
}
