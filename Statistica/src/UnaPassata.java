import java.util.List;

/**
 * Classe mutabile utile a calcolare la media e la varianza di alcune osservazioni con una passata.
 */
public class UnaPassata extends StatisticaAbstract{

    private double sommaQuadrati;

    public UnaPassata(List<Double> osservazioni) {
        super(osservazioni);
    }

    public UnaPassata(double osservazione) {
        super(osservazione);
    }
    

    @Override
    public void aggiungiOsservazione(double osservazione) {
        super.aggiungiOsservazione(osservazione);
        sommaQuadrati += osservazione * osservazione;
    }
    
    /**
     * Restituisce la somma dei quadrati delle osservazioni
     * @return la somma quadrata
     */
    public double getSommaQuadrati() {
        return sommaQuadrati;
    }
    
    @Override
    public double varianza() {
        int n = numeroOsservazioni();
        return ((n * sommaQuadrati) - (getSomma() * getSomma())) / (n * (n - 1));
    }

}
