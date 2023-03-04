import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Classe concreta e immutabile utile a rappresentare un vagone, ovvero un rotabile con delle dotazioni
 */
public class Vagone extends Rotabile{
    ArrayList<Dotazioni> dotazioni = new ArrayList<>();
    /**
     * RI: le dotazioni in dotazione non devono essere null;
     */


    /**
     * Costruisce un nuovo rotabile dato il modello e il peso
     *
     * @param modello il modello del rotabile
     * @param peso    il peso del rotabile
     * @throws IllegalArgumentException se il peso è <= 0
     * @throws NullPointerException     se il modello o una dotazione è {@code null}
     */
    public Vagone(String modello, int peso, List<Dotazioni> dotazioni) {
        super(modello, peso);
        for (var d : dotazioni) {
            Objects.requireNonNull(d, "Le dotazioni non possono essere null");
            aggiornaDotazioni(d);
        }
        Collections.sort(this.dotazioni);
    }
    private void aggiornaDotazioni(final Dotazioni d){
        int q = d.quanità;
        final String nome = d.nome;
        if (this.dotazioni.contains(d)) {
            int index = this.dotazioni.indexOf(d);
            q += this.dotazioni.get(index).quanità;
            this.dotazioni.remove(index);
            this.dotazioni.add(new Dotazioni(nome,q));
        }else{
            this.dotazioni.add(new Dotazioni(nome,q));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vagone: modello = "+getModello()+", peso = "+getPeso()+", dotazioni : "+dotazioni);
        return sb.toString();
    }

    @Override
    public boolean isLocomotore() {
        return false;
    }
}
