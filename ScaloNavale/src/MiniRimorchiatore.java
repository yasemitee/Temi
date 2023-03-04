import java.util.Objects;

/**
 * Le istanze di questa classe sono immutabili e utili a usare un mini
 * rimorchiatore
 * (ovvero un rimorchiatore in grado di spostare solo una nave alla volta)
 * 
 */

// potevo farlo in un default dell'interfaccia??
public class MiniRimorchiatore implements Rimorchiatore {
    @Override
    public void sposta(Molo a, Molo b) {
        Objects.requireNonNull(a, "Il molo non può essere null");
        Objects.requireNonNull(b, "Il molo non può essere null");
        b.attracca(a.salpa());
    }
}
