package ch.clip.trips.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Reise implements Serializable {

    private static final long serialVersionUID = 6L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reiseId;

    private String zielort;
    private String beschreibung;
    private LocalDate startdatum;
    private LocalDate enddatum;

    @ManyToMany
    @JoinTable(
            name = "reise_mitreisende",
            joinColumns = @JoinColumn(name = "reise_id"),
            inverseJoinColumns = @JoinColumn(name = "kunde_id")
    )
    private List<Kunde> mitreisende;

    @ManyToMany
    @JoinTable(
            name = "reise_leistungen",
            joinColumns = @JoinColumn(name = "reise_id"),
            inverseJoinColumns = @JoinColumn(name = "leistung_id")
    )
    @JsonManagedReference  // Leistungen in der Reise sichtbar
    private List<Leistung> leistungen;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonManagedReference(value = "hotel-reisen")  // Hotel sichtbar in Reise
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "hauptkunde_id")
    private Kunde hauptkunde;

    private BigDecimal gesamtpreis;

    public Reise() {}

    public Reise(Long reiseId, String zielort, String beschreibung, LocalDate startdatum, LocalDate enddatum,
                 List<Kunde> mitreisende, List<Leistung> leistungen, Hotel hotel, Kunde hauptkunde) {
        this.reiseId = reiseId;
        this.zielort = zielort;
        this.beschreibung = beschreibung;
        this.startdatum = startdatum;
        this.enddatum = enddatum;
        this.mitreisende = mitreisende;
        this.leistungen = leistungen;
        this.hotel = hotel;
        this.hauptkunde = hauptkunde;
        this.gesamtpreis = calculateGesamtpreis();
    }

    public BigDecimal calculateGesamtpreis() {
        BigDecimal preis = BigDecimal.ZERO;
        if (leistungen != null) {
            for (Leistung leistung : leistungen) {
                if (leistung.getPreis() != null) {
                    preis = preis.add(leistung.getPreis());
                }
            }
        }
        if (hotel != null && hotel.getPreis() != null) {
            preis = preis.add(hotel.getPreis());
        }
        return preis;
    }

    public BigDecimal getGesamtpreis() {
        if (gesamtpreis == null) {
            gesamtpreis = calculateGesamtpreis();
        }
        return gesamtpreis;
    }

    public void setGesamtpreis(BigDecimal gesamtpreis) {
        this.gesamtpreis = gesamtpreis;
    }
}
