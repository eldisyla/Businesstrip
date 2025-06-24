package ch.clip.trips.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BuchungDto {
    private Long buchungId;
    private LocalDate buchungsdatum;
    private BigDecimal gesamtpreis;

    private Long kundeId;
    private String kundeName;

    private Long reiseId;
    private String reiseziel;
    private String reisebeschreibung;
    private LocalDate startdatum;
    private LocalDate enddatum;
}
