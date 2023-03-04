/**
 * Interfaccia utile a rappresentare un listino prezzi
 */
public interface Listino {
    /**
     * Restituisce il prezzo di un giocattolo se presente nel listino
     * @param giocattolo il giocattolo di cui si vuole sapere il prezzo
     * @return il prezzo
     * @throws NullPointerException il giocattolo non può essere {@code null}
     * @throws IllegalArgumentException se il giocattolo non è nel listino
     */
    public int prezzoUnitario(final Giocattolo giocattolo);

    /**
     * Restituisce il prezzo totale dei giocattoli specificando il numero di giocattoli.
     * Il prezzo totale viene calcolato in base alla politica del listino specifico.
     * @param numero il numero di oggetti
     * @param giocattolo il giocattolo
     * @return il prezzo totale
     * @throws NullPointerException se il giocattolo è {@code null}
     * @throws IllegalArgumentException se il numero è negativo
     */
    public int prezzoTotale(int numero,final Giocattolo giocattolo);

    /**
     * Indica se il listino conosce il prezzo di un dato giocattolo.
     *
     * @param giocattolo il giocattolo.
     * @return se il listino conosce, o meno, il prezzo del giocattolo.
     * @throws NullPointerException se il giocattolo è null
     */
    public boolean èNelListino(final Giocattolo giocattolo);
}
