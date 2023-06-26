import java.util.Iterator;

/**
 * Classe astratta che rappresenta una {@link Programmazione} caratterizzata dal giorno del mese iniziale 
 * da cui parte la programmazione
 */
public abstract class AbstractProgrammazione implements Programmazione{

    /* Giorno iniziale da cui parte la programmazione */
    private final int primoGiorno;

    /*
     * RI: primoGiorno deve essere un intero compreso tra 1 e 31 (estremi inclusi)
     */

    // Costruttori

    /**
     * Costruisce una programmazione, definita a partire del primoGiorno passato
     * 
     * @param primoGiorno
     * @throws IllegalArgumentException se primoGiorno è > 31 o < 1
     */
    AbstractProgrammazione(int primoGiorno){
        if (primoGiorno < 1 || primoGiorno > 31)
            throw new IllegalArgumentException("Il primo giorno della programmazione deve essere un numero compreso tra 1 e 31 - Found: "+primoGiorno);
        this.primoGiorno = primoGiorno;
    }

    // Metodi

    @Override
    public abstract Iterator<Integer> iterator();

    @Override
    public int primoGiorno() {
        return primoGiorno;
    }

    @Override
    public boolean contiene(int giorno) {
        Iterator<Integer> it = iterator();
        while (it.hasNext()){
            if (it.next() == giorno) return true;
        }
        return false;
    }

    /* Funzione d'astrazione */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = iterator();
        while (it.hasNext()){
            sb.append(it.next());
            if (it.hasNext()) sb.append(", ");
        }
        return sb.toString();
    } 

}
