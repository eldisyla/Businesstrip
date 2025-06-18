package ch.clip.trips.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Leistung implements Serializable {

    private static final long serialVersionUID = 6L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leistungId;

    private String bezeichnung;
    private String typ;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "mitarbeiter_id")
    @JsonBackReference
    private Mitarbeiter mitarbeiter;

    public Leistung() {
        // Standardkonstruktor für JPA
    }

    public Leistung(Long leistungId, String bezeichnung, String typ, Hotel hotel, Mitarbeiter mitarbeiter) {
        this.leistungId = leistungId;
        this.bezeichnung = bezeichnung;
        this.typ = typ;
        this.hotel = hotel;
        this.mitarbeiter = mitarbeiter;
    }

    @Override
    public String toString() {
        return "Leistung [leistungId=" + leistungId + ", bezeichnung=" + bezeichnung + "]";
    }
}
