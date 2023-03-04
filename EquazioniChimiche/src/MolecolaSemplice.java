import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Classe concreta e immutabile utile a rappresentare una molecola semplice,
 * ovvero una molecola formata da almeno 2 atomi dello stesso elemento.
 */
public class MolecolaSemplice implements Molecola{
    private final ElementoChimico elemento; //l'atomo della molecola semplice
    private final int numerosità; //la numerosità dell'atomo della molecola semplice

    /**
     * La numerosità deve essere > 1, l'elemento non deve essere null, deve essere presente nella tavola periodica
     */

    /**
     * Costruisce una molecola semplice dato un elemento, la sua numerosità e la tavola periodica
     * @param elemento l'elemento chimico
     * @param numerosità la numerosità dell'elemento chimico
     * @param tv la tavola periodica
     * @throws NullPointerException se l'elemento chimico o la tavola periodica sono null
     * @throws IllegalArgumentException se la numerosità è < 2
     * @throws NoSuchElementException ne l'elemento non è nella tavola periodica
     */
    public MolecolaSemplice(final ElementoChimico elemento, int numerosità, TavolaPeriodica tv) {
        Objects.requireNonNull(tv,"La tavola periodica non può essere null");
        if (!tv.èNellaTavola(Objects.requireNonNull(elemento,"L'elemento non può essere null"))){
            throw new NoSuchElementException("Se l'elemento chimico non è presente nella tavola periodica");
        }
        if(numerosità < 2) throw new IllegalArgumentException("La numerosità dell'atomo deve essere > 1"); //altrimenti costruirei semplicemente un atomo non una molecola
        this.elemento = elemento;
        this.numerosità = numerosità;
    }

    /**
     * Costruisce una molecola semplice data la formula e la tavola periodica
     * @param formula la formula
     * @param tv la tavola periodica
     * @throws NullPointerException se l'elemento chimico o la tavola periodica sono null
     * @throws IllegalArgumentException se la numerosità è < 2 e se nella formula è presente più di un atomo
     * @throws NoSuchElementException ne l'elemento non è nella tavola periodica
     */
    public MolecolaSemplice(final String formula, final TavolaPeriodica tv){
        Objects.requireNonNull(tv,"La tavola periodica non può essere null");
        var componentiFormula = Helpers.parseFormula(formula);
        if (componentiFormula.length > 2) throw new IllegalArgumentException("Nella formula è presente più di un atomo");
        ElementoChimico el = tv.getElemento(componentiFormula[0]);
        int num = Integer.parseInt(componentiFormula[1]);
        if (!tv.èNellaTavola(Objects.requireNonNull(el,"L'elemento non può essere null"))){
            throw new NoSuchElementException("l'elemento chimico non è presente nella tavola periodica");
        }
        if(num < 1) {
            throw new IllegalArgumentException("La numerosità dell'atomo "+formula+" deve essere almeno 1");
        }
        this.elemento = el;
        this.numerosità = num;
    }


    @Override
    public String formula() {
        return elemento.simbolo() + String.valueOf(numerosità);
    }

    @Override
    public double peso() {
        return elemento.peso() * numerosità;
    }

    @Override
    public int numerositàAtomo(ElementoChimico atomo) {
        Objects.requireNonNull(atomo,"L'atomo non può essere null");
        if (atomo.equals(elemento)){
            return numerosità;
        }
        return 0;
    }

    @Override
    public Iterator<ElementoChimico> iterator() {
        return Collections.singleton(elemento).iterator();
    }

    /**
     * Funzione d'astrazione
     */
    @Override
    public String toString() {
      return formula() + ", "+ peso() + ", semplice";
    }
}
