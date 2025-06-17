package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Mitarbeiter;
import ch.clip.trips.repo.MitarbeiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mitarbeiter")
@CrossOrigin
public class MitarbeiterController {

    @Autowired
    private MitarbeiterRepository mitarbeiterRepository;

    @GetMapping
    public List<Mitarbeiter> getAll() {
        return mitarbeiterRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mitarbeiter getById(@PathVariable Long id) {
        return mitarbeiterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mitarbeiter", id));
    }

    @PostMapping
    public Mitarbeiter create(@RequestBody Mitarbeiter mitarbeiter) {
        return mitarbeiterRepository.save(mitarbeiter);
    }

    @PutMapping("/{id}")
    public Mitarbeiter update(@PathVariable Long id, @RequestBody Mitarbeiter updated) {
        return mitarbeiterRepository.findById(id).map(m -> {
            m.setName(updated.getName());
            m.setRolle(updated.getRolle());
            return mitarbeiterRepository.save(m);
        }).orElseThrow(() -> new EntityNotFoundException("Mitarbeiter", id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mitarbeiterRepository.deleteById(id);
    }
}
