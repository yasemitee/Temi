/**
 * Classe concreta e immutabile utile a rappresentare un rotabile, ovvero un rotabile caratterizzato da un potenza di traino
 */
public class Locomotore extends Rotabile{
    private final int potenza;
    /**
     * RI: potenza deve > 0
     */

    /**
     * Costruisce un nuovo rotabile dato il modello e il peso
     *
     * @param modello il modello del rotabile
     * @param peso    il peso del rotabile
     * @throws IllegalArgumentException se il peso è <= 0 o la potenza è <= 0
     * @throws NullPointerException     se il modello è {@code null}
     */
    public Locomotore(String modello, int peso, int potenza) {
        super(modello, peso);
        if (potenza <= 0) throw new IllegalArgumentException("la potenza non può essere <= 0");
        this.potenza = potenza;
    }

    public int getPotenza() {
        return potenza;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("locomotore: modello = "+getModello()+", peso = "+getPeso()+", potenza = "+getPotenza());
        return sb.toString();
    }

    @Override
    public boolean isLocomotore() {
        return true;
    }
}
