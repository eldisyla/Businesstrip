package ch.clip.trips.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.clip.trips.model.Buchung;

public interface BuchungRepository extends JpaRepository<Buchung, Long> {
}