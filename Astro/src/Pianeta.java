import java.util.Objects;

public class Pianeta extends CorpoCeleste {
    /**
     * Le istanze di questa classe sono mutabli e utili a rappresentare un Pianeta,
     * un pianeta è un corpo celeste caratterizzato da un nome, una posizione e una velocità
     * FA(Pianeta): Pianeta, nome: Pianeta.nome, pos: Pianeta.posizione, vel: Pianeta.velocità
     * IR(Pianta): IR(CorpoCeleste) && Pianeta.posizione != null && Pianeta.velocità != null
     */

    private Punto posizione, velocità;

    public Pianeta(final String nome, final int x, final int y, final int z) {
        super(nome);
        posizione = new Punto(x, y, z);
        velocità = new Punto(0, 0, 0);
    }

    @Override
    public void aggiornaPosizione() {
        posizione = posizione.somma(velocità);
    }

    @Override
    public void aggiornaVelocità(final CorpoCeleste c) {
        Objects.requireNonNull(c, "x non può essere null");
        Punto d = posizione.sottrai(c.posizione());
        int changeX, changeY, changeZ = 0;
        changeX = d.x() < 1 ? 1 : -1;
        changeY = d.y() < 1 ? 1 : -1;
        changeZ = d.z() < 1 ? 1 : -1;
        d = new Punto(changeX, changeY, changeZ);
        velocità = velocità.somma(d);
    }

    @Override
    public String toString() {
        return String.format("Pianeta, nome: %s, pos: %s, vel: %s", nome, posizione, velocità);
    }

    @Override
    public Punto velocità() {
        return velocità;
    }

    @Override
    public Punto posizione() {
        return posizione;
    }

}
