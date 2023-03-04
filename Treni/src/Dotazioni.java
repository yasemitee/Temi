import java.util.Objects;

/**
 * Classe concreta e immutabile utile rappresentare le varie dotazioni dei vagoni
 */
public class Dotazioni implements Comparable<Dotazioni>{
    public final String nome;
    public final int quanità;

    /**
     * RI: il nome non deve essere null && la quantità non deve essere <= 0
     */

    /**
     * Crea una nuova dotazione dato un nome e una quantità
     * @param nome il nome della dotazione
     * @param quanità la quantità della dotazione
     * @throws IllegalArgumentException se la quantità è <= 0
     * @throws NullPointerException se nome è {@code null}
     */
    public Dotazioni(final String nome, final int quanità){
        this.nome = Objects.requireNonNull(nome,"il nome non deve essere null");
        if (quanità < 0) throw new IllegalArgumentException("La quantità non può essere <= 0");
        this.quanità = quanità;
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof Dotazioni){
            if ( ((Dotazioni)other).nome.equals(nome) ){
                return true;
            }
        }
        return false;
    }

    /**
     * Funzione d'astrazione
     */
    @Override
    public String toString() {
        return nome +" = "+quanità;
    }

    @Override
    public int compareTo(Dotazioni o) {
        return nome.compareTo(o.nome);
    }
}
