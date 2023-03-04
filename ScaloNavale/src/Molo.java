import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

/**
 * Le istanze di questa classe sono mutabili e utili a rappresentare un molo.
 * <p>
 * Un molo è un fila di navi, dove il primo di una sequenza di cargo che
 * attraccano sarà l'ultimo a salpare.
 * </p>
 * RI: la nave che attracca deve essere != null
 * <p>
 * FA: nave_1 nave_2 nave_3 ... nave_n-1 dove n è il numero di navi attraccate
 * al molo
 */
public class Molo {
    /**
     * Le istanze di NaveCargo sono immutabili e utili a rappresentare una nave
     * cargo caratterizzata da un nome e un peso.
     * RI: peso >= 0 && nome != null
     * FA: nome[peso]
     * 
     * @param nome
     * @param peso
     */
    public record NaveCargo(String nome, int peso) {
        // Costruttore
        /**
         * Crea una nuova nave cargo.
         * 
         * @param nome nome della nave.
         * @param peso peso della nave.
         * @throws NullPointerException     se il nome della nave è null.
         * @throws IllegalArgumentException se il peso della nave è <= 0.
         */
        public NaveCargo(String nome, int peso) {
            this.nome = Objects.requireNonNull(nome, "Il nome non può essere null");
            if (peso <= 0)
                throw new IllegalArgumentException("Il peso di una nave deve essere > 0");
            this.peso = peso;
        }

        @Override
        public String toString() {
            return nome + "[" + peso + "]";
        }
    }

    // Campi
    private Stack<NaveCargo> filaNavi; // la fila di navi è comoda da rappresentare con una stack

    // Costruttore
    public Molo() {
        filaNavi = new Stack<NaveCargo>();
    }

    // Metodi
    /**
     * Aggiunge nella prima posizione libera della fila una nave.
     * 
     * @param nome nome della nave da attraccare.
     * @param peso peso della nave da attraccare.
     * @throws NullPointerException     se il nome della nave è null.
     * @throws IllegalArgumentException se il peso della nave è <= 0.
     */
    public void attracca(String nome, int peso) {
        NaveCargo nave = new NaveCargo(nome, peso);
        filaNavi.push(nave);
    }

    // Nota implementativa: mi è sembrato utile dare due metodi per far attraccare
    // una nave nuova o una nave già esistente
    /**
     * Aggiunge nella prima posizione libera della fila una nave.
     * 
     * @param nave nome della nave da attraccare.
     * @throws NullPointerException se il nome della nave è null.
     */
    public void attracca(NaveCargo nave) {
        filaNavi.push(Objects.requireNonNull(nave));
    }

    /**
     * Restituisce la nave che può salpare dal molo (ovvero l'ultima nave che è
     * attraccata).
     * 
     * @return l'ultima nave che è attraccata nel molo.
     * @throws EmptyMoloException se il molo è vuoto.
     */
    public NaveCargo salpa() {
        if (filaNavi.size() == 0)
            throw new EmptyMoloException("Il molo è vuoto");
        return filaNavi.pop();
    }

    /**
     * Restituisce il numero di navi attualmente attraccate al molo.
     * 
     * @return il numero di navi attualmente attraccate al molo.
     */
    public int size() {
        return filaNavi.size();
    }

    /**
     * Restituisce tutte le navi attraccate al molo in ordine di attracco (la prima
     * attraccata sarà l'ultima arrivata)
     * 
     * @return Le navi attraccate al molo
     */
    public NaveCargo[] getFilaNavi() {
        NaveCargo[] fila = new NaveCargo[size()];
        int i = filaNavi.size() - 1;
        Iterator<NaveCargo> itr = filaNavi.iterator();
        while (itr.hasNext()) {
            fila[i--] = itr.next();
        }
        return fila;
    }
}
