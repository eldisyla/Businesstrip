package ch.clip.trips.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.clip.trips.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}