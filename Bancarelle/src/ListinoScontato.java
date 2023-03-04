import java.util.Map;

/**
 * Classe concreta mutabile utile a rappresentare un listino scontato
 */
public class ListinoScontato extends ListinoAstratto{
    private int soglia, sconto;

    /**
     * RI: soglia >= 0 && sconto >= 0
     */

    /**
     * Crea un nuovo listino dei prezzi
     *
     * @param listinoPrezzi il listino dei prezzi
     * @param soglia soglia per applicare lo sconto
     * @throws NullPointerException     se all'interno del listino c'è un giocattolo {@code null}
     * @throws IllegalArgumentException se il prezzo di un giocattolo è negativo o la soglia è negativa o lo sconto non è tra 1 e 100
     */
    public ListinoScontato(Map<Giocattolo, Integer> listinoPrezzi, int soglia, int sconto) {
        super(listinoPrezzi);
        if (soglia < 0) throw new IllegalArgumentException("La soglia deve essere positiva");
        this.soglia = soglia;
        if (sconto < 1 || sconto > 100)
            throw new IllegalArgumentException("Lo sconto dev'essere compreso tra 1 e 100");
        this.sconto = sconto;
    }

    public void cambiaSconto(int sconto){
        if (sconto < 1 || sconto > 100)
            throw new IllegalArgumentException("Lo sconto dev'essere compreso tra 1 e 100");
        this.sconto = sconto;
    }

    public void cambiaSoglia(int soglia){
        if (soglia < 0) throw new IllegalArgumentException("La soglia deve essere positiva");
        this.soglia = soglia;
    }
    @Override
    public int prezzoTotale(int numero, Giocattolo giocattolo) {
        if (!èNelListino(giocattolo)) throw new IllegalArgumentException("Il giocattolo non è nel listino");
        if (numero < 0) throw new IllegalArgumentException("il numero non può essere negativo");
        int totale = prezzoUnitario(giocattolo);
        if (numero >= soglia){
            totale = totale - (totale*sconto)/100;
        }
        return totale;
    }
}
