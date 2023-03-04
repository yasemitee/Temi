import java.util.Objects;

/**
 * Classe concreta e immutabile utile a rappresentare un rotabile, composto da un modello e un peso
 */
public abstract class Rotabile {

    private final String modello;
    private final int peso;

    /**
     * RI: Il modello non deve essere null && il peso deve essere > 0
     */

    /**
     * Costruisce un nuovo rotabile dato il modello e il peso
     * @param modello il modello del rotabile
     * @param peso il peso del rotabile
     * @throws IllegalArgumentException se il peso è <= 0
     * @throws NullPointerException se il modello è {@code null}
     */
    public Rotabile(String modello, int peso) {
        this.modello = Objects.requireNonNull(modello);
        if (peso <= 0) throw new IllegalArgumentException("Il peso non può essere <= 0");
        this.peso = peso;
    }

    public abstract boolean isLocomotore();

    public String getModello() {
        return modello;
    }

    public int getPeso() {
        return peso;
    }
}
