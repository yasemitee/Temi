import java.math.BigDecimal;
import java.util.Objects;

/**
 * Classe concreta immutabile utile a rappresentare un Importo, 
 * un importo è caratterizzato da un valore e da un valuta
 * 
 * Implementa {@code Comparable}, fornendo quindi una relazione di confronto tra due importi
 * (se questi sono dello stessa valuta)
 */
public record Importo(BigDecimal valore, Valuta valuta) implements Comparable<Importo>{

    /*
     * RI: 
     *  - valore e valuta devono essere diversi da null
     */

    /**
     * Costruisce un nuovo importo a partire da un valore e una valuta
     * @param valore il valore dell'importo
     * @param valuta la valuta dell'importo
     * @throws NullPointerException se la valuta è null o il valore è null
     */
    public Importo{
        Objects.requireNonNull(valuta,"La valuta dell'importo non può essere null");
        Objects.requireNonNull(valore,"il valore dell'importo non può essere null");
    }

    /**
     * Consente di controllare se due importi hanno la stessa valuta
     * @param o un altro importo
     * @return {@code true} se i due importi hanno la stessa valuta, {@code false} altrimenti
     * @throws NullPointerException se o è {@code null}
     */
    private boolean controlloValuta(Importo o){
        Objects.requireNonNull(o, "l'importo di cui si vuole confrontare la valuta non può essere null");
        return valuta.equals(o.valuta);
    }

    /**
     * Restituisce l'importo corrispondente a "zero" nella valuta di this
     * @return l'importo zero
     */
    public Importo zero(){
        return new Importo(new BigDecimal(0), this.valuta);
    }

    /**
     * Verifica che this sia uguale a 0,
     * restituendo {@code true} se this è uguale a zero, {@code false} altrimenti
     * @return {@code true} se this è uguale a zero, {@code false} altrimenti
     */
    public boolean isZero(){
        return this.valore.equals(zero().valore);
    }

    @Override
    public int compareTo(Importo o) {
        if (controlloValuta(o)){
            return valore.compareTo(o.valore);
        }else{
            throw new IllegalArgumentException("L'importo fornito di valuta "+o.valuta+" non può essere confrontato con this valuta");
        }
    }

    /**
     * Restituisce un nuovo importo corrispondente alla somma di this e un altro importo
     * (se questi hanno la stessa valuta)
     * @param o un altro importo
     * @throws IllegalArgumentException se le valute di this e o non sono uguali
     * @return un nuovo importo
     */
    public Importo somma(Importo o){
        if (controlloValuta(o)){
            return new Importo(valore.add(o.valore), valuta);
        }
        else{
            throw new IllegalArgumentException("L'importo fornito di valuta "+o.valuta+" non può essere confrontato con this valuta");
        }
    }

    /**
     * Restituisce un nuovo importo corrispondente alla differenza (operazione di sottrazione) 
     * tra this e un altro importo (se questi hanno la stessa valuta)
     * @param o un altro importo
     * @throws IllegalArgumentException se le valute di this e o non sono uguali
     * @return un nuovo importo
     */
    public Importo sottrai(Importo o){
        if (controlloValuta(o)){
            return new Importo(valore.subtract(o.valore), valuta);
        }
        else{
            throw new IllegalArgumentException("L'importo fornito di valuta "+o.valuta+" non può essere confrontato con this valuta");
        }
    }

    @Override
    public String toString(){
        return valuta.simbolo+valore.toString();
    }
}
