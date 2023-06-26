import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classe concreta immutabile, utile a rappresentare una {@link Programmazione} con repliche, 
 * ovvero una programmazione che partendo da un giorno iniziale si ripeterà per i giorni successivi in base al numero di repliche,
 * quindi si avranno tante date quante il numero di repliche
 */
public class ProgrammazioneRepliche extends AbstractProgrammazione{

    /* Il numero di repliche della programmazione */
    private final int repliche; 

    /*
     * RI: repliche deve essere un intero compreso tra 1 e 31
     */

    //Costruttori

    /**
     * Costruisce un nuovo oggetto ProgrammazioneRepliche dato il primo giorno e il numero di repliche
     * 
     * @param primoGiorno il primo giorno della programmazione
     * @param repliche il numero di repliche
     * @throws IllegalArgumentException se repliche o primoGiorno non sono numeri compresi tra 1 e 31
     */
    ProgrammazioneRepliche(int primoGiorno, int repliche) {
        super(primoGiorno);
        if (repliche < 1 || repliche > 31)
            throw new IllegalArgumentException("Il numero di repliche deve essere un numero compreso tra 1 e 31 - Found: "+repliche);
        this.repliche = repliche;
    }

    // Metodi
    /**
     * Restituisce il numero di repliche della programmazione 
     * 
     * @return il numero di repliche
     */
    public int repliche() {
        return repliche;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>(){
            
            int next = primoGiorno();

            @Override
            public boolean hasNext() {
                return next < primoGiorno() + repliche;
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                int r = next;
                next++;
                return r;
            }
        };
    }

}
