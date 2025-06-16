package ch.clip.trips.repo;

import ch.clip.trips.model.BuchungLeistung;
import ch.clip.trips.model.BuchungLeistungId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuchungLeistungRepository extends JpaRepository<BuchungLeistung, BuchungLeistungId> {
    
}
