import java.util.*;

/**
 * Classe concreta e mutabile utile a rappresentare un inventario, ovvero una collerzione di oggetti con la loro rispettiva numerosità nell'inventario
 */
public class Inventario implements Iterable<Giocattolo>{
    /**
     * RI: La numerosità di un giocattolo nell'inventario deve essere > 0
     */
    private final Map<Giocattolo, Integer> giocattoli;

    /**
     * Crea un inventario inizialmente vuoto
     */
    public Inventario(){
        giocattoli = new HashMap<>();
    }

    /**
     * Aggiunge un numero di giocattoli uguali all'inventario
     * @param numeroGiocattoli il numero di giocattoli
     * @param giocattolo il giocattolo
     * @throws NullPointerException se il giocattolo è {@code null}
     * @throws IllegalArgumentException se il numero di giocattoli è <= 0
     */
    public void aggiungi(int numeroGiocattoli, final Giocattolo giocattolo){
        Objects.requireNonNull(giocattolo,"il giocattolo non può essere null");
        if (numeroGiocattoli <= 0) throw new IllegalArgumentException("Il numero di giocattoli non può essere <= 0");
        if (giocattoli.containsKey(giocattolo)){
            giocattoli.put(giocattolo,giocattoli.get(giocattolo) + numeroGiocattoli);
        }else {
            giocattoli.putIfAbsent(giocattolo,numeroGiocattoli);
        }
    }

    /**
     * Rimuove un numero di giocattoli uguali dall'inventario
     * @param numeroGiocattoli il numero di giocattoli da rimuovere
     * @param giocattolo il giocattolo da rimuovere
     * @throws IllegalArgumentException se il numero di giocattoli da rimuovere è < 0 o
     *                                  se il numero di giocattoli da rimuovere è maggiore del numero di giocattoli presenti nell'inventario
     * @throws NullPointerException se il giocattolo è {@code null}
     */
    public void rimuovi(int numeroGiocattoli, final Giocattolo giocattolo){
        Objects.requireNonNull(giocattolo,"Il giocattolo non può essere null");
        if (numeroGiocattoli < 0) throw new IllegalArgumentException("Il numero di giocattoli non può essere < 0");
        if (giocattoli.containsKey(giocattolo)) {
            if (giocattoli.get(giocattolo) < numeroGiocattoli) throw new IllegalArgumentException("Il numero di giocattoli presenti è < del numero di giocattoli da rimuovere");
            giocattoli.put(giocattolo, giocattoli.get(giocattolo) - numeroGiocattoli);
        }
        if (giocattoli.get(giocattolo) == 0) {
            giocattoli.remove(giocattolo);
        }
    }

    /**
     * Restituisce la quantità di un giocattolo prenente nell'inventario
     * @param giocattolo il giocattolo
     * @return il numero di giocattoli presenti
     * @throws NullPointerException se il giocattolo è {@code null}
     */
    public int quantità(final Giocattolo giocattolo){
        Objects.requireNonNull(giocattolo,"il giocattolo non può essere null");
        if (!(giocattoli.containsKey(giocattolo))) return 0;
        return giocattoli.get(giocattolo);
    }


    @Override
    public Iterator<Giocattolo> iterator() {
        final ArrayList<Giocattolo> g = new ArrayList<>(giocattoli.keySet());
        Collections.sort(g, new Comparator<Giocattolo>() {
            @Override
            public int compare(Giocattolo o1, Giocattolo o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        return Collections.unmodifiableCollection(g).iterator();
    }

    /**
     *Funzione d'astrazione
     */
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        for (final Giocattolo g : this) result.append(giocattoli.get(g) + " " + g + "\n");
        return result.toString();
    }

}
