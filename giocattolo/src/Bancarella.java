import java.util.HashMap;
import java.util.Objects;

public class Bancarella {
    public class Inventario {
        /**
         * Inventario è una classe mutabile utile a rappresentare l'inventario di una
         * bancarella, un inventario è una collezione di giocattoli vendibili dalla
         * bancarella.
         * RI(Inventario): I giocattoli all'interno dell'inventario non devono essere
         * null
         */
        private final HashMap<Giocattolo, Integer> magazzino;

        public Inventario() {
            magazzino = new HashMap<Giocattolo, Integer>();
        }

        /**
         * Aggiunge un giocattolo all'inventario
         * 
         * @param g giocattolo
         * @throws NullPointerException se g è null
         */
        public void aggiungi(int num, Giocattolo g) {

            Objects.requireNonNull(g, "Il Giocattolo non può essere null");
            magazzino.put(g, magazzino.get(g) + num);
        }

        /**
         * Rimuove un giocattolo specifico dall'inventario
         * 
         * @param g giocattolo
         * @throws NullPointerException se g è null
         */
        public void rimuovi(int num, Giocattolo g) {
            if (magazzino.containsKey(Objects.requireNonNull(g))) {
                if (magazzino.get(g) > num) {
                    magazzino.put(g, magazzino.get(g) - num);
                } else {
                    magazzino.remove(g);
                }
            }
        }

        /**
         * Restituisce il numero di giocattoli dato un giocattolo
         * 
         * @param g giocattolo
         * @return numero intoro
         */
        public int quantiDisponibili(Giocattolo g) {
            return magazzino.get(Objects.requireNonNull(g, "Il Giocattolo non può essere null"));
        }
    }

    public final String proprietario;

    /** Il listino della bancarella. */
    private final ListinoUnitario listino;

    /** L'inventario della bancarella. */
    private final Inventario inventario;

    // RI: gli attributi non sono null, il proprietario non è vuoto; il listino
    // conosce il prezzo di ogni giocattolo presente nell'inventario.
}
