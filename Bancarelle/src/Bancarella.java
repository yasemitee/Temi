import java.util.Objects;

/**
 * Classe concreta e mutabile utile a rappresentare una bancarella, una bancarella è caratterizzata da un proprietario un inventario e una bancarella
 */
public class Bancarella {
    private final String proprietario;
    private final Inventario inventario;
    private final Listino listino;

    /**
     * RI: il proprietario l'inventario e il listino devono essere diversi da null
     */

    public Bancarella(String proprietario, Inventario inventario, Listino listino) {
        this.proprietario = Objects.requireNonNull(proprietario,"Il proprietario non può essere null");
        this.inventario = Objects.requireNonNull(inventario,"l'inventario non può essere null");
        Objects.requireNonNull(listino,"il listino non può essere null");
        for (var g : inventario){
            if (!listino.èNelListino(g)) throw new IllegalArgumentException("Il giocattolo "+g+" non ha prezzo");
        }
        this.listino = listino;
    }

    /**
     * Restituisce la quantità del giocattolo specificato nell'inventario della bancarella.
     *
     * @param giocattolo il giocattolo.
     * @return il numero di giocattoli specificato nell'inventario della bancarella (eventualmente 0).
     * @throws NullPointerException se giocattolo è {@code null}
     */
    public int quantità(final Giocattolo giocattolo) {
        return inventario.quantità(giocattolo);
    }

    /**
     * Restituisce il prezzo della quantità indicata del giocattolo specificato.
     *
     * @param num la quantità di cui determinare il prezzo.
     * @param giocattolo il giocattolo da vendere.
     * @return il prezzo della quantità indicata del giocattolo specificato.
     * @throws NullPointerException se giocattolo è {@code null}
     * @throws IllegalArgumentException se al listino della bancarella non è noto il prezzo del
     *     giocattolo.
     */
    public int prezzo(final int num, final Giocattolo giocattolo) {
        return listino.prezzoTotale(num, giocattolo);
    }
    /**
     * Effettua la vendita del numero richiesto del giocattolo indicato.
     *
     * @param num il numero di giocattoli da vendere.
     * @param giocattolo il giocattolo da vendere.
     * @throws NullPointerException se il giocattolo è {@code null}.
     * @throws IllegalArgumentException se il numero non è positivo, o eccede il numero di giocattoli
     *     di quel tipo presenti nell'inventario della bancarella.
     */
    public void vende(final int num, final Giocattolo giocattolo) {
        inventario.rimuovi(num, giocattolo);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Bancarella)) return false;
        return ((Bancarella) other).proprietario.equals(proprietario);
    }

    @Override
    public int hashCode() {
        return proprietario.hashCode();
    }

    /**
     * Funzione d'astrazione
     */
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append("Bancarella di: " + proprietario + "\n");
        for (final Giocattolo g : inventario)
            result.append(
                    "num. " + inventario.quantità(g) + " " + g + ", prezzo: " + listino.prezzoTotale(1, g) + "\n");
        return result.toString();
    }
}
