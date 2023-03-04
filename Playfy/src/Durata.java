package Playfy.src;

import java.util.Objects;

/**
 * Record (immutabile) che rappresenta una durata (non negativa).
 * Le istanze possono essere costruite a partire dalla durata espressa in
 * secondi, oppure tramite un metodo di fabbricazione che accetta stringhe
 * del formato
 */
public record Durata(int secondi) {

    /**
     * Costruisce una durata.
     *
     * @param secondi la durata espressa in secondi.
     * @throws IllegalArgumentException se la durata è negativa.
     */
    public Durata {
        if (secondi < 0)
            throw new IllegalArgumentException("La durata non può essere negativa.");
    }

    /**
     * 
     * @param componente la componente dell'orario.
     * @param bounded    se il il valore numerico della componente dev'essere minore
     *                   di 60.
     * @return il valore numerico della componente.
     * @throws NullPointerException     se la componente è {@code null}.
     * @throws IllegalArgumentException se la componente è vuota, se non può essere
     *                                  converita in un
     *                                  intero, se il suo valore non è compreso tra
     *                                  0 (compreso) e il bound specificato
     *                                  (escluso).
     */
    private static int toHMS(final String componente, final boolean bounded) {
        if (Objects.requireNonNull(componente, "La componente non può essere null.").isEmpty())
            throw new IllegalArgumentException("La componente non può essere vuota.");
        int hms;
        try {
            hms = Integer.parseInt(componente);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La componente \"" + componente + "\" non è un numero.");
        }
        if (hms < 0)
            throw new IllegalArgumentException(
                    "Il valore della componente \"" + componente + "\" non può essere negativo.");
        if (bounded && hms > 59)
            throw new IllegalArgumentException(
                    "Il valore della componente \"" + componente + "\" deve essere minore di 60.");
        return hms;
    }

    /**
     * Fabbrica una durata data una stringa del formato HH:MM:SS
     *
     * @param durata la durata nel formato specificato.
     * @return una {@link Durata}.
     * @throws NullPointerException     se la durata è {@code null}.
     * @throws IllegalArgumentException se la stringa è nulla, o non è nel formato
     *                                  specificato.
     */
    static Durata valueOf(final String durata) {
        if (Objects.requireNonNull(durata, "La durata non può essere null.").isEmpty())
            throw new IllegalArgumentException("La durata non può essere vuota.");
        final String[] parti = durata.split(":");
        final int numParti = parti.length;
        if (numParti > 3)
            throw new IllegalArgumentException("L'orario non può comprendere \":\" più di due volte.");
        try {
            int ore = numParti < 3 ? 0 : toHMS(parti[numParti - 3], false);
            int minuti = numParti < 2 ? 0 : toHMS(parti[numParti - 2], true);
            int secondi = toHMS(parti[numParti - 1], true);
            return new Durata(3600 * ore + 60 * minuti + secondi);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Formato della durata invalito. " + e.getMessage());
        }
    }

    /**
     * Restituisce una nuova durata pari alla somma di questa durata con
     * l'argomento.
     *
     * @param altra la durata da sommare a questa.
     * @return la somma delle durate.
     * @throws NullPointerException se la durata è {@code null}.
     */
    public Durata somma(final Durata altra) {
        return new Durata(
                this.secondi + Objects.requireNonNull(altra, "La durata non può essere null.").secondi);
    }

    /**
     * Restituisce una nuova durata pari alla differenza tra questa durata con
     * l'argomento.
     *
     * @param altra la durata da sottrarre da questa.
     * @return la differenza delle durate.
     * @throws NullPointerException     se la durata è {@code null}.
     * @throws IllegalArgumentException se l'altra durata è maggiore di questa.
     */
    public Durata sottrai(final Durata altra) {
        return new Durata(
                this.secondi - Objects.requireNonNull(altra, "La durata non può essere null.").secondi);
    }

    @Override
    public String toString() {
        final int hh = secondi / 3600;
        return String.format("%s%02d:%02d", hh > 0 ? hh + ":" : "", (secondi / 60) % 60, secondi % 60);
    }

}