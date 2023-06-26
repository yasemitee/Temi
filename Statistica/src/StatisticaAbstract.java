import java.util.List;
import java.util.Objects;

/**
 * Classe astratta mutabile che implementa il calcolo della media dell'interfaccia {@link Statistica}.
 * Inoltre tiene traccia della somma e del numero di osservazioni coinvolte nell'analisi delle sottoclassi, 
 * che dovranno offrire un implementazione per {@link Statistica#varianza()} 
 * 
 */
public abstract class StatisticaAbstract implements Statistica{

    /*La somma delle osservazioni coinvolte nell'analisi */
    private double somma;
    /*Il numero di osservazioni coinvolte nell'analisi */
    private int nOsservazioni;

    /*
     * RI: nOsservazioni deve essere > 0
    */

    /**
     * Crea un nuovo oggetto data una osservazione.
     * ATTENZIONE: le classi che implementano la classe astratta che intendono adoperare questo costruttore (costruttore di default)
     * devono implementare almeno una volta il metodo {@link StatisticaAbstract#aggiungiOsservazione(double)},
     * in caso contrario la classe non è valida, quindi la correttezza dei risultati non sarà garantita.
     */
    protected StatisticaAbstract() {}

    //nota questo casino l'ho fatto perchè mi dava null l'array di DuePassate usando il costruttore 
    //public StatisticaAbstract(final List<Double> osservazioni), il perchè faccia così rimarrà un mistero

    /**
     * Crea un nuovo oggetto data una osservazione.
     * @param osservazione da aggiungere
     */
    public StatisticaAbstract(double osservazione) {
            aggiungiOsservazione(osservazione);
    }

     /**
     * Crea un nuovo oggetto data una lista di osservazioni.
     * @param osservazioni
     * @throws NullPointerException se la lista di osservazioni è {@code null} o contiene elementi {@code null}.
     * @throws IllegalArgumentException se la lista non contiene almeno un osservazione.
     */
    public StatisticaAbstract(final List<Double> osservazioni) {
        if (Objects.requireNonNull(osservazioni, "le osservazioni non possono essere null").size() < 1)
            throw new IllegalArgumentException("osservazioni deve contenere almeno un osservazione");
        for (Double o : osservazioni){
            aggiungiOsservazione(Objects.requireNonNull(o, "La lista di osservazioni non può contenere elementi null"));
        }
    }

    /**
     * Aggiunge un osservazione alle osservazioni in analisi
     * @param osservazione
     */
    public void aggiungiOsservazione(double osservazione){
        somma += osservazione;
        nOsservazioni++;
    }

    @Override
    public double media() {
        return somma / nOsservazioni;
    }

    @Override
    public int nOsservazioni() {
        return nOsservazioni;
    }

    @Override
    abstract public double varianza();

    /**
     * Restituisce la somma delle osservazioni
     * @return la somma
     */
    public double getSomma() {
        return somma;
    }

    /**
     * Restituisce il numero di osservazioni
     * @return il numero di osservazioni
     */
    public int numeroOsservazioni() {
        return nOsservazioni;
    }

    /*Funzione d'astrazione*/
    @Override
    public String toString() {
        return nOsservazioni + ", " + String.format("%.8e", media()) + ", " + String.format("%.8e", varianza());
    }


}
