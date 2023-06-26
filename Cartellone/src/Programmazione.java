import java.util.Iterator;

/**
 * Interfaccia che definisce i comportamenti di una programmazione, intesa come 
 * una sequenza (non vuota, ma eventualmente di un solo elemento e in ordine strettamente crescente) 
 * di interi compresi tra 1 e 31 che rappresentano i giorni del mese in cui si svolge la proiezione.
 * 
 * L'interfaccia estende {@link Iterable}, permettendo quindi di iterare sui giorni del mese in cui occorre la programmazione
 */
public interface Programmazione extends Iterable<Integer>{

    /**
     * Restituisce il primo giorno del mese in cui occorre la programmazione
     * 
     * 
     * @return il primo giorno
     */
    public int primoGiorno();

    /**
     * Restituisce {@code true} se nella programmazione è presente il giorno specificato, {@code false} altrimenti
     * @param gioro il giorno che si vuole controllare
     * @return {@code true} se il giorno è presente, {@code false} altrimenti
     */
    public boolean contiene(int giorno);

    /**
     * Un "(Liskov) iterator" che è un metodo che restituisce un "(Liskov) generator",
     * che permette di avere la sequenza di date della programmazione
     * @return
     */
    @Override
    public Iterator<Integer> iterator();
    

    
    
}
