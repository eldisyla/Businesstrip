package ch.clip.trips.controller;

import ch.clip.trips.model.Buchung;
import ch.clip.trips.model.BuchungLeistung;
import ch.clip.trips.model.Leistung;
import ch.clip.trips.repo.BuchungLeistungRepository;
import ch.clip.trips.repo.BuchungRepository;
import ch.clip.trips.repo.LeistungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/buchungsleistungen")
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
    public BuchungLeistung create(@PathVariable Long buchungId, @PathVariable Long leistungId) {
        Buchung buchung = buchungRepository.findById(buchungId).orElseThrow();
        Leistung leistung = leistungRepository.findById(leistungId).orElseThrow();

        BuchungLeistung bl = new BuchungLeistung();
        bl.setBuchung(buchung);
        bl.setLeistung(leistung);
        bl.setMenge(1);
        bl.setEinzelpreis(new BigDecimal("199.90"));

        return buchungLeistungRepository.save(bl);
    }

    @DeleteMapping("/{buchungId}/{leistungId}")
    public void delete(@PathVariable Long buchungId, @PathVariable Long leistungId) {
        List<BuchungLeistung> alle = buchungLeistungRepository.findAll();

        for (BuchungLeistung bl : alle) {
            if (bl.getBuchung().getBuchungId().equals(buchungId) &&
                    bl.getLeistung().getLeistungId().equals(leistungId)) {
                buchungLeistungRepository.delete(bl);
                return;
            }
        }

        throw new RuntimeException("BuchungLeistung mit IDs nicht gefunden");
    }
}
