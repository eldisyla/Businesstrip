package ch.clip.trips.controller;

import ch.clip.trips.model.Hotel;
import ch.clip.trips.repo.HotelRepository;
import ch.clip.trips.ex.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "http://localhost:5174")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    // Alle Hotels abrufen
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    // Neues Hotel erstellen
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel create(@RequestBody Hotel hotel) {
        if (hotel.getPreis() == null || hotel.getPreis().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Der Preis des Hotels muss positiv sein.");
        }
        return hotelRepository.save(hotel);
    }

    // Hotel bearbeiten
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel update(@PathVariable Long id, @RequestBody Hotel updated) {
        return hotelRepository.findById(id).map(h -> {
            h.setName(updated.getName());
            h.setOrt(updated.getOrt());
            h.setSterne(updated.getSterne());
            h.setKontaktEmail(updated.getKontaktEmail());
            if (updated.getPreis() != null && updated.getPreis().compareTo(BigDecimal.ZERO) > 0) {
                h.setPreis(updated.getPreis());
            }
            return hotelRepository.save(h);
        }).orElseThrow(() -> new EntityNotFoundException("Hotel", id));
    }

    // Hotel löschen
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new EntityNotFoundException("Hotel", id);
        }
        hotelRepository.deleteById(id);
    }
}
