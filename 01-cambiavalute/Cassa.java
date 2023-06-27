
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Classe concreta e mutabile utile a rappresentare una cassa, Una cassa è un "contenitore" di importi che può contenere importi di qualunque valuta
 */
public class Cassa implements Iterable<Importo>{
    
    /* Il contenitore degli importi */
    private final Set<Importo> importi;

    /*
     * RI: 
     * - importi non deve essere null e non può contenere importi null
     */

     /**
      * Costruisce una nuova cassa (vuota)
      */
     public Cassa() {
        importi = new TreeSet<Importo>(new Comparator<Importo>() {
            public int compare(Importo i1, Importo i2) {
                return i1.valuta().compareTo(i2.valuta());
            }
        });
    }

    /**
     * Aggiunge alla cassa un importo
     * 
     * 
     * @param importo l'importo da aggiungere alla cassa
     * @throws NullPointerException se l'importo è {@code null}
     * 
     */
    public void versa(Importo importo){
        importi.add(Objects.requireNonNull(importo, "L'importo da aggiungere alla cassa non può essere null"));
    }

    /**
     * Preleva dalla cassa un importo (rimuovendolo dalla cassa)
     * (purché la cassa contenga un importo sufficiente nella valuta richiesta)
     * 
     * 
     * @param importo l'importo da aggiungere alla cassa
     * @throws NullPointerException se l'importo è {@code null}
     */
    public Importo preleva(Importo importo){
        Objects.requireNonNull(importo, "L'importo da aggiungere alla cassa non può essere null");
        for (Importo im : importi){
            if (im.valuta().equals(importo.valuta())){
                if (im.compareTo(importo) > 0){
                    Importo result = im.sottrai(importo);
                    importi.remove(im);
                    importi.add(result);
                }
            }
        }
        return importo;
    }

    @Override
    public Iterator<Importo> iterator() {
        return Collections.unmodifiableCollection(importi).iterator();
    }

    /* Funzione d'astrazione */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cassa:\n");
        Iterator<Importo> it = iterator();
        while (it.hasNext()){
            Importo next = it.next();
            
            if (!next.isZero()){
                sb.append(next);
            } 
            if (it.hasNext()) sb.append("\n");
        }
        return sb.toString();
    }

    
 
}
