import java.util.List;

/**
 * Le istanze di questa classe sono utili a rappresentare la politica di sconto:
 * "% ogni n prodotti acquistati"
 */
public class PrezzoScontato extends PoliticaSconto {
    private final int nGiocattoli;
    private final double percentuale;

    public PrezzoScontato(List<Giocattolo> g, List<Integer> p, double percentuale, int n) {
        super(g, p);
        this.nGiocattoli = n;
        this.percentuale = percentuale;
    }

    @Override
    public double formulaSconto(Giocattolo g, int q) {
        if (q <= 0)
            throw new IllegalArgumentException("Quantità non accettata");
        if (q >= nGiocattoli) {
            return prezzoTotale(g, q) - percentuale * prezzoTotale(g, q);
        }
        return prezzoTotale(g, q);
    }

}
