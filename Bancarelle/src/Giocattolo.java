import java.util.Objects;

/**
 *  Record (immutabile) che permette di rappresentare un giocattolo, definito da un nome e un materiale
 */
public record Giocattolo(String nome, String materiale) {
    /**
     * RI: il nome non può essere null && il materile non può essere null
     */

    /**
     * Crea un nuovo giocattolo dato un nome e un materiale
     * @param nome il nome del giocattolo
     * @param materiale il materiale del giocattolo
     * @throws NullPointerException se il nome è null o se il materiale è null
     */
    public Giocattolo{
        Objects.requireNonNull(nome,"Il nome non può essere null");
        Objects.requireNonNull(materiale,"Il materiale non può essere null");
    }

    @Override
    public String toString() {
        return nome +" di "+materiale;
    }
}
