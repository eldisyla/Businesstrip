package ch.clip.trips.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.clip.trips.model.Reise;

public interface ReiseRepository extends JpaRepository<Reise, Long> {
}