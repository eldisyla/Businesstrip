package ch.clip.trips.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BuchungLeistung implements Serializable {

    private static final long serialVersionUID = 8L;

    @EmbeddedId
    private BuchungLeistungId id;

    @ManyToOne
    @MapsId("buchungId")
    @JoinColumn(name = "buchung_id")
    @JsonBackReference
    private Buchung buchung;

    @ManyToOne
    @MapsId("leistungId")
    @JoinColumn(name = "leistung_id")
    private Leistung leistung;

    private Integer anzahl;
    private BigDecimal einzelpreis;

    public BuchungLeistung() {}

    public BuchungLeistung(Buchung buchung, Leistung leistung, Integer anzahl, BigDecimal einzelpreis) {
        this.id = new BuchungLeistungId(
                buchung.getBuchungId(),
                leistung.getLeistungId()
        );
        this.buchung = buchung;
        this.leistung = leistung;
        this.anzahl = anzahl;
        this.einzelpreis = einzelpreis;
    }

    @Override
    public String toString() {
        return "BuchungLeistung [buchung=" + buchung.getBuchungId() +
                ", leistung=" + leistung.getLeistungId() + "]";
    }
}
