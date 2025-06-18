package ch.clip.trips.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Reise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reiseId;

    private String zielort;
    private String beschreibung;

    private LocalDate startdatum;
    private LocalDate enddatum;

    public Reise() {
    }

    public Reise(String zielort, String beschreibung, LocalDate startdatum, LocalDate enddatum) {
        this.zielort = zielort;
        this.beschreibung = beschreibung;
        this.startdatum = startdatum;
        this.enddatum = enddatum;
    }
}
