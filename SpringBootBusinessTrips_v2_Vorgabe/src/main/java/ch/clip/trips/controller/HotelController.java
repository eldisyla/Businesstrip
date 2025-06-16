package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Hotel;
import ch.clip.trips.repo.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel", id));
    }

    @PostMapping
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @PutMapping("/{id}")
    public Hotel update(@PathVariable Long id, @RequestBody Hotel updated) {
        return hotelRepository.findById(id).map(h -> {
            h.setName(updated.getName());
            h.setOrt(updated.getOrt());
            h.setSterne(updated.getSterne());
            h.setKontaktEmail(updated.getKontaktEmail());
            return hotelRepository.save(h);
        }).orElseThrow(() -> new EntityNotFoundException("Hotel", id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        hotelRepository.deleteById(id);
    }
}
