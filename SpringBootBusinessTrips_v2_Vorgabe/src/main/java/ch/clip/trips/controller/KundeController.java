package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Kunde;
import ch.clip.trips.repo.KundeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kunden")
@CrossOrigin(origins = "*")
public class KundeController {

    @Autowired
    private KundeRepo repo;

    @GetMapping
    public List<Kunde> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Kunde getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Kunde", id));
    }

    @PostMapping
    public Kunde create(@RequestBody Kunde entity) {
        return repo.save(entity);
    }

    @PutMapping("/{id}")
    public Kunde update(@RequestBody Kunde updated, @PathVariable Long id) {
        return repo.findById(id).map(e -> {
            e.setName(updated.getName());
            return repo.save(e);
        }).orElseThrow(() -> new EntityNotFoundException("Kunde", id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Kunde", id);
        repo.deleteById(id);
    }
}
