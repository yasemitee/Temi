import java.util.Objects;

/**
 * Classe concreta e mutabile utile a rappresentare una nave (nel gioco di
 * battaglia navale),
 * una nave è caratterizzata da un tipo, una posizione di partenza e l'
 * orientamento
 */
public class Nave {
    private final String tipo;
    private final Posizione posizione;
    private final String orientamento;
    private final Posizione[] danniSubiti;

    /**
     * RI:
     * - Il tipo non deve essere null o una stringa vuota e deve essere un tipo di
     * nave
     * - La posizione non deve essere null
     * - L'orientamento può essere solo O o V
     * - Danni subiti non deve essere null o contenere null
     */

    public Nave(String tipo, Posizione posizione, String orientamento) {
        if (Objects.requireNonNull(tipo, "Il tipo non può essere null").isEmpty()) {
            throw new IllegalArgumentException("Il tipo non deve essere vuoto");
        }
        this.tipo = tipo;
        this.posizione = posizione;
        this.orientamento = orientamento;
        this.danniSubiti = danniSubiti;
    }

}
