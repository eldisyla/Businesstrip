package ch.clip.trips.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Hotel implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    private String name;
    private String ort;
    private int sterne;
    private String kontaktEmail;
    private BigDecimal preis;

    @OneToMany(mappedBy = "hotel")
    @JsonBackReference(value = "hotel-leistungen")
    private List<Leistung> leistungen;

    @OneToMany(mappedBy = "hotel")
    @JsonBackReference(value = "hotel-reisen")  // Verhindert Endlosschleife bei Reisen -> Hotel -> Reisen
    private List<Reise> reisen;

    public Hotel() {}

    public Hotel(Long hotelId, String name, String ort, int sterne, String kontaktEmail, BigDecimal preis) {
        this.hotelId = hotelId;
        this.name = name;
        this.ort = ort;
        this.sterne = sterne;
        this.kontaktEmail = kontaktEmail;
        this.preis = preis;
    }
}
