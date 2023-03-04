/**
 * Le istanze di questa classe sono mutabili e utili a rappresentare uno scalo
 * navale.
 * <p>
 * Uno scalo navale è un elenco numerato (non vuoto e finito) di moli a ciascuno
 * dei quali sono attraccate alcune navi cargo.
 *
 * RI: numero dei moli deve essere maggiore di zero
 * FA: Per ogni molo dello scalo navale:
 * molo1: < nave1, nav2, ....., navek #
 * molo2: < nave1, nav2, ....., navek #
 * ...
 * moloi: < nave1, nav2, ....., navek #
 * dove k è il numero di navi attraccate al molo i
 */
public class ScaloNavale {
    private final Molo[] scalo;

    /**
     * Crea uno scalo navale con un numero finito di moli
     *
     * @param n numero di moli
     * @throws IllegalArgumentException se il numero di moli è > zero;
     */
    public ScaloNavale(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("il numero di moli deve essere > 0");
        scalo = new Molo[n];
        for (int i = 0; i < n; i++) {
            scalo[i] = new Molo();
        }
    }

    /**
     * Attracca una nave ad un molo specifico
     * 
     * @param nave nave da attraccare
     * @param i    molo di attracco
     */
    public void attraccaNaveA(Molo.NaveCargo nave, int i) {
        scalo[i].attracca(nave);
    }

    public void spostamento(int n, int partenza, int arrivo) {
        if (n < 0)
            throw new IllegalArgumentException("Il numero di moli da spostare deve essere > 0");
        if (partenza < 0 || partenza > scalo.length)
            throw new IllegalArgumentException("il molo di partenza deve essere un molo dello scalo navale");
        if (arrivo < 0 || arrivo > scalo.length)
            throw new IllegalArgumentException("il molo di arrivo deve essere un molo dello scalo navale");
        Rimorchiatore rimorchiatore = new RimorchiatorePrudente(30);
        rimorchiatore.sposta(scalo[partenza], scalo[arrivo]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scalo.length; i++) {
            sb.append(i + ": < ");
            var navi = scalo[i].getFilaNavi();
            for (int j = 0; j < navi.length - 1; j++) {
                sb.append(navi[j] + ", ");
            }
            sb.append(navi[navi.length - 1] + " #\n");
        }
        return sb.toString();
    }
}