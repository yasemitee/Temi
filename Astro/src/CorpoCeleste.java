import java.util.Objects;

public abstract class CorpoCeleste {
    /**
     * Questa classe astratta è utile a rappresentare un copro celeste (Pianeti,
     * Stelle), un corpo celeste è caratterizzato da un nome e un'enerigia.
     * IR(CorpoCeleste): CorpoCeleste.nome != null
     */
    public final String nome;

    public CorpoCeleste(final String nome) {
        this.nome = Objects.requireNonNull(nome, "Il nome non può essere null");
    }

    /**
     * Restituisce l'energia di un corpo celeste come da definizione
     * 
     * @return l'energia
     */
    public long energia() {
        return posizione().norma() * velocità().norma();

    }

    /**
     * Le implementazioni restituiscono la velocità(non null)
     * 
     * @return un velocità
     */
    public abstract Punto velocità();

    /**
     * Le implementazioni restituiscono la posizione(non null)
     * 
     * @return una posizione
     */
    public abstract Punto posizione();

    /**
     * Le implementazioni aggiornano la posizione del corpo celeste
     */
    public abstract void aggiornaPosizione();

    /**
     * Le implementazioni aggiornano le informazioni di this in base alle informazioni di c
     * inoltre bisogna garantire che c non venga modificato in alcun modo
     * 
     * @param c
     *        corpo celeste da confrontare
     * @throws NullPointerException
     *         se c è null
     */
    public abstract void aggiornaVelocità(final CorpoCeleste c);

}