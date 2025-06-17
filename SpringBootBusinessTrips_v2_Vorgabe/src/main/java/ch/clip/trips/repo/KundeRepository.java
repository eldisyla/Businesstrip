package ch.clip.trips.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.clip.trips.model.Kunde;

public interface KundeRepository extends JpaRepository<Kunde, Long> {
}