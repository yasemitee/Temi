import java.util.*;

/**
 * Classe concreta e immutabile utile a rappresentare una molecola composta,
 * ovvero una molecola formata da più atomi
 */
public class MolecolaComposta implements Molecola{
    /** l'insieme che tiene traccia degli atomi e la loro numerosità */
    private final Map<ElementoChimico,Integer> elementiMolecola = new TreeMap<>();
    /**
     * RI: Gli elementi chimici in elementi molecola non devono essere null, ogni numerosità deve essere > 0, devono esserci almeno 2 atomi
     */
    public MolecolaComposta(final String formula, TavolaPeriodica tv) {
        Objects.requireNonNull(tv,"La tavola periodica non può essere null");
        var componentiFormula = Helpers.parseFormula(formula);
        if (componentiFormula.length < 3) throw new IllegalArgumentException("Nella formula è presente solo un atomo");
        for (int i = 0; i < componentiFormula.length; i += 2) {
            ElementoChimico el = tv.getElemento(componentiFormula[i]);
            int num = Integer.parseInt(componentiFormula[1]);
            if (!tv.èNellaTavola(Objects.requireNonNull(el,"L'elemento non può essere null"))){
                throw new NoSuchElementException("l'elemento chimico non è presente nella tavola periodica");
            }
            if(num < 1) throw new IllegalArgumentException("La numerosità dell'atomo deve essere almeno 1");
            elementiMolecola.put(el,num);
        }
    }

    @Override
    public String formula() {
        String formula = "";
        for (var el : elementiMolecola.entrySet()){
            formula += el.getKey().simbolo()+ String.valueOf(el.getValue() > 1 ? el.getKey() : "");
        }
        return formula;
    }

    @Override
    public double peso() {
        Double peso = 0.0;
        for (var el : elementiMolecola.entrySet()){
            peso += el.getKey().peso() * el.getValue();
        }
        return peso;
    }

    @Override
    public int numerositàAtomo(ElementoChimico atomo) {
        Objects.requireNonNull(atomo,"L'atomo non può essere null");
        if (elementiMolecola.containsKey(atomo)){
            return elementiMolecola.get(atomo);
        }
        return 0;
    }

    @Override
    public Iterator<ElementoChimico> iterator() {
        return Collections.unmodifiableCollection(elementiMolecola.keySet()).iterator();
    }

    /**
     * Funzione d'astrazione
     */
    @Override
    public String toString() {
        return formula() + ", "+ peso() + ", composta";
    }
}
