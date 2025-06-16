package ch.clip.trips.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Kunde implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kundeId;

    private String vorname;
    private String nachname;
    private String email;
    private String telefon;

    @OneToMany(mappedBy = "kunde")
    @JsonBackReference
    private List<Buchung> buchungen;

    @ManyToMany
    @JoinTable(
            name = "Mitreisende",
            joinColumns = @JoinColumn(name = "kunde_id"),
            inverseJoinColumns = @JoinColumn(name = "mitreisender_id")
    )
    @JsonManagedReference
    private List<Kunde> mitreisende;

    @ManyToMany(mappedBy = "mitreisende")
    @JsonBackReference
    private List<Kunde> hauptkunden;

    public Kunde() {}

    public Kunde(Long kundeId, String vorname, String nachname, String email, String telefon) {
        this.kundeId = kundeId;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Kunde [kundeId=" + kundeId + ", name=" + vorname + " " + nachname + "]";
    }
}
