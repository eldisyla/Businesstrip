package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Buchung;
import ch.clip.trips.repo.BuchungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buchungen")
@CrossOrigin
public class BuchungController {

    @Autowired
    private BuchungRepository buchungRepository;

    @GetMapping
    public List<Buchung> getAll() {
        return buchungRepository.findAll();
    }

    @GetMapping("/{id}")
    public Buchung getById(@PathVariable Long id) {
        return buchungRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Buchung", id));
    }

    @PostMapping
    public Buchung create(@RequestBody Buchung buchung) {
        return buchungRepository.save(buchung);
    }

    @PutMapping("/{id}")
    public Buchung update(@PathVariable Long id, @RequestBody Buchung newBuchung) {
        return buchungRepository.findById(id).map(b -> {
            b.setBuchungsdatum(newBuchung.getBuchungsdatum());
            b.setGesamtpreis(newBuchung.getGesamtpreis());
            b.setReise(newBuchung.getReise());
            b.setKunde(newBuchung.getKunde());
            return buchungRepository.save(b);
        }).orElseThrow(() -> new EntityNotFoundException("Buchung", id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        buchungRepository.deleteById(id);
    }
}
