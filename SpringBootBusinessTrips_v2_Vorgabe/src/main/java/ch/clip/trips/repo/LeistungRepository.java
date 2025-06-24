package ch.clip.trips.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ch.clip.trips.model.Leistung;

public interface LeistungRepository extends JpaRepository<Leistung, Long> {

}