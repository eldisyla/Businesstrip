package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Leistung;
import ch.clip.trips.repo.LeistungRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leistungen")
@CrossOrigin(origins = "*")
public class LeistungController {

    @Autowired
    private LeistungRepo repo;

    @GetMapping
    public List<Leistung> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Leistung getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Leistung", id));
    }

    @PostMapping
    public Leistung create(@RequestBody Leistung entity) {
        return repo.save(entity);
    }

    @PutMapping("/{id}")
    public Leistung update(@RequestBody Leistung updated, @PathVariable Long id) {
        return repo.findById(id).map(e -> {
            e.setBeschreibung(updated.getBeschreibung());
            return repo.save(e);
        }).orElseThrow(() -> new EntityNotFoundException("Leistung", id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Leistung", id);
        repo.deleteById(id);
    }
}
