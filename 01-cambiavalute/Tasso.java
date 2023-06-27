import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * record utile a rappresentare un Tasso di cambio,
 * un tasso di cambio è specificato da due importi (con valute diverse) 
 * da intendersi "equivalenti" nel senso che è possibile convertire qualunque multiplo del primo importo nello stesso multiplo del secondo.
 */
public record Tasso(Importo primoImporto, Importo secondoImporto) {

    /*
     * RI:
     * - primaValuta e secondaValuta non devono essere null
     * - primaValuta e secondaValuta non possono avere la stessa valuta
     */

     /**
      * Costruisce un nuovo tasso a partire da 2 importi
      * @param primaValuta il primo importo
      * @param secondaValuta il secondo importo
      * @throws NullPointerException se primoImporto o secondoImprto sono {@code null}
      * @throws IllegalArgumentException se primoImporto o secondoImprto hanno la stessa valuta
      */
    public Tasso{
        Objects.requireNonNull(primoImporto, "Il primo importo coinvolto nel cambio non può essere null");
        Objects.requireNonNull(secondoImporto, "Il secondo importo coinvolto nel cambio non può essere null");
        if (primoImporto.valuta().equals(secondoImporto.valuta()))
            throw new IllegalArgumentException("Le due valute associate agli importi non possono essere uguali");
    }

    /**
     * Converte il primo importo nella valuta del secondo importo
     * @return un nuovo importo corrispondente alla conversione
     * @throws NullPointerException se primoImporto o secondoImprto sono {@code null}
     */
    public Importo converti(Importo i){
        Objects.requireNonNull(primoImporto, "Il primo importo coinvolto nella conversione non può essere null");
        Objects.requireNonNull(secondoImporto, "Il secondo importo coinvolto nella conversione non può essere null");
        BigDecimal tassoCambio = new BigDecimal(0);
        BigDecimal v = new BigDecimal(0);
        Valuta valutaFinale = i.valuta();
        if (i.valuta().equals(primoImporto.valuta())){
            v = primoImporto.valore();
            tassoCambio = secondoImporto.valore();
            valutaFinale = secondoImporto.valuta();
        }else{
            v = secondoImporto.valore();
            tassoCambio = primoImporto.valore();
            valutaFinale = primoImporto.valuta();
        }
        BigDecimal cambio = new BigDecimal(0);
        
        cambio = (tassoCambio.multiply(i.valore())).divide(v);
        return new Importo(cambio, valutaFinale);
    }

    /* Funzione d'astrazione */
    @Override
    public String toString() {
        return primoImporto.toString() +" = "+secondoImporto.toString();
    }

    


}
