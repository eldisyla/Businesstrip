package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Kunde;
import ch.clip.trips.repo.KundeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kunden")
@CrossOrigin
public class KundeController {

    @Autowired
    private KundeRepository kundeRepository;

    @GetMapping
    public List<Kunde> getAll() {
        return kundeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Kunde getById(@PathVariable Long id) {
        return kundeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kunde", id));
    }

    @PostMapping
    public Kunde create(@RequestBody Kunde kunde) {
        return kundeRepository.save(kunde);
    }

    @PutMapping("/{id}")
    public Kunde update(@PathVariable Long id, @RequestBody Kunde updated) {
        return kundeRepository.findById(id).map(k -> {
            k.setVorname(updated.getVorname());
            k.setNachname(updated.getNachname());
            k.setEmail(updated.getEmail());
            k.setTelefon(updated.getTelefon());
            return kundeRepository.save(k);
        }).orElseThrow(() -> new EntityNotFoundException("Kunde", id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        kundeRepository.deleteById(id);
    }
}
