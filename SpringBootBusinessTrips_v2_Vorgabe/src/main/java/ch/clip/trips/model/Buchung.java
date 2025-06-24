package ch.clip.trips.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Buchung implements Serializable {

    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buchungId;

    @ManyToOne
    @JoinColumn(name = "kunde_id")
    private Kunde kunde;

    @ManyToOne
    @JoinColumn(name = "reise_id")
    private Reise reise;

    private LocalDate buchungsdatum;
    private BigDecimal gesamtpreis;

    public Buchung() {}

    public Buchung(Long buchungId, Kunde kunde, Reise reise, LocalDate buchungsdatum, BigDecimal gesamtpreis) {
        this.buchungId = buchungId;
        this.kunde = kunde;
        this.reise = reise;
        this.buchungsdatum = buchungsdatum;
        this.gesamtpreis = gesamtpreis;
    }
}
