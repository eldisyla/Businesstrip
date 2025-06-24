package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Leistung;
import ch.clip.trips.repo.LeistungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leistungen")
@CrossOrigin(origins = "http://localhost:5174")
public class LeistungController {

    @Autowired
    private LeistungRepository leistungRepository;

    // Alle Leistungen abrufen
    @GetMapping
    public List<Leistung> getAll() {
        return leistungRepository.findAll();
    }

    // Eine Leistung anhand der ID abrufen
    @GetMapping("/{id}")
    public Leistung getById(@PathVariable Long id) {
        return leistungRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leistung", id));
    }

    // Neue Leistung erstellen
    @PostMapping
    public Leistung create(@RequestBody Leistung leistung) {
        return leistungRepository.save(leistung);
    }

    // Bestehende Leistung aktualisieren
    @PutMapping("/{id}")
    public Leistung update(@PathVariable Long id, @RequestBody Leistung neueLeistung) {
        return leistungRepository.findById(id).map(l -> {
            l.setName(neueLeistung.getName());
            l.setBeschreibung(neueLeistung.getBeschreibung());
            l.setPreis(neueLeistung.getPreis());
            l.setHotel(neueLeistung.getHotel());
            return leistungRepository.save(l);
        }).orElseThrow(() -> new EntityNotFoundException("Leistung", id));
    }

    // Leistung löschen
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!leistungRepository.existsById(id)) {
            throw new EntityNotFoundException("Leistung", id);
        }
        leistungRepository.deleteById(id);
    }
}
