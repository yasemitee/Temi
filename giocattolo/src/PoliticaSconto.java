import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Classe astratta utile a rappresentare una politica di sconto e rappresentare
 * il prezzo per ogni giocattolo
 * RI: prezzo > 0 && giocattolo != null
 */
public abstract class PoliticaSconto implements Prezzo {
    private final HashMap<Giocattolo, Integer> prezzi = new HashMap<Giocattolo, Integer>();

    /**
     * Crea un nuovo listino di prezzi
     * 
     * @param g giocattolo
     * @param p prezzo
     */
    public PoliticaSconto(List<Giocattolo> g, List<Integer> p) {
        if (p.contains(0))
            throw new IllegalArgumentException("Non è possibile avere prezzi negativi");
        if (p.size() == 0 || g.size() == 0)
            throw new IllegalArgumentException("Oggetti o prezzi non disponibili");
        if (p.size() != g.size())
            throw new IllegalArgumentException("prezzi e oggetti non corrispondenti");
        for (int i = 0; i < g.size(); i++) {
            if (prezzi.containsKey(g.get(i)))
                throw new IllegalArgumentException("L'oggetto ha già un prezzo");
            prezzi.put(g.get(i), p.get(i));
        }
    }

    public int prezzoTotale(final Giocattolo g, final int q) {
        if (q <= 0)
            throw new IllegalArgumentException("Non è determinare il costo di " + q + " oggetti");
        Objects.requireNonNull(g, "Il Giocattolo non può essere null");
        return prezzi.get(g) * q;
    }

    /**
     * L'implementazione definisce il criterio (o formula) con il quale viene
     * applicato lo sconto
     * 
     * @param g giocattolo
     * @param q numero di giocattoli (uguali)
     * @return prezzo corrispondente al risultato della formula
     */
    public abstract double formulaSconto(Giocattolo g, int q);

}
