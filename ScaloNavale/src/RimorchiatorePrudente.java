import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RimorchiatorePrudente implements Rimorchiatore{
    private final int maxPeso;
    private final List<Molo.NaveCargo> aux; //lista ausiliario per mantenere l'ordine delle navi spostate

    /**
     * Crea un nuovo super rimorchiatore prudente
     * @param peso il peso massimo che il rimorchiatore è in grado di spostare
     * @throws IllegalArgumentException se peso <= 0
     */
    public RimorchiatorePrudente(int peso) {
        if (peso <= 0) throw new IllegalArgumentException("Il rimorchiatore deve poter spostare almeno una nave");
        this.maxPeso = peso;
        aux = new ArrayList<>();
    }

    @Override
    public void sposta(Molo a, Molo b) {
        Objects.requireNonNull(a,"Il molo a non può essere null");
        Objects.requireNonNull(b,"Il molo b non può essere null");
        int currPeso = 0;
        while (currPeso < maxPeso) {
            var currNave = a.salpa();
            if (currPeso > 0 && currPeso + currNave.peso() > maxPeso){
                break;
            }
            aux.add(currNave);
            currPeso += currNave.peso();

        }
        for (Molo.NaveCargo nave : aux) {
            a.attracca(nave);
        }
    }
}
