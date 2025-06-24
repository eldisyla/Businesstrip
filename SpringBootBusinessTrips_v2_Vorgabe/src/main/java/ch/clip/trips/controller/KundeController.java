package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Buchung;
import ch.clip.trips.model.Kunde;
import ch.clip.trips.repo.BuchungRepository;
import ch.clip.trips.repo.KundeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/kunden")
@CrossOrigin(origins = "http://localhost:5174")
public class KundeController {

    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private BuchungRepository buchungRepository;

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
        Kunde kunde = kundeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kunde", id));

        List<Buchung> buchungen = buchungRepository.findByKunde(kunde);
        if (!buchungen.isEmpty()) {
            throw new RuntimeException("Kunde kann nicht gelöscht werden, da er mit Buchungen verknüpft ist.");
        }

        kundeRepository.deleteById(id);
    }

    // Mitreisende Funktionen

    @PostMapping("/{hauptkundeId}/mitreisende")
    public void addMitreisender(@PathVariable Long hauptkundeId, @RequestBody MitreisenderRequest request) {
        Kunde hauptkunde = kundeRepository.findById(hauptkundeId)
                .orElseThrow(() -> new EntityNotFoundException("Kunde", hauptkundeId));
        Kunde mitreisender = kundeRepository.findById(request.getMitreisenderId())
                .orElseThrow(() -> new EntityNotFoundException("Kunde", request.getMitreisenderId()));

        if (hauptkunde.getMitreisende() == null) {
            hauptkunde.setMitreisende(new ArrayList<>());
        }

        if (!hauptkunde.getMitreisende().contains(mitreisender)) {
            hauptkunde.getMitreisende().add(mitreisender);
            kundeRepository.save(hauptkunde);
        }
    }

    @DeleteMapping("/{hauptkundeId}/mitreisende/{mitreisenderId}")
    public void removeMitreisender(@PathVariable Long hauptkundeId, @PathVariable Long mitreisenderId) {
        Kunde hauptkunde = kundeRepository.findById(hauptkundeId)
                .orElseThrow(() -> new EntityNotFoundException("Kunde", hauptkundeId));
        Kunde mitreisender = kundeRepository.findById(mitreisenderId)
                .orElseThrow(() -> new EntityNotFoundException("Kunde", mitreisenderId));

        if (hauptkunde.getMitreisende() != null) {
            hauptkunde.getMitreisende().remove(mitreisender);
            kundeRepository.save(hauptkunde);
        }
    }

    public static class MitreisenderRequest {
        private Long mitreisenderId;

        public Long getMitreisenderId() {
            return mitreisenderId;
        }

        public void setMitreisenderId(Long mitreisenderId) {
            this.mitreisenderId = mitreisenderId;
        }
    }
}
