/**
 * Interfaccia utile a definire un multiset, ovvero un insieme di elementi (in ordine sparso) con una molteplicità (che dipende dalle varie implementazioni)
 */
interface MultiSet<E> extends Iterable<E> {
    /**
     * Aggiunge un elemento al multiset, restituendo la molteplicità di quel elemento dopo l'inserimento
     * @param e elemento da aggiungere
     * @return la molteplicità dell'elemento dopo l'inserimento
     * @throws NullPointerException se e è null
     * @throws ClassCastException se l'elemento è di tipo tale da non poter essere aggiunto.
     */
    int add(E e) throws NullPointerException, ClassCastException;

    /**
     * rimuove un elemento da questo multiset, restituendo la molteplicità di tale elemento prima della rimozione
     * (ignorando le richieste di rimuovere elementi non presenti nel multiset);
     * @param o elemento da eliminare
     * @return la molteplicità di dell'elemento prima della rimozione
     */
    int remove(Object o);

    /**
     * restituisce true se e solo se l’elemento specificato appartiene a questo multiset
     * @param o elemento da controllare
     * @return true se l'elemento è presente nel multiset, false altrimenti
     */
    default boolean contains(Object o) {
        return multiplicity(o) > 0;
    }

    /**
     * restituisce la molteplicità dell’elemento in questo multiset
     * (restituendo 0 se l’elemento non appartiene al multiset)
     * @param o elemento da controllare
     * @return la molteplicità dell'elemento nel multiset
     */
    int multiplicity(Object o);

    /**
     * Restituisce la cardinalità del multiset
     * @return la cardinalità
     */
    default int size() {
        int size = 0;
        for (E e : this) size += multiplicity(e);
        return size;
    }

    /**
     * restituisce il multiset ottenuto come unione di questo multiset con quello indicato come argomento
     * (senza modificare questo multiset, o quello passato come argomento)
     * @param o multiset con cui fare l'unione
     * @return un nuovo multiset unione
     * @throws NullPointerException se o è null
     */
    MultiSet<E> union(MultiSet<? extends E> o);

    /**
     * restituisce il multiset ottenuto come intersezione di questo multiset con quello indicato come argomenti
     * (senza modificare questo multiset, o quello passato come argomento)
     * @param o multiset su cui fare l'intersezione
     * @return un nuovo multiset intersezione
     * @throws NullPointerException se o è null
     */
    MultiSet<E> intersection(MultiSet<? extends E> o);
}
