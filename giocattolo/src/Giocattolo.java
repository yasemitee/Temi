import java.util.Objects;

/**
 * Le istanze di questa classe sono mutabili e utili a rappresentare un
 * giocattolo, un giocattolo è caratterizzato da un nome e un materiale
 * RI(Giocattolo): Giocattolo.nome != null && Giocattolo.materiale != null
 */
public class Giocattolo {
    private final String nome;
    private final String materiale;

    /**
     * Crea un nuovo giocattolo
     * 
     * @param nome      del giocattolo
     * @param materiale di cui è fatto il giocattolo
     * @throws NullPointerException se nome o materiale sono null
     */
    public Giocattolo(String nome, String materiale) {
        this.nome = Objects.requireNonNull(nome, "Il nome non può essere null");
        this.materiale = Objects.requireNonNull(materiale, "Il nome non può essere null");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((materiale == null) ? 0 : materiale.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Giocattolo other = (Giocattolo) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (materiale == null) {
            if (other.materiale != null)
                return false;
        } else if (!materiale.equals(other.materiale))
            return false;
        return true;
    }

}
