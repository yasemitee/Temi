import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classe concreta immutabile, utile a rappresentare una {@link Programmazione} periodica, 
 * ovvero una programmazione che partendo da un giorno iniziale si ripeterà 
 * in una serie di date ad intervalli regolari (con intervalli di lunghezza costante)
 */
public class ProgrammazionePeriodica extends AbstractProgrammazione{
    
    /* il numero di giorni che trascorre tra una programmazione ed un altra */
    private final int intervallo;

    /*
     * RI: intervallo deve essere compreso tra 1 e 31
     */

     //Costruttori

    /**
     * Costruisce un nuovo oggetto ProgrammazionePeriodica dato il primo giorno e l'intervallo tra le date
     * 
     * @param primoGiorno il primo giorno della programmazione
     * @param intervallo il numero di giorni che trascorre tra una programmazione ed un altra
     * @throws IllegalArgumentException se intervallo o primoGiorno non sono numeri compresi tra 1 e 31
     */
    ProgrammazionePeriodica(int primoGiorno, int intervallo) {
        super(primoGiorno);
        if (intervallo < 1 || intervallo > 31)
            throw new IllegalArgumentException("L'intervallo deve essere un numero compreso tra 1 e 31 - Found: "+intervallo);
        this.intervallo = intervallo;
    }

    // Metodi
    /**
     * Restituisce l'intervallo che trascorre tra una programmazione ed un altra
     * 
     * @return l'intervallo
     */
    public int intervallo() {
        return intervallo;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>(){
            
            int next = primoGiorno();

            @Override
            public boolean hasNext() {
                return next <= 31;
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                int r = next;
                next = next + intervallo;
                return r;
            }
        };
    }
}
