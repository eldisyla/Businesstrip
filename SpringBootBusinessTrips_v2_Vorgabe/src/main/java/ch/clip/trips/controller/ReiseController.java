package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Reise;
import ch.clip.trips.repo.ReiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reisen")
@CrossOrigin
public class ReiseController {

    @Autowired
    private ReiseRepository reiseRepository;

    @GetMapping
    public List<Reise> getAll() {
        return reiseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reise getById(@PathVariable Long id) {
        return reiseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reise", id));
    }

    @PostMapping
    public Reise create(@RequestBody Reise reise) {
        return reiseRepository.save(reise);
    }

    @PutMapping("/{id}")
    public Reise update(@PathVariable Long id, @RequestBody Reise updated) {
        return reiseRepository.findById(id).map(r -> {
            r.setZielort(updated.getZielort());
            r.setStartdatum(updated.getStartdatum());
            r.setEnddatum(updated.getEnddatum());
            return reiseRepository.save(r);
        }).orElseThrow(() -> new EntityNotFoundException("Reise", id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reiseRepository.deleteById(id);
    }
}
