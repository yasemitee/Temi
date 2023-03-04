public interface Prezzo {
    /**
     * Restituisce il prezzo totale del giocattoli di uno stesso tipo
     * 
     * @param g Giocattolo
     * @param q Quantità
     * @return prezzo totale
     */
    public int prezzoTotale(final Giocattolo g, final int q);
}
