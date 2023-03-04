import java.util.List;

/**
 * Le istanze di questa classe sono utili a rappresentare la politica di sconto:
 * "Nessuno sconto", quindi il prezzo è 1:1
 */
public class ListinoUnitario extends PoliticaSconto {

    public ListinoUnitario(List<Giocattolo> g, List<Integer> p) {
        super(g, p);
    }

    @Override
    public double formulaSconto(Giocattolo g, int q) {
        return prezzoTotale(g, q); // Nessuno sconto applicato
    }

}
