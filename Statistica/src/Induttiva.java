import java.util.List;
import java.util.Objects;

/**
 * Classe mutabile utile a calcolare l'indice di media e di varianza con una passata stabile.
 * Il calcolo avviene in modo induttivo
 */
public class Induttiva implements Statistica{
    /*Insieme delle osservazioni*/
    private int nOsservazioni;
    private double mediaI = 0;
    private double varianzaI = 0;


    /* RI: nOsservazioni deve essere > 0 */

    /**
     * Crea un nuovo oggetto {@code Induttiva} data una osservazione.
     * @param osservazione da aggiungere
     */
    public Induttiva(double osservazione) {
            aggiungiOsservazione(osservazione);
    }

        /**
     * Crea un nuovo oggetto {@code Induttiva} data una lista di osservazioni}.
     * @param osservazioni
     * @throws NullPointerException se la lista di osservazioni è {@code null} o contiene elementi {@code null}.
     * @throws IllegalArgumentException se la lista non contiene almeno un osservazione.
     */
    public Induttiva(final List<Double> osservazioni) {
        if (Objects.requireNonNull(osservazioni, "le osservazioni non possono essere null").size() < 1)
            throw new IllegalArgumentException("osservazioni deve contenere almeno un osservazione");
        for (Double o : osservazioni){
            aggiungiOsservazione(Objects.requireNonNull(o, "La lista di osservazioni non può contenere elementi null"));
        }
    }

    /**
     * Aggiunge un osservazione alle osservazioni in analisi
     * @param osservazione
     */
    public void aggiungiOsservazione(double osservazione){
        nOsservazioni++;
        varianzaI = passoInduttivoVarianza(varianzaI, osservazione, mediaI, nOsservazioni);
        mediaI = passoInduttivoMedia(mediaI, osservazione, nOsservazioni);
    }

    /**
     * Effettua il passo induttivo per aggiornare il valore della media all'i-esimo passo
     * @param media la media al passo i-1
     * @param osservazione l'osservazione coinvolta nell'i-esimo passo
     * @param i passo raggiunto
     * @return la media all'i-esimo passo
     */
    private double passoInduttivoMedia(double media, double osservazione, int i){
        return  media + ((osservazione - media)/i);
    }
    /**
     * Effettua il passo induttivo per aggiornare il valore della varianza all'i-esimo passo
     * @param varianza la varianza al passo i-1
     * @param osservazione l'osservazione coinvolta nell'i-esimo passo
     * @param i passo raggiunto
     * @return la varianza all'i-esimo passo
     */
    private double passoInduttivoVarianza(double varianza, double osservazione, double media, int i){
        double mediaNuova = passoInduttivoMedia(media, osservazione, i);
        return varianza +((osservazione-media)*(osservazione-mediaNuova));
    }

    @Override
    public double media() {
        return mediaI;
    }

    @Override
    public double varianza() {
        return varianzaI / (nOsservazioni - 1);
    }

    @Override
    public int nOsservazioni() {
        return nOsservazioni;
    }

    /*Funzione d'astrazione*/
    @Override
    public String toString() {
        return nOsservazioni + ", " + String.format("%.8e", media()) + ", " + String.format("%.8e", varianza());
    }


}
