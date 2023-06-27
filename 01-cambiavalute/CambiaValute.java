import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/*
 * Classe concreta e immutabile utile a rappresentare un cambiavalute,
 * Un cambiavalute è un servizio dotato di una cassa che, 
 * presa conoscenza di una serie di tassi di cambio, può cambiare a richiesta un importo (in una data valuta) in una valuta differente
 */
public class CambiaValute {
    /* la cassa interna del cambio valute */
    private final Cassa cassaInterna;

    /* Lista contenente i tassi di cambio relativi di cui il cambia valute è a conoscenza */
    private List<Tasso> tassi;

    /* RI
     * - cassInterna non può essere null
     * - tassi non deve essere null e non contiene tassi null
     * - i tassi non possono essere duplicati, o il altre parole non è possibile avere due tassi che coinvolgono le stesse valute
     * 
     * FA: è una cambiavalute contenente una cassa interna = cassInterna (non modificabile) che ha memorizzati dei tassi di cambio = tassi.
     *     i tassi sono unici
     */

     /**
      * Costruisce un nuovo cambiavalute a partire da una cassa (contenete gli importi da inserire nel cambia valute)
      * @param cassaInterna la cassa interna
      * @throws NullPointerException se la cassa è {@code null} o contiene importi {@code null}
      */
     public CambiaValute(Cassa cassaInterna) {
        Objects.requireNonNull(cassaInterna, "la cassa interna non può essere null");
        this.cassaInterna = new Cassa();
        for (Importo i : cassaInterna){
            cassaInterna.versa(Objects.requireNonNull(i, "la cassa non può contenere importi null"));
        }
        this.tassi = new ArrayList<>();
    }

    /**
     * Restituisce un cassa che contiene gli importi della cassa interna
     * @return la cassa interna
     */
    public Cassa getCassaInterna() {
        Cassa c = new Cassa();
        for (Importo i : cassaInterna){
            cassaInterna.versa(i);
        }
        return c;
    }

    /**
     * restituisce i tassi memorizzati all'interno del cambiovalute
     * @return
     */
    public List<Tasso> getTassi() {
        return Collections.unmodifiableList(tassi);
    }

     
}
