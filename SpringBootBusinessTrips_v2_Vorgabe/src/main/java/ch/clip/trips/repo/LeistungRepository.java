package ch.clip.trips.repo;

import ch.clip.trips.model.Leistung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeistungRepository extends JpaRepository<Leistung, Long> {
}
