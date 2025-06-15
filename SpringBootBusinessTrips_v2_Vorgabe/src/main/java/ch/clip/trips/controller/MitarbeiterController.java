package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Mitarbeiter;
import ch.clip.trips.repo.MitarbeiterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mitarbeiter")
@CrossOrigin(origins = "*")
public class MitarbeiterController {

    @Autowired
    private MitarbeiterRepo repo;

    @GetMapping
    public List<Mitarbeiter> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Mitarbeiter getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Mitarbeiter", id));
    }

    @PostMapping
    public Mitarbeiter create(@RequestBody Mitarbeiter entity) {
        return repo.save(entity);
    }

    @PutMapping("/{id}")
    public Mitarbeiter update(@RequestBody Mitarbeiter updated, @PathVariable Long id) {
        return repo.findById(id).map(e -> {
            e.setName(updated.getName());
            return repo.save(e);
        }).orElseThrow(() -> new EntityNotFoundException("Mitarbeiter", id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Mitarbeiter", id);
        repo.deleteById(id);
    }
}
