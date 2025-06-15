package ch.clip.trips.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Hotel implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotelId;

    private String name;
    private String ort;
    private Integer sterne;
    private String kontaktEmail;

    @OneToMany(mappedBy = "hotel")
    private List<Leistung> leistungen;

    public Hotel() {}

    public Hotel(Integer hotelId, String name, String ort, Integer sterne, String kontaktEmail) {
        this.hotelId = hotelId;
        this.name = name;
        this.ort = ort;
        this.sterne = sterne;
        this.kontaktEmail = kontaktEmail;
    }

    @Override
    public String toString() {
        return "Hotel [hotelId=" + hotelId + ", name=" + name + "]";
    }
}
