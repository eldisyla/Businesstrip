package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Leistung;
import ch.clip.trips.repo.LeistungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leistungen")
@CrossOrigin
public class LeistungController {

    @Autowired
    private LeistungRepository leistungRepository;

    @GetMapping
    public List<Leistung> getAll() {
        return leistungRepository.findAll();
    }

    @GetMapping("/{id}")
    public Leistung getById(@PathVariable Long id) {
        return leistungRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leistung", id));
    }

    @PostMapping
    public Leistung create(@RequestBody Leistung leistung) {
        return leistungRepository.save(leistung);
    }

    @PutMapping("/{id}")
    public Leistung update(@RequestBody Leistung neueLeistung, @PathVariable Long id) {
        return leistungRepository.findById(id).map(l -> {
            l.setBezeichnung(neueLeistung.getBezeichnung());
            l.setTyp(neueLeistung.getTyp());
            l.setHotel(neueLeistung.getHotel());
            l.setMitarbeiter(neueLeistung.getMitarbeiter());
            return leistungRepository.save(l);
        }).orElseThrow(() -> new EntityNotFoundException("Leistung", id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        leistungRepository.deleteById(id);
    }
}
