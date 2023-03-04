import java.util.*;

/**
 *Classe concreta e mutabile che offre un implementazione di multiset con una mappa che associa ad ogni elemento una molteplicità
 */
public class MapMultiSet<E> implements MultiSet<E> {
    /**
     * La mappa che, per ciascun elemento del multiset, ne indica la molteplicità.
     */
    private final Map<E, Integer> multiset = new HashMap<>();
    /**
     * RI: la mappa non deve essere null, opgni elemento di tipo E non deve essere null e ogni valore deve essere > 0
     */
    @Override
    public int add(E e) throws NullPointerException, ClassCastException {
        Objects.requireNonNull(e,"l'elemento da inserire non può essere null");
        int m = multiplicity(e);
        multiset.put(e,m+1);
        return m + 1;
    }

    @Override
    public int remove(Object o) {
        int m = multiplicity(o);
        @SuppressWarnings("unchecked")
        E elemento = (E)o;
        if (m > 1)
            multiset.put(elemento,m-1);
        if (m == 1)
            multiset.remove(elemento);
        return m;
    }

    @Override
    public int multiplicity(Object o) {
        return multiset.getOrDefault(o, 0);
    }

    @Override
    public MultiSet<E> union(MultiSet<? extends E> o) {
        Objects.requireNonNull(o,"il multiset non può essere null");
        MapMultiSet<E> result = new MapMultiSet<>();
        for (Map.Entry<E, Integer> elemMult : multiset.entrySet()) {
            final E elem = elemMult.getKey();
            final int mult = Math.min(elemMult.getValue(), o.multiplicity(elem));
            if (mult > 0) result.multiset.put(elem, mult);
        }
        return result;
    }

    @Override
    public MultiSet<E> intersection(MultiSet<? extends E> o) {
        Objects.requireNonNull(o);
        MapMultiSet<E> result = new MapMultiSet<>();
        for (Map.Entry<E, Integer> elemMult : multiset.entrySet()) {
            final E elem = elemMult.getKey();
            final int mult = Math.min(elemMult.getValue(), o.multiplicity(elem));
            if (mult > 0) result.multiset.put(elem, mult);
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return Collections.unmodifiableCollection(multiset.keySet()).iterator();
    }
}
