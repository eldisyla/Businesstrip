package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.*;
import ch.clip.trips.repo.BuchungRepository;
import ch.clip.trips.repo.KundeRepository;
import ch.clip.trips.repo.ReiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buchungen")
@CrossOrigin(origins = "http://localhost:5174")
public class BuchungController {

    @Autowired
    private BuchungRepository buchungRepository;

    @Autowired
    private ReiseRepository reiseRepository;

    @Autowired
    private KundeRepository kundeRepository;

    @GetMapping
    public List<BuchungDto> getAll() {
        return buchungRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BuchungDto getById(@PathVariable Long id) {
        Buchung buchung = buchungRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Buchung", id));
        return convertToDto(buchung);
    }

    @PostMapping
    public Buchung create(@RequestBody Buchung buchung) {
        Long kundeId = buchung.getKunde().getKundeId();
        Long reiseId = buchung.getReise().getReiseId();

        Kunde kunde = kundeRepository.findById(kundeId)
                .orElseThrow(() -> new EntityNotFoundException("Kunde", kundeId));
        Reise reise = reiseRepository.findById(reiseId)
                .orElseThrow(() -> new EntityNotFoundException("Reise", reiseId));

        BigDecimal gesamtpreis = reise.getGesamtpreis();

        buchung.setKunde(kunde);
        buchung.setReise(reise);
        buchung.setBuchungsdatum(LocalDate.now());
        buchung.setGesamtpreis(gesamtpreis);

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
        if (!buchungRepository.existsById(id)) {
            throw new EntityNotFoundException("Buchung", id);
        }
        buchungRepository.deleteById(id);
    }

    private BuchungDto convertToDto(Buchung buchung) {
        BuchungDto dto = new BuchungDto();
        dto.setBuchungId(buchung.getBuchungId());
        dto.setBuchungsdatum(buchung.getBuchungsdatum());
        dto.setGesamtpreis(buchung.getGesamtpreis());

        if (buchung.getKunde() != null) {
            dto.setKundeId(buchung.getKunde().getKundeId());
            dto.setKundeName(buchung.getKunde().getVorname() + " " + buchung.getKunde().getNachname());
        }

        if (buchung.getReise() != null) {
            dto.setReiseId(buchung.getReise().getReiseId());
            dto.setReiseziel(buchung.getReise().getZielort());
            dto.setReisebeschreibung(buchung.getReise().getBeschreibung());
            dto.setStartdatum(buchung.getReise().getStartdatum());
            dto.setEnddatum(buchung.getReise().getEnddatum());
        }

        return dto;
    }
}
