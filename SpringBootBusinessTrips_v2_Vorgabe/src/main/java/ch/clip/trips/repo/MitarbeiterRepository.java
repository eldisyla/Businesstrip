package ch.clip.trips.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.clip.trips.model.Mitarbeiter;

public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Long> {
}