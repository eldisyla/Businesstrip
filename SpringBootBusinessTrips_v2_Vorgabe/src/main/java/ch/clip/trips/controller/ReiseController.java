package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Reise;
import ch.clip.trips.repo.ReiseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reisen")
@CrossOrigin(origins = "*")
public class ReiseController {

    @Autowired
    private ReiseRepo repo;

    @GetMapping
    public List<Reise> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Reise getById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reise", id));
    }

    @PostMapping
    public Reise create(@RequestBody Reise entity) {
        return repo.save(entity);
    }

    @PutMapping("/{id}")
    public Reise update(@RequestBody Reise updated, @PathVariable Long id) {
        return repo.findById(id).map(e -> {
            e.setZielort(updated.getZielort());
            return repo.save(e);
        }).orElseThrow(() -> new EntityNotFoundException("Reise", id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Reise", id);
        repo.deleteById(id);
    }
}
