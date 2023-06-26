import java.util.*;

/**
 * Classe mutabile che estende {@link StatisticaAbstract}, utile a calcolare la media e la varianza di alcune osservazioni con due passate.
 * Per due passate si intende che l'indice di varianza viene calcolato dopo aver calcolato l'indice di media.
 */
public class DuePassate extends StatisticaAbstract{
    /*Insieme delle osservazioni*/
    private List<Double> osservazioni = new ArrayList<>();


    /* RI:
     * - osservazioni deve essere diverso da null
     * - osservazioni deve contenere almeno un osservazione
    */
    public DuePassate(List<Double> osservazioni) {
        super();
        if (Objects.requireNonNull(osservazioni, "le osservazioni non possono essere null").size() < 1)
            throw new IllegalArgumentException("osservazioni deve contenere almeno un osservazione");
        for (Double o : osservazioni){
            aggiungiOsservazione(Objects.requireNonNull(o, "La lista di osservazioni non può contenere elementi null"));
        }
    }

    public DuePassate(double osservazione) {
        super(osservazione);
    }

    

    @Override
    public void aggiungiOsservazione(double osservazione) {
        super.aggiungiOsservazione(osservazione);
        osservazioni.add(osservazione);
    }

    /**
     * restituisce una lista contenente le osservazioni
     * @return una lista contenente le osservazioni
     */
    public List<Double> getOsservazioni() {
        return new ArrayList<>(osservazioni);
    }

    @Override
    public double varianza() {
        double media = media();
        double sommaQuadrati = 0;
        for (Double o : osservazioni){
            sommaQuadrati += (o - media)*(o - media);
        }
        return sommaQuadrati / (numeroOsservazioni() - 1);
    }
}
