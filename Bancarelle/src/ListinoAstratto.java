import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Classe astratta immutabile che implementa l'interfaccia listino, utile a rappresentare un listino astratto che tiene la corrispondenza tra oggetto e il suo prezzo
 */
public abstract class ListinoAstratto implements Listino{
    private final Map<Giocattolo,Integer> listinoPrezzi;
    /**
     * RI: nel listino non ci possono essere giocattoli null o prezzi negativi
     */

    /**
     * Crea un nuovo listino dei prezzi
     * @param listinoPrezzi il listino dei prezzi
     * @throws NullPointerException se all'interno del listino c'è un giocattolo {@code null}
     * @throws IllegalArgumentException se il prezzo di un giocattolo è {@code null}
     */
    public ListinoAstratto(Map<Giocattolo, Integer> listinoPrezzi) {
        this.listinoPrezzi = new HashMap<>();
        for (var e : listinoPrezzi.entrySet()){
            final Giocattolo g = Objects.requireNonNull(e.getKey(),"Il giocattolo non può essere null");
            if (e.getValue() < 0) throw new IllegalArgumentException("Il prezzo non può essere negativo");
            final int p = e.getValue();
            this.listinoPrezzi.put(g,p);
        }
    }

    @Override
    public int prezzoUnitario(Giocattolo giocattolo) {
       if (!èNelListino(giocattolo)) throw new IllegalArgumentException("Il giocattolo non è presente nel listino");
       return listinoPrezzi.get(giocattolo);
    }

    @Override
    public boolean èNelListino(Giocattolo giocattolo) {
        return listinoPrezzi.containsKey(Objects.requireNonNull(giocattolo));
    }
    @Override
    public abstract int prezzoTotale(int numero, Giocattolo giocattolo);
}
