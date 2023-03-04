package Playfy.src;

import java.util.*;

/**
 * Le istanze di questa classe sono mutabili e utili a rappresentare una playlist.
 * Una playlist è una collezione di brani inizialmente vuota ed è caratterizzata da un titolo (modificabile) e una durata (che corrisponde alla durata di tutti i brani)
 *
 * RI(Playlist): Playlist.titolo != null && Playlist.size >= 0
 */
public class Playlist implements Iterable<Album.Brano>{
    private String titolo;
    private Durata durata = new Durata(0);
    private final List<Album.Brano> brani = new ArrayList<>();

    public Playlist(String titolo) {
        if (Objects.requireNonNull(titolo, "Il titolo non può essere null.").isEmpty())
            throw new IllegalArgumentException("Il titolo non può essere vuoto.");
    }
    public Durata getDurata() {
        return durata;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Aggiunge un brano alla playlist
     * @param b brano da aggiungere
     * @throws NullPointerException se b è null
     */
    public void aggiungiBrano(Album.Brano b){
         brani.add(Objects.requireNonNull(b,"Il brano non può essere null"));
         durata = durata.somma(b.durata);
    }
    /**
     * Rimuove un brano dalla playlist
     * @param b brano da rimuovere
     * @throws NullPointerException se b è null
     */
    public void rimuoviBrano(Album.Brano b){
        brani.remove(Objects.requireNonNull(b,"Il brano non può essere null"));
        durata = durata.sottrai(b.durata);
    }

    /**
     * Restituisce un brano data una posizione
     * @param pos posizione del brano
     * @return il brano in posizione pos
     */
    public Album.Brano branoDataLaPosizione(final int pos) {
        try {
            return brani.get(pos);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Il numero di brano non è compreso tra 1 e " + brani.size());
        }
    }

    /**
     * Restituisce la posizione dato un brano
     * @param brano
     * @return posizione del brano nella playlist
     */
    public int posizioneDatoIlBrano(final Album.Brano brano) {
        return brani.indexOf(Objects.requireNonNull(brano, "Il brano non può essere null."));
    }

    /**
     * Restituisce la dimensione della playlist
     * @return il numero di brani all'interno della playlist
     */
    public int size(){
        return brani.size();
    }

    public Playlist fondi(final String nome, final Playlist altra) {
        Objects.requireNonNull(nome,"Il nome non può essere null");
        Objects.requireNonNull(altra,"La playlist non può essere null");
        Playlist nuova = new Playlist(nome);
        for (Album.Brano b : altra.brani) nuova.aggiungiBrano(b);
        for (Album.Brano b : altra.brani) nuova.aggiungiBrano(b);
        return nuova;
    }

    /**
     * Restituisce un iteratore che enumera tutti i brani della playlist che provengono dall'album
     * dato.
     *
     * @param album l'album.
     * @return l'iteartore.
     * @throws NullPointerException se l'album è {@code null}.
     */
    public Iterator<Album.Brano> brani(final Album album) {
        Objects.requireNonNull(album, "L'album non può essere null.");
        return new Iterator<Album.Brano>() {

            /** Un iteratore su tutti i brani della playlist. */
            private final Iterator<Album.Brano> it = iterator();

            /** Il prossimo brano da restituire. */
            private Album.Brano next = null;

            @Override
            public boolean hasNext() {
                if (next != null) return true;
                while (it.hasNext()) {
                    next = it.next();
                    if (next.appartiene(album)) return true;
                }
                next = null;
                return false;
            }

            @Override
            public Album.Brano next() {
                if (!hasNext()) throw new NoSuchElementException();
                final Album.Brano ret = next;
                next = null;
                return ret;
            }
        };
    }

    /**
     * Restituisce un iteratore che enumera (senza ripetizioni) gli album di cui esiste un brano in
     * questa playlist.
     *
     * @return l'itertore.
     */
    public Iterator<Album> album() {
        return new Iterator<Album>() {

            /** Un iteratore su tutti i brani della playlist. */
            private final Iterator<Album.Brano> it = iterator();

            /** Il prossimo album da restituire. */
            private Album next = null;

            /** L'insieme degli album restituiti da {@link #next()}. */
            private final Set<Album> restituiti = new HashSet<>();

            @Override
            public boolean hasNext() {
                if (next != null) return true;
                while (it.hasNext()) {
                    next = it.next().album();
                    if (!restituiti.contains(next)) {
                        restituiti.add(next);
                        return true;
                    }
                }
                next = null;
                return false;
            }

            @Override
            public Album next() {
                if (!hasNext()) throw new NoSuchElementException();
                final Album ret = next;
                next = null;
                return ret;
            }
        };
    }

    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder();
        s.append("Nome playlist: " + titolo + "\n");
        for (int i = 0; i < brani.size(); i++)
            s.append(String.format("%d - %s\n", i + 1, brani.get(i).asString(true)));
        s.append("Durata totale: " + durata);
        return s.toString();
    }

    @Override
    public Iterator<Album.Brano> iterator() {
        return Collections.unmodifiableCollection(brani).iterator();
    }
}
}
