package ch.clip.trips.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Kunde implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kundeId;

    private String vorname;
    private String nachname;
    private String email;
    private String telefon;

    //Kunde kann andere Kunden als Mitreisende haben
    @ManyToMany
    @JoinTable(
            name = "mitreisende",
            joinColumns = @JoinColumn(name = "hauptkunde_id"),
            inverseJoinColumns = @JoinColumn(name = "mitreisender_id")
    )
    @JsonIgnoreProperties({"mitreisende"}) // Verhindert Endlosschleife bei der Ausgabe
    private List<Kunde> mitreisende = new ArrayList<>();

    public Kunde() {}

    public Kunde(Long kundeId, String vorname, String nachname, String email, String telefon) {
        this.kundeId = kundeId;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.telefon = telefon;
    }
}
