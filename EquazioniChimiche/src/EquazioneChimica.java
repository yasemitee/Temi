import java.util.*;

/**
 * Classe concreta e immutabile utile a rappresentare un equazione chimica,
 * un equazione chimica descrive una reazione (o trasformazione) chimica e consiste di una lista di reagenti (le molecole di partenza)
 * e di una lista di prodotti (le molecole di arrivo)
 */
public class EquazioneChimica {
    private final String equazione; //equazione
    private final Map<Molecola,Integer> reagenti = new HashMap<>(); //i reagenti con la loro numerosità
    private final Map<Molecola,Integer> prodotti = new HashMap<>(); //i prodotti con la loro numerosità

    /**
     * RI: Reagenti e prodotti non devono essere null, ogni molecola non deve essere null, ogni molecola deve avere numerosità almeno 1
     */

    /**
     * Costruisce una nuova formula chimica a partire da un equazione e la tavola periodica
     * @param equazione l'equazione
     * @param tv la tavola periodica
     * @throws NullPointerException se l'equazione è nul
     */
    public EquazioneChimica(final String equazione, TavolaPeriodica tv) {
        if (Objects.requireNonNull(equazione,"L'equazione non può essere null").isEmpty()){
            throw new IllegalArgumentException("l'equazione non può essere null");
        }
        if (!equazione.contains("->")) throw new IllegalArgumentException("L'equazione non ha reagenti o prodotti");
        this.equazione = equazione;
        var componentiEq = equazione.split("->");
        var parseReagenti  = Helpers.parseSommaStechiometrica(componentiEq[0]);
        for (int i = 1; i < parseReagenti.length; i+=2) {
            Molecola m;
            if (Molecola.isSemplice(parseReagenti[i])){
                m = new MolecolaSemplice(parseReagenti[i],tv);
            }else {
                m = new MolecolaComposta(parseReagenti[i],tv);
            }
            reagenti.put(m,Integer.parseInt(parseReagenti[i-1]));
        }
        var parseProdotti  = Helpers.parseSommaStechiometrica(componentiEq[1]);
        for (int i = 1; i < parseProdotti.length; i+=2) {
            Molecola m;
            if (Molecola.isSemplice(parseProdotti[i])){
                m = new MolecolaSemplice(parseProdotti[i],tv);
            }else {
                m = new MolecolaComposta(parseProdotti[i],tv);
            }
            prodotti.put(m,Integer.parseInt(parseProdotti[i-1]));
        }
    }

    /**
     * Verifica se un equazione è bilanciata ovvero se e solo se ciascun tipo di elementi è presente nello stesso numero sia nei reagenti che nei prodotti.
     * @return true se l'equazione è bilanciata, false altrimenti
     */
    public boolean isBilanciata(){
        Map<ElementoChimico,Integer> numeroElementiReagenti = new HashMap<>();
        Map<ElementoChimico,Integer> numeroElementiProdotti = new HashMap<>();
        for (var el : reagenti.entrySet()){
            for (ElementoChimico atomo : el.getKey()){
                if (numeroElementiReagenti.containsKey(atomo)) {
                    numeroElementiReagenti.put(atomo, numeroElementiReagenti.get(atomo) + el.getValue() * el.getKey().numerositàAtomo(atomo));
                }else {
                    numeroElementiReagenti.put(atomo, el.getValue() * el.getKey().numerositàAtomo(atomo));
                }
            }
        }
        for (var el : prodotti.entrySet()){
            for (ElementoChimico atomo : el.getKey()){
                if (numeroElementiProdotti.containsKey(atomo)){
                    numeroElementiProdotti.put(atomo , numeroElementiProdotti.get(atomo) + el.getValue() * el.getKey().numerositàAtomo(atomo));
                }else{
                    numeroElementiProdotti.put(atomo , el.getValue() * el.getKey().numerositàAtomo(atomo));
                }
            }
        }
        return numeroElementiProdotti.equals(numeroElementiReagenti);
    }

    /**
     * Restituisce una lista contenente i reagenti coinvolti nell'equazione
     * @return i reagenti
     */
    public List<Molecola> getReagenti(){
        return new ArrayList<Molecola>(reagenti.keySet());
    }

    /**
     * Restituisce una lista contenente i prodotti coinvolti nell'equazione
     * @return i prodotti
     */
    public List<Molecola> getProdotti(){
        return new ArrayList<Molecola>(prodotti.keySet());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String spazi = "        ";
        sb.append("Equazione: \n");
        sb.append(spazi);
        sb.append(equazione + ", "+ (isBilanciata() ? "bilanciata" : "non bilanciata")+"\n");
        sb.append("Reagenti: \n");
        for (var reagente : getReagenti()){
            sb.append(spazi + reagente+"\n");
        }
        sb.append("Prodotto: \n");
        for (var reagente : getProdotti()){
            sb.append(spazi + reagente+"\n");
        }
        return sb.toString();
    }
}
