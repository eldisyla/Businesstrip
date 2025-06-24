package ch.clip.trips.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ReiseDto {
    private String zielort;
    private String beschreibung;
    private LocalDate startdatum;
    private LocalDate enddatum;

    private Long hauptkundeId;
    private List<Long> mitreisendeIds;
    private Long hotelId;
    private List<Long> leistungenIds;

    private BigDecimal gesamtpreis;
}
