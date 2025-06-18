package ch.clip.trips.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Mitarbeiter implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mitarbeiterId;

    private String name;
    private String rolle;

    @OneToMany(mappedBy = "mitarbeiter")
    private List<Leistung> leistungen;

    public Mitarbeiter() {
        // Standardkonstruktor für JPA
    }

    public Mitarbeiter(Long mitarbeiterId, String name, String rolle) {
        this.mitarbeiterId = mitarbeiterId;
        this.name = name;
        this.rolle = rolle;
    }

    @Override
    public String toString() {
        return "Mitarbeiter [mitarbeiterId=" + mitarbeiterId + ", name=" + name + "]";
    }
}
