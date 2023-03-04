import java.util.Map;

/**
 *Classe concreta e immutabile, utile a rappresentare un listino moltiplicativo, dove il prezzo è dato da U * N
 */
public class ListinoMoltiplicativo extends ListinoAstratto {

    /**
     * Crea un nuovo listino dei prezzi
     *
     * @param listinoPrezzi il listino dei prezzi
     * @throws NullPointerException     se all'interno del listino c'è un giocattolo {@code null}
     * @throws IllegalArgumentException se il prezzo di un giocattolo è {@code null}
     */
    public ListinoMoltiplicativo(Map<Giocattolo, Integer> listinoPrezzi) {
        super(listinoPrezzi);
    }

    @Override
    public int prezzoTotale(int numero, Giocattolo giocattolo) {
        if (!èNelListino(giocattolo)) throw new IllegalArgumentException("Il giocattolo non è nel listino");
        if (numero < 0) throw new IllegalArgumentException("il numero non può essere negativo");
        return prezzoUnitario(giocattolo) * numero;
    }
}
