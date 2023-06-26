/*Interfaccia che offre delle funzionalità utili al calcolo degli indici di media e varianza */
public interface Statistica {
    
    /**
     * Consente di calcolare la media di alcune osservazioni
     * @return l'indice di media
     */
    public double media();
    
    /**
     * Consente di calcolare la varianza di alcune osservazioni
     * @return l'indice di varianza
     */
    public double varianza();

    /**
     * Restituisce il numero di osservazioni 
     * @return il numero di osservazioni
     */
    public int nOsservazioni();
}
