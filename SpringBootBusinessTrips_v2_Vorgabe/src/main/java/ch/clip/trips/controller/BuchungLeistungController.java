package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.BuchungLeistung;
import ch.clip.trips.model.BuchungLeistungId;
import ch.clip.trips.repo.BuchungLeistungRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buchungsleistungen")
@CrossOrigin
public class BuchungLeistungController {

    @Autowired
    private BuchungLeistungRepo buchungLeistungRepo;

    @GetMapping
    public List<BuchungLeistung> getAll() {
        return buchungLeistungRepo.findAll();
    }

    @GetMapping("/{buchungId}/{leistungId}")
    public BuchungLeistung getById(@PathVariable Integer buchungId, @PathVariable Integer leistungId) {
        BuchungLeistungId id = new BuchungLeistungId(buchungId, leistungId);
        return buchungLeistungRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BuchungLeistung", id));
    }

    @PostMapping
    public BuchungLeistung create(@RequestBody BuchungLeistung buchungLeistung) {
        return buchungLeistungRepo.save(buchungLeistung);
    }

    @DeleteMapping("/{buchungId}/{leistungId}")
    public void delete(@PathVariable Integer buchungId, @PathVariable Integer leistungId) {
        BuchungLeistungId id = new BuchungLeistungId(buchungId, leistungId);
        buchungLeistungRepo.deleteById(id);
    }
}
