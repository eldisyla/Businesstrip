package ch.clip.trips.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Buchung implements Serializable {

    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer buchungId;

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

    public Buchung() {}

    public Buchung(Integer buchungId, Kunde kunde, Reise reise, LocalDate buchungsdatum, BigDecimal gesamtpreis) {
        this.buchungId = buchungId;
        this.kunde = kunde;
        this.reise = reise;
        this.buchungsdatum = buchungsdatum;
        this.gesamtpreis = gesamtpreis;
    }

    @Override
    public String toString() {
        return "Buchung [id=" + buchungId + ", kunde=" + kunde.getNachname() + "]";
    }
}
