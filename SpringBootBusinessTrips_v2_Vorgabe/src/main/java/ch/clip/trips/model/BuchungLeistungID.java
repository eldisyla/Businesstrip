package ch.clip.trips.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class BuchungLeistungId implements Serializable {

    private static final long serialVersionUID = 7L;

    private Long buchungId;
    private Long leistungId;

    public BuchungLeistungId() {}

    public BuchungLeistungId(Long buchungId, Long leistungId) {
        this.buchungId = buchungId;
        this.leistungId = leistungId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BuchungLeistungId)) return false;
        BuchungLeistungId that = (BuchungLeistungId) o;
        return Objects.equals(buchungId, that.buchungId) &&
                Objects.equals(leistungId, that.leistungId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buchungId, leistungId);
    }
}
