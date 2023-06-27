
/**
 * Enum utile ad avere definire delle valute caratterizzate da un nome e un simbolo
 */
public enum Valuta{
    Dollaro('$'),
    Euro ('€'),
    Franco ('₣'),
    Lira ('₺'),
    Rupia ('₹'),
    Sterlina ('£'),
    Yen ('¥');

    /*
     * RI: 
     *  - Il simbolo associato alla valuta non deve essere vuoto
     *  - il nome della valuta non può essere null o vuoto;
     */

    /* Il simbolo della valuta */
    public final char simbolo;

    /**
     * Costruisce una valuta a partire da un simbolo
     * @param simbolo il simbolo della valuta
     * @throws IllegalArgumentException se il simbolo è vuoto
     */
    private Valuta(char simbolo) {
        if (Character.isWhitespace(simbolo))
            throw new IllegalArgumentException("Il simbolo non può essere vuoto");
        this.simbolo = simbolo;
    }

}