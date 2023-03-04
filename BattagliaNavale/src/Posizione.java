import java.util.Objects;

/**
 * Record (immutabile) utile a rappresentare un punto in uno spazio di gioco (di
 * battaglia navale)
 */
public record Posizione(String riga, int colonna) {
    /**
     * RI:
     * - riga deve essere una lettera non null e non vuota, compresa tra A e J
     * - colonna deve essere un numero compreso tra 0 e 9
     */
    public Posizione {
        if (Objects.requireNonNull(riga, "La riga non può essere null").isEmpty()) {
            throw new IllegalArgumentException("La riga non può essere vuota");
        }
        if (riga.compareTo("A") < 0 || riga.compareTo("J") > 0) {
            throw new IllegalArgumentException("La riga inserita non è compresa tra A e J");
        }
        if (colonna < 0 || colonna > 9) {
            throw new IllegalArgumentException("La colonna inserita non è compresa tra 0 e 9");
        }
    }
}
