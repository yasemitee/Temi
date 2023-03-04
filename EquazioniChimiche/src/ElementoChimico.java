import java.util.Objects;

/**
 * Record (immutabile) utile a rappresentare un elemento chimico, ogni elemento chimico è caratterizzato da:
 * un nome, un simbolo, un peso e il suo numero atomico (numero di atomi presenti)
 */
public record ElementoChimico(int numeroAtomico, String nome, String simbolo, double peso) implements Comparable<ElementoChimico>{
    /**
     * RI: numero atomico deve essere compreso tra 1 e 118 compresi
     * && nome non deve essere null e non deve essere vuoto
     * && il simbolo non deve essere null, non deve essere vuoto e la prima lettera deve essere maiuscola
     * && peso > 0
     */

    /**
     * Costruisce un nuovo elemento chimico dato il numero atomico, il nome, il simolo, il peso
     * @param numeroAtomico il numero atomico dell'elemento chimico
     * @param nome il nome dell'elemento
     * @param simbolo il simbolo dell'elemento
     * @param peso il peso dell'elemento
     * @throws NullPointerException se il nome o il simbolo sono null
     * @throws IllegalArgumentException se il nome o il simbolo sono vuoti, se il numero atomico non è compreso tra 0 e 118 o il peso è negativo
     */
    public ElementoChimico {
        if (numeroAtomico < 1 || numeroAtomico > 118) throw new IllegalArgumentException("il numero atomico non è compreso tra 0 e 118");
        if (Objects.requireNonNull(nome,"Il nome non può essere null").isEmpty())
            throw new IllegalArgumentException("Il nome non può essere null");
        if (Objects.requireNonNull(simbolo,"Il simbolo non può essere null").isEmpty()) {
            throw new IllegalArgumentException("Il simbolo non può essere null");
        }
        if (!Helpers.elementoValido(simbolo)) throw new IllegalArgumentException("Il simbolo non ha un formato valido (ovvero deve essere composta da un carattere maiuscolo, eventualmente seguito da caratteri minuscoli)");
        if (peso < 0) throw new IllegalArgumentException("Il peso non può essere negativo");
    }


    @Override
    public int compareTo(ElementoChimico o) {
        return Integer.compare(numeroAtomico, o.numeroAtomico);
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof ElementoChimico)) return false;
        ElementoChimico other = (ElementoChimico) o;
        return this.simbolo.equals(other.simbolo) && this.nome.equals(other.nome) && this.peso == other.peso && this.numeroAtomico == other.numeroAtomico;
    }

    @Override
    public int hashCode(){
        return Objects.hash(simbolo,nome,peso,numeroAtomico);
    }

    /**
     * Funzione d'astrazione
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(simbolo);
        sb.append(" ");
        sb.append(nome);
        sb.append(" ");
        sb.append(peso);
        return sb.toString();
    }


}
