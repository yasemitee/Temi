import java.util.Iterator;

/**
 * Interfaccia utile a rappresentare una molecola,
 * le molecole possono essere formate da atomi dello stesso elemento o di elementi diversi, che caratterizzano molte sostanze
 */
public interface Molecola extends Iterable<ElementoChimico> {
    /**
     * Verifica se una molecola è semplice
     * @param formula la formula da verificare
     * @return true se è semplice, false altrimenti
     */
    public static boolean isSemplice(String formula){
        var componentiFormula = Helpers.parseFormula(formula);
        if (componentiFormula.length == 2) return true;
        return false;
    }

    /**
     * Restituisce la formula della molecola in notazione di Hill
     * @return la formula in notazione di Hill
     */
    public String formula();

    /**
     * Resituisce il peso della molecola (La somma dei pesi degli atomi che costituiscono una molecola)
     * @return il peso della molecola
     */
    public double peso();

    /**
     * Restituisce la numerosità di un atomo all'interno di una molecola
     * @param atomo Atomo di cui si vuole sapere la numerosità nella molecola
     * @return la numerosità di un atomo nella molecola
     * @throws NullPointerException se l'atomo è null
     */
    public int numerositàAtomo(ElementoChimico atomo);

    /**
     * Restituisce un iteratore che itera sugli elementi chimici che compongono la molecola. La quantità non viene restituita
     *
     * @return un iteratore che itera sugli elementi chimici che compongono la molecola. La quantità non viene restituita
     * @throws UnsupportedOperationException se viene invocato il metodo remove() dell'iteratore
     */
    public Iterator<ElementoChimico> iterator() throws UnsupportedOperationException;

}
