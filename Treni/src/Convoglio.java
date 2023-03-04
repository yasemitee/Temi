import java.util.*;

/**
 * Classe concreta e immutabile utile a rappresentare un convoglio, un convoglio è una collezione di rotabili
 */
public class Convoglio {
    final private String nome;
    final private int peso;
    final private int potenza;

    final private LinkedList<Rotabile> rotabili;
    final private ArrayList<Dotazioni> dotazioni;

    /**
     * RI: il nome deve essere diverso da null, il peso e la potenza devono essere positivi,
     * le dotazioni non devono essere null i rotabili non devono essere null (e quindi le due liste non devono essere null)
     */
    public Convoglio(String nome, List<Rotabile> rotabili) {
        this.nome = Objects.requireNonNull(nome,"Il nome non deve essere null");
        Objects.requireNonNull(rotabili,"la lista di rotabili non può essere null");
        if (rotabili.contains(null)) throw new NullPointerException("Non ci possono essere rotabili nella lista null");
        this.rotabili = new LinkedList<>(rotabili);
        this.dotazioni = new ArrayList<>();
        for (var r: this.rotabili){
            if (!r.isLocomotore()){
                if (r instanceof Vagone vagone) {
                    for (var d : vagone.dotazioni) {
                        Objects.requireNonNull(d, "Le dotazioni non possono essere null");
                        aggiornaDotazioni(d);
                    }
                }
            }
        }
        Collections.sort(this.dotazioni);
        this.peso = calcolaPeso(this.rotabili);
        this.potenza = calcolaPotenza(this.rotabili);
    }

    private int calcolaPotenza(final LinkedList<Rotabile> rotabili) {
        int totale = 0;
        if (rotabili.getFirst() instanceof Locomotore locomotore){
            totale += locomotore.getPotenza();
        }
        if (rotabili.getLast() instanceof Locomotore locomotore){
            totale += locomotore.getPotenza();
        }
    return totale;
    }
    private int calcolaPeso(final List<Rotabile> rotabili){
        int totale = 0;
        for (var r: rotabili){
            totale += r.getPeso();
        }
        return totale;
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

    public boolean isValido(){
        return potenza >= peso;
    }

    public Rotabile testa(){
        return rotabili.getFirst();
    }

    public Rotabile coda(){
        return rotabili.getLast();
    }

    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    public int getPotenza() {
        return potenza;
    }

    public LinkedList<Rotabile> getRotabili() {
        return new LinkedList<>(rotabili);
    }

    public ArrayList<Dotazioni> getDotazioni() {
        return new ArrayList<>(dotazioni);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Convoglio: nome = "+nome+", peso = "+peso+", potenza = "+potenza+", dotazioni : "+dotazioni);
        if (isValido()){
            sb.append(", valido");
        }else{
            sb.append(", non valido");
        }
        return sb.toString();
    }
}
