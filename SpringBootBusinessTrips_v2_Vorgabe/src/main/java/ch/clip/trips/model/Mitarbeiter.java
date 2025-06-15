package ch.clip.trips.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Mitarbeiter implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mitarbeiterId;

    private String name;
    private String rolle;

    @OneToMany(mappedBy = "mitarbeiter")
    private List<Leistung> leistungen;

    public Mitarbeiter() {}

    public Mitarbeiter(Integer mitarbeiterId, String name, String rolle) {
        this.mitarbeiterId = mitarbeiterId;
        this.name = name;
        this.rolle = rolle;
    }

    @Override
    public String toString() {
        return "Mitarbeiter [id=" + mitarbeiterId + ", name=" + name + "]";
    }
}
