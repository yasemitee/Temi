public class StellaFissa extends CorpoCeleste {

    /**
     * Le istanze di questa classe sono immutabili e utili a rappresentare una
     * Stella fissa, una stella fissa è un corpo celeste con una posizione fissata;
     * FA(StellaFissa): Stella fissa, nome: StellaFissa.nome pos: StellaFissa.posizione
     * IR(StellaFissa):
     */
    private final Punto posizione, velocità;

    public StellaFissa(String nome, final int x, final int y, final int z) {
        super(nome);
        posizione = new Punto(x, y, z);
        velocità = new Punto(0, 0, 0);
    }

    @Override
    public void aggiornaPosizione() {}

    @Override
    public void aggiornaVelocità(final CorpoCeleste c) {}

    @Override
    public String toString() {
        return String.format("Stella fissa, nome: %s, pos: %s", nome, posizione);
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
