package ch.clip.trips.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;
import ch.clip.trips.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    // Methode, um Hotels nach Ort zu suchen
    List<Hotel> findByOrt(String ort);

    // Methode, um Hotels nach Preis zu finden
    List<Hotel> findByPreisGreaterThanEqual(BigDecimal preis);

    // Methode, um Hotels nach Name zu suchen
    List<Hotel> findByNameContainingIgnoreCase(String name);

    // Methode, um Hotels nach mehreren Kriterien zu finden (Ort und Sterne)
    List<Hotel> findByOrtAndSterneGreaterThanEqual(String ort, int sterne);
}
