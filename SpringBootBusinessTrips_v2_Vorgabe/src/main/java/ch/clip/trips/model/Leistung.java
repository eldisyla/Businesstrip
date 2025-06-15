package ch.clip.trips.model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Leistung implements Serializable {

    private static final long serialVersionUID = 6L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leistungId;

    private String bezeichnung;
    private String typ;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "mitarbeiter_id")
    private Mitarbeiter mitarbeiter;

    public Leistung() {}

    public Leistung(Integer leistungId, String bezeichnung, String typ, Hotel hotel, Mitarbeiter mitarbeiter) {
        this.leistungId = leistungId;
        this.bezeichnung = bezeichnung;
        this.typ = typ;
        this.hotel = hotel;
        this.mitarbeiter = mitarbeiter;
    }

    @Override
    public String toString() {
        return "Leistung [id=" + leistungId + ", typ=" + typ + "]";
    }
}
