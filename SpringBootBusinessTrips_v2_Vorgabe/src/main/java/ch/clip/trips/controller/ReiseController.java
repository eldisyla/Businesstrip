package ch.clip.trips.controller;

import ch.clip.trips.ex.EntityNotFoundException;
import ch.clip.trips.model.Kunde;
import ch.clip.trips.model.Reise;
import ch.clip.trips.model.ReiseDto;
import ch.clip.trips.repo.HotelRepository;
import ch.clip.trips.repo.KundeRepository;
import ch.clip.trips.repo.LeistungRepository;
import ch.clip.trips.repo.ReiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/reisen")
@CrossOrigin(origins = "http://localhost:5174")
public class ReiseController {

    @Autowired
    private ReiseRepository reiseRepository;

    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private LeistungRepository leistungRepository;

    @GetMapping
    public List<Reise> getAll() {
        return reiseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reise getById(@PathVariable Long id) {
        return reiseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reise", id));
    }

    @PostMapping
    public Reise create(@RequestBody ReiseDto reiseDto) {
        Reise reise = new Reise();
        reise.setZielort(reiseDto.getZielort());
        reise.setBeschreibung(reiseDto.getBeschreibung());
        reise.setStartdatum(reiseDto.getStartdatum());
        reise.setEnddatum(reiseDto.getEnddatum());

        // Hauptkunde
        if (reiseDto.getHauptkundeId() != null) {
            Kunde hauptkunde = kundeRepository.findById(reiseDto.getHauptkundeId())
                    .orElseThrow(() -> new EntityNotFoundException("Kunde", reiseDto.getHauptkundeId()));
            reise.setHauptkunde(hauptkunde);
        }

        // Mitreisende
        if (reiseDto.getMitreisendeIds() != null && !reiseDto.getMitreisendeIds().isEmpty()) {
            List<Kunde> mitreisende = kundeRepository.findAllById(reiseDto.getMitreisendeIds());
            reise.setMitreisende(mitreisende);
        }

        // Hotel
        if (reiseDto.getHotelId() != null) {
            reise.setHotel(hotelRepository.findById(reiseDto.getHotelId())
                    .orElseThrow(() -> new EntityNotFoundException("Hotel", reiseDto.getHotelId())));
        }

        // Leistungen
        if (reiseDto.getLeistungenIds() != null && !reiseDto.getLeistungenIds().isEmpty()) {
            reise.setLeistungen(leistungRepository.findAllById(reiseDto.getLeistungenIds()));
        }

        reise.setGesamtpreis(reise.calculateGesamtpreis());
        return reiseRepository.save(reise);
    }

    @PutMapping("/{id}")
    public Reise update(@PathVariable Long id, @RequestBody Reise updated) {
        return reiseRepository.findById(id).map(r -> {
            r.setZielort(updated.getZielort());
            r.setBeschreibung(updated.getBeschreibung());
            r.setStartdatum(updated.getStartdatum());
            r.setEnddatum(updated.getEnddatum());
            r.setLeistungen(updated.getLeistungen());
            r.setHotel(updated.getHotel());
            r.setMitreisende(updated.getMitreisende());
            r.setHauptkunde(updated.getHauptkunde());
            r.setGesamtpreis(r.calculateGesamtpreis());
            return reiseRepository.save(r);
        }).orElseThrow(() -> new EntityNotFoundException("Reise", id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!reiseRepository.existsById(id)) {
            throw new EntityNotFoundException("Reise", id);
        }
        reiseRepository.deleteById(id);
    }

    @GetMapping("/mit-mitreisenden")
    public List<ReiseMitMitreisendenDTO> getReisenMitMitreisenden() {
        List<Reise> reisen = reiseRepository.findAll();
        List<ReiseMitMitreisendenDTO> result = new ArrayList<>();

        for (Reise reise : reisen) {
            List<Kunde> mitreisende = reise.getMitreisende();
            result.add(new ReiseMitMitreisendenDTO(reise, mitreisende != null ? mitreisende : Collections.emptyList()));
        }
        return result;
    }

    public static class ReiseMitMitreisendenDTO {
        public Reise reise;
        public List<Kunde> mitreisende;

        public ReiseMitMitreisendenDTO(Reise reise, List<Kunde> mitreisende) {
            this.reise = reise;
            this.mitreisende = mitreisende;
        }
    }
}
