package ch.clip.trips.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
public class BuchungLeistung implements Serializable {

    private static final long serialVersionUID = 6L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buchung_id")
    @JsonBackReference
    private Buchung buchung;

    @ManyToOne
    @JoinColumn(name = "leistung_id")
    private Leistung leistung;

    private int menge;
    private BigDecimal einzelpreis;

    public BuchungLeistung() {}

    public BuchungLeistung(Buchung buchung, Leistung leistung, int menge, BigDecimal einzelpreis) {
        this.buchung = buchung;
        this.leistung = leistung;
        this.menge = menge;
        this.einzelpreis = einzelpreis;
    }
}
