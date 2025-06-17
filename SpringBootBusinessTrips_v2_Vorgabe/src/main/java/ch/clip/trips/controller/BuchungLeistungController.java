package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Buchung;
import ch.clip.trips.model.BuchungLeistung;
import ch.clip.trips.model.BuchungLeistungId;
import ch.clip.trips.model.Leistung;
import ch.clip.trips.repo.BuchungLeistungRepository;
import ch.clip.trips.repo.BuchungRepository;
import ch.clip.trips.repo.LeistungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buchung-leistungen")
@CrossOrigin
public class BuchungLeistungController {

    @Autowired
    private BuchungLeistungRepository buchungLeistungRepository;

    @Autowired
    private BuchungRepository buchungRepository;

    @Autowired
    private LeistungRepository leistungRepository;

    @GetMapping
    public List<BuchungLeistung> getAll() {
        return buchungLeistungRepository.findAll();
    }

    @PostMapping("/{buchungId}/{leistungId}")
    public BuchungLeistung addLeistungToBuchung(@PathVariable Long buchungId, @PathVariable Long leistungId) {
        Buchung buchung = buchungRepository.findById(buchungId)
                .orElseThrow(() -> new EntityNotFoundException("Buchung", buchungId));
        Leistung leistung = leistungRepository.findById(leistungId)
                .orElseThrow(() -> new EntityNotFoundException("Leistung", leistungId));

        BuchungLeistungId id = new BuchungLeistungId(buchungId, leistungId);
        BuchungLeistung bl = new BuchungLeistung();
        bl.setId(id);
        bl.setBuchung(buchung);
        bl.setLeistung(leistung);

        return buchungLeistungRepository.save(bl);
    }

    @DeleteMapping("/{buchungId}/{leistungId}")
    public void delete(@PathVariable Long buchungId, @PathVariable Long leistungId) {
        BuchungLeistungId id = new BuchungLeistungId(buchungId, leistungId);
        buchungLeistungRepository.deleteById(id);
    }
}
