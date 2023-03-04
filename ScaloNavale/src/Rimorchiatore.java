/**
 * Questa interfaccia offre le funzionalità di base che un rimorchiatore deve avere
 */
public interface Rimorchiatore {
    /**
     * Effettua uno spostamento navale da un molo a un altro
     * @param a molo di partenza
     * @param b molo di arrivo
     */
    public void sposta(Molo a, Molo b);
}
