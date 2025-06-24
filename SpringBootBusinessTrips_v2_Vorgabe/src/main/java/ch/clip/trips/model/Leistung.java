package ch.clip.trips.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Leistung implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leistungId;

    private String name;
    private String beschreibung;
    private BigDecimal preis;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonManagedReference(value = "hotel-leistungen")  // Hotel in Leistung sichtbar machen
    private Hotel hotel;

    @ManyToMany(mappedBy = "leistungen")
    @JsonBackReference  // Verhindert Endlosschleife zu Reise
    private List<Reise> reisen;

    public Leistung() {}

    public Leistung(Long leistungId, String name, String beschreibung, BigDecimal preis, Hotel hotel) {
        this.leistungId = leistungId;
        this.name = name;
        this.beschreibung = beschreibung;
        this.preis = preis;
        this.hotel = hotel;
    }
}
