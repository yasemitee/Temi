import java.util.*;

/**
 * Classe concreta e immutabile, utile a rappresentare una tavola periodica, ovvero un elenco di elementi chimici ordinati per numero atomico
 */
public class TavolaPeriodica implements Iterable<ElementoChimico> {
    /**
     * Scelgo di usare un treeset per avere un insieme di elementi chimici in ordine e senza ripetizioni;
     */
    private final Set<ElementoChimico> elementiTavola = new TreeSet<>();

    /**
     * RI: elementiTavola non deve essere null, non deve contenere elementi chimici null, può contenere al massimo 118 elementi chimici, non contiene doppioni di elementi chimici
     */
    public TavolaPeriodica(List<ElementoChimico> elementiChimici) {
        if ((Objects.requireNonNull(elementiChimici, "La lista di elementi chimici non può essere null")).contains(null)) {
            throw new NullPointerException("La lista di elementi chimici non può contenere null");
        }
        if (elementiChimici.size() > 118)
            throw new IllegalArgumentException("Il numero di elementi chimici deve essere al più 118");
        elementiTavola.addAll(elementiChimici); // essendo un treeset verranno rimossi i doppioni se presenti e quindi la dimensione sarà sicuramente <= a 118 (controllato prima)
    }

    /**
     * Restituisce true se l'elemento chimico è nella tavola periodica, false altrimenti
     *
     * @param el elemento chimico
     * @return true se l'elemento chimico è nella tavola periodica, false altrimenti
     * @throws NullPointerException se el è null
     */
    public boolean èNellaTavola(ElementoChimico el) {
        return elementiTavola.contains(Objects.requireNonNull(el, "L'elemento non può essere null"));
    }

    /**
     * Resituisce l'elemento chimico dato il simbolo se presente nella tavola periodica, null altrimenti
     *
     * @param simbolo il simbolo dell'elemento
     * @return l'elemento chimico se presente, null altrimenti
     * @throws NullPointerException     se il simbolo è null
     * @throws IllegalArgumentException se il simbolo è vuoto
     */
    public ElementoChimico getElemento(String simbolo) {
        if (Objects.requireNonNull(simbolo, "Il simbolo non può essere null").isEmpty()) {
            throw new IllegalArgumentException("Il simbolo non può essere vuoto");
        }
            Iterator<ElementoChimico> it = elementiTavola.iterator();
            while (it.hasNext()) {
                ElementoChimico el = it.next();
                if (el.simbolo().equals(simbolo)) return el;
            }
        return null;
    }

        /**
         * Restituisce un iteratore sugli elementi della tavola periodica
         * @return iteratore
         * @throws UnsupportedOperationException se viene chiamato il metodo remove;
         */
        @Override
        public Iterator<ElementoChimico> iterator() throws UnsupportedOperationException {
            return Collections.unmodifiableCollection(elementiTavola).iterator();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (ElementoChimico e : elementiTavola) {
                sb.append(e.toString());
                sb.append("\n");
            }
            return sb.toString();
        }
}
