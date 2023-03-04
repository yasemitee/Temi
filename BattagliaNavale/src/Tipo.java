public enum Tipo {
    P(8),
    F(7),
    S(5),
    C(2);

    final int lunghezza;

    private Tipo(int lunghezza) {
        this.lunghezza = lunghezza;
    }

}
