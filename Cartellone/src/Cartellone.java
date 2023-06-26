import java.util.*;

/**
 * Classe concreta mutabile, utile a rappresentare un cartellone mensile di un
 * cinema,
 * caratterizzato dal nome del cinema, dalla durata del mese in giorni e da un
 * elenco di proiezioni.
 */
public class Cartellone implements Iterable<Cartellone.Proiezione> {

    /* Il nome del cinema */
    private final String nome;

    /* La durata del mese */
    private final int durataMese;

    /* La lista delle proiezioni del mese */
    List<Proiezione> proiezioni;

    /*
     * RI:
     * - nome non deve essere null e non deve essere vuoto
     * - durataMese deve essere un numero compreso tra 29 e 31 (estremi inclusi)
     * - proiezioni non deve essere null e non può contenere Proiezioni null
     * 
     * AF: Cartellone Mensile di un cinema di nome = nome, per un mese di durataMese
     * giorni,
     * contenenti le proiezioni descritte dalle proiezioni contenute in proiezioni
     * dove in un giorno del mese è presente una proiezione se la chiave (giorno del
     * mese)
     * ha associato un valore con un titolo di una proiezione
     *
     * 
     */

    /**
     * 
     * @param nome
     * @param durataMese
     * @throws NullPointerException se il nome è {@code null} o se la programmazione
     *                              è {@code null}
     */
    public Cartellone(String nome, int durataMese) {
        if (Objects.requireNonNull(nome, "il nome del cinema non può essere null").isEmpty())
            throw new IllegalArgumentException("Il nome del cinema non può essere vuoto");
        if (durataMese < 28 || durataMese > 31)
            throw new IllegalArgumentException(
                    "La durata del mese deve essere un numero compreso tra 28 e 31 - Found" + durataMese);

        this.nome = nome;
        this.durataMese = durataMese;
        proiezioni = new ArrayList<Proiezione>();
    }

    /**
     * Restituisce il nome del cinema
     * 
     * @return il nome
     */
    public String nome() {
        return nome;
    }

    /**
     * Restituisce al durata del mese del cartellone
     * 
     * @return la durata del mese
     */
    public int durataMese() {
        return durataMese;
    }

    /**
     * Aggiunge una proiezione al cartellone cinematografico dato il titolo e la
     * programmazione della proiezione,
     * se le programmazione della programmazione da aggiungere non si sovrappongono
     * con una delle programmazioni presenti nel cartellone
     * 
     * @param nome           il nome della proiezione
     * @param programmazione la programmazione della proiezione
     * @return {@code true} se la proiezione è stata aggiunta, {@code false}
     *         altrimenti
     * @throws NullPointerException     se il nome è {@code null}, se la
     *                                  programmazione è {@code null}
     * @throws IllegalArgumentException se il nome è vuoto
     * 
     */
    public boolean aggiungiProiezione(String nome, Programmazione programmazione) {
        if (Objects.requireNonNull(nome, "Il nome non può essere null").isEmpty())
            throw new IllegalArgumentException("Il nome non puo essere vuoto");
        Objects.requireNonNull(programmazione, "La programmazione non può essere null");
        for (Proiezione p : proiezioni) {
            for (int d : programmazione) {
                if (p.programmazione.contiene(d))
                    return false;
            }
        }
        this.proiezioni.add(new Proiezione(nome, programmazione));
        return true;
    }

    public Iterator<String> proiezioniGiorni() {
        return new Iterator<String>() {

            int giorno = 1;

            @Override
            public boolean hasNext() {
                return giorno < durataMese + 1;
            }

            @Override
            public String next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                String result = giorno + " - ";
                for (Proiezione p : proiezioni) {
                    for (int d : p.programmazione) {
                        if (d == giorno) {
                            result += p.titolo;
                            break;
                        }
                    }
                }
                giorno++;
                return result;
            }
        };
    }

    /**
     * Restituisce un iteratore sulle proiezioni nell'ordine in cui sono state
     * aggiunte
     * (a prescindere dalle date in cui hanno luogo, una sola volta ciascuna).
     * 
     * @return un iteratore
     * @throws UnsupportedOperationException se si chiama il metodo remove()
     */
    @Override
    public Iterator<Cartellone.Proiezione> iterator() {
        return Collections.unmodifiableList(proiezioni).iterator();
    }

    @Override
    public String toString() {
        return "Cinema: " + nome + ", giorni: " + durataMese + ", proiezioni" + proiezioni.size();
    }

    // ----------------------------------
    /**
     * Record utile a rappresentare una proiezione,
     * una proiezione è caratterizzata da un titolo e una programmazione (la date in
     * cui la proiezione occorre)
     */
    public class Proiezione {

        /* Il titolo della proiezione */
        private final String titolo;

        /* la programmazione della proiezione */
        private final Programmazione programmazione;

        /*
         * RI:
         * - titolo non deve essere null e non deve essere vuoto
         * - programmazione non deve essere null
         */

        // Costruttore

        /**
         * Crea una nuova programmazione dato il titolo della pellicola e la sua
         * programmazione
         * 
         * @param titolo         il titolo della pellicola
         * @param programmazione la programmazione del film
         * @throws NullPointerException se il nome è null o se la programmazione è null
         */
        public Proiezione(String titolo, Programmazione programmazione) {
            if (Objects.requireNonNull(titolo, "il titolo non può essere null").isEmpty())
                throw new IllegalArgumentException("Il titolo non può essere vuoto");
            Objects.requireNonNull(programmazione, "La programmazione non può essere null");
            this.titolo = titolo;
            this.programmazione = programmazione;
        }

        // Metodi

        /**
         * Restituisce il titolo della programmazione
         * 
         * @return il titolo
         */
        public String titolo() {
            return titolo;
        }

        /**
         * Restituisce la programmazione della proiezione
         * 
         * @return la programmazione
         */
        public Programmazione programmazione() {
            return programmazione;
        }

        /* Funzione d'astrazione */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Cinema: ").append(nome).append("titolo: ").append(titolo).append("date: ");
            Iterator<Integer> it = programmazione.iterator();
            while (it.hasNext()) {
                int p = it.next();
                if (p < durataMese) {
                    sb.append(p);
                }
                if (it.hasNext())
                    sb.append(", ");
            }
            return sb.toString();
        }
    }
}
