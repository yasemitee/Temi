package Playfy.src;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Le istanze di questa classe sono immutabili e utili a rappresentare un album,
 * un album è caratterizzato da un nome, una durata complessiva e un insieme
 * ordinato di brani (in base all'inserimento) caratterizzato da una durata e un
 * nome.
 * 
 * FA(Playfy.src.Album): Nome: brano.nome Playfy.src.Durata: brano.durata
 * 
 * RI(Playfy.src.Album): ALBUM: Playfy.src.Album.nome DURATA ALBUM: Playfy.src.Album.durata
 * Per ogni brano:
 * Playfy.src.Album.brano.nomei != Playfy.src.Album.brano.nomej per ogni i e j
 * && Playfy.src.Album.size > 0
 * && Playfy.src.Album.titolo != null
 * && l'album mantiene l'ordine relativo all'inserimento
 */
public class Album implements Iterable<Album.Brano>{
    public class Brano {
        //RI: titolo e durata non sono null, titolo non è vuoto, durata non è 0
        public final String nome;
        public final Durata durata;
        /**
         * Costruisce un brano.
         * Una volta creato, un brano deve essere aggiunto all'album che lo avvolge.
         *
         * @param titolo il titolo.
         * @param durata la durata.
         * @throws NullPointerException     se titolo o durata sono nulli.
         * @throws IllegalArgumentException se il titolo è vuoto, o la durata è 0.
         */
        private Brano(final String titolo, final Durata durata) {
            if (Objects.requireNonNull(titolo, "Il titolo non può essere null.").isEmpty())
                throw new IllegalArgumentException("Il titolo non può essere vuoto.");
            if (Objects.requireNonNull(durata, "La durata non può essere null.").secondi() == 0)
                throw new IllegalArgumentException("La durata non può essere pari a zero.");
            this.nome = titolo;
            this.durata = durata;
        }

        @Override
        public String toString() {
            return "Titolo: "+nome+" Durata: "+durata;
        }

        public Album album() {
            return Album.nome;
        }
    }
    public final String nome;
    private final Durata durata;
    private final Brano[] brani;

    /**
     * Crea un nuovo album contenente dei brani
     *
     * @param nome   dell'album
     * @param titoli lista dei titoli dei brani da inserire nell'album
     * @param durate durate corrispondenti ai titoli
     * @throws NullPointerException     se il nome è null
     * @throws IllegalArgumentException se aggiungo due brani uguali all'album
     */
    public Album(String nome, List<String> titoli, List<Durata> durate) {
        this.nome = Objects.requireNonNull(nome, "Il nome non può essere null");
        if (titoli.size() == 0 || durate.size() == 0 || titoli.size() != durate.size())
            throw new IllegalArgumentException("Il formato delle liste non è valido");
        brani = new Brano[titoli.size()];
        Durata durataTotale = new Durata(0);
        for (int i = 0; i < titoli.size(); i++) {
            Brano newBrano = new Brano(titoli.get(i), durate.get(i));
            if (Arrays.asList(brani).contains(newBrano))
                throw new IllegalArgumentException("Non possono avere brani uguali all'interno dell'album");
            durataTotale = durataTotale.somma(durate.get(i));
            brani[i] = newBrano;
        }
        this.durata = durataTotale;
    }



    public Brano getBrano(String nome) {
        Objects.requireNonNull(nome, "Il nome non può essere null");
        for (Brano br : brani) {
            if (br.nome.equals(nome))
                return br;
        }
        return null;
    }

    public Brano getBranoInPosizione(int i) {
        if (i < 0 || i > brani.length)
            throw new IndexOutOfBoundsException("Indice non valido");
        return brani[i];
    }

    /**
     * @param titolo del brano
     * @return la posizione del brano
     */
    public int getPosizioneDelBrano(String titolo) {
        Objects.requireNonNull(titolo, "Il titolo non può essere null");
        for (int i = 0; i < brani.length; i++) {
            if (brani[i].nome.equals(titolo))
                return i;
        }
        return -1;
    }

    @Override
    public Iterator<Album.Brano> iterator() {
        return new albumIterator(this.brani);
    }
    private class albumIterator implements Iterator<Album.Brano> {
        private Brano[] brani;
        public albumIterator(Brano[] brani) {
            this.brani = brani;
        }
        private int index;
        @Override
        public boolean hasNext() {
            return index < brani.length;
        }

        @Override
        public Brano next() {
            return brani[index++];
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ALBUM: " + nome + " DURATA ALBUM: " + durata + "\n");
        for (Brano brano : brani) {
            sb.append("Nome: " + brano.nome + " Playfy.src.Durata: " + brano.durata + "\n");
        }
        return sb.toString();
    }
}

