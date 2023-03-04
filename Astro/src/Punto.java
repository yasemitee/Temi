import java.util.Objects;

/**
 * Le istanze di questa classe sono utili a rappresentare un punto
 * tridimensionale a 3 coordinate, un punto è
 * un oggetto immutabile.
 */
public record Punto(int x, int y, int z) {

    /**
     * Restituisce un nuovo punto ottenuto sommando this e il punto dato come
     * argomento
     * 
     * @param q
     *        Punto da sommare
     * @throws NullPointerException
     *         se q è null
     * @return Un nuovo punto
     */
    public Punto somma(final Punto q) {
        Objects.requireNonNull(q, "Il punto " + q + "non può essere nullo");
        return new Punto(x + q.x, y + q.y, z + q.z);
    }

    /**
     * Restituisce un nuovo punto ottenuto sottraendo a this il punto dato come
     * argomento
     * 
     * @param q
     *        Punto da sottrarre
     * @throws NullPointerException
     *         se q è null
     * @return Un nuovo punto
     */
    public Punto sottrai(final Punto q) {
        Objects.requireNonNull(q, "Il punto " + q + "non può essere nullo");
        return new Punto(x - q.x, y - q.y, z - q.z);
    }

    /**
     * Restituisce la norma, ovvero il valore ottenuto sommando ogni coordinata
     * presa come valore assoluto
     * 
     * @return un valore intero corrispondente alla norma
     */
    public int norma() {
        return (x > 0 ? x : -x) + (y > 0 ? y : -y) + (z > 0 ? z : -z);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

}
