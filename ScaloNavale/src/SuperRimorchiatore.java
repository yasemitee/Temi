import java.util.Arrays;
import java.util.Objects;

/**
 * Le istanze di questa classe sono immutabili e utili a rappresentare un Super
 * rimorchiatore.
 * (Ovvero un rimorchiatore in grado di spostare un numero fissato di navi cargo
 * alla volta)
 * 
 * RI: n>0 ?? devo mettere anche aux?????
 * FA: Devo metterla????
 *
 */
public class SuperRimorchiatore implements Rimorchiatore {
    private final int n;
    private final Molo.NaveCargo[] aux; // array ausiliario per mantenere l'ordine delle navi spostate

    /**
     * Crea un nuovo super rimorchiatore
     * 
     * @param n il numero di navi che il rimorchiatore è in grado di spostare
     * @throws IllegalArgumentException se n <= 0
     */
    public SuperRimorchiatore(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Il rimorchiatore deve poter spostare almeno una nave");
        this.n = n;
        aux = new Molo.NaveCargo[n];
    }

    @Override
    public void sposta(Molo a, Molo b) {
        Objects.requireNonNull(a, "Il molo a non può essere null");
        Objects.requireNonNull(b, "Il molo b non può essere null");
        for (int i = 0; i < n; i++) {
            aux[n - i - 1] = a.salpa();
        }
        for (int i = 0; i < n; i++) {
            b.attracca(aux[i]);
        }
        Arrays.fill(aux, null);
    }
}
