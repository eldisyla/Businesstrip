package ch.clip.trips.ex;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, Long id) {
        super(entity + " mit ID " + id + " nicht gefunden.");
    }
}
