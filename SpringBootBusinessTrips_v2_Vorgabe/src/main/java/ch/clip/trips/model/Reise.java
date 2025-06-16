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
}
