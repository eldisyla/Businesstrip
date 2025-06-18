package ch.clip.trips.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "buchung")
    @JsonManagedReference
    private List<BuchungLeistung> leistungen;

    public Buchung() {
        // Standardkonstruktor für JPA
    }

    public Buchung(Long buchungId, Kunde kunde, Reise reise, LocalDate buchungsdatum, BigDecimal gesamtpreis) {
        this.buchungId = buchungId;
        this.kunde = kunde;
        this.reise = reise;
        this.buchungsdatum = buchungsdatum;
        this.gesamtpreis = gesamtpreis;
    }

    @Override
    public String toString() {
        return "Buchung [buchungId=" + buchungId + ", kunde=" + kunde + ", reise=" + reise + "]";
    }
}
