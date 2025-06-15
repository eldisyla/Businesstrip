package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Hotel;
import ch.clip.trips.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "*")
public class HotelController {

    @Autowired
    private HotelRepo repo;

    @GetMapping
    public List<Hotel> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel", id));
    }

    @PostMapping
    public Hotel create(@RequestBody Hotel entity) {
        return repo.save(entity);
    }

    @PutMapping("/{id}")
    public Hotel update(@RequestBody Hotel updated, @PathVariable Long id) {
        return repo.findById(id).map(e -> {
            e.setName(updated.getName());
            return repo.save(e);
        }).orElseThrow(() -> new EntityNotFoundException("Hotel", id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Hotel", id);
        repo.deleteById(id);
    }
}
