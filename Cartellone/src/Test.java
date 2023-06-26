import java.util.Iterator;

public class Test {

    public static void main(String[] args) {

        System.out.println("CARTELLONE\n");
        
        Cartellone cartellone = new Cartellone("Archipelago", 30);
        
        cartellone.aggiungiProiezione("My Frozen Chestnuts", new ProgrammazionePeriodica(3, 4));
        cartellone.aggiungiProiezione("They Call Me Trinity", new ProgrammazioneRepliche(4, 3));
        cartellone.aggiungiProiezione("The Single Standard", new ProgrammazioneSingola(8));
        cartellone.aggiungiProiezione("Three Amigos", new ProgrammazionePeriodica(10, 3));
        
        System.out.println(cartellone + "\n");

        Iterator<String> date = cartellone.proiezioniGiorni();
        
        while (date.hasNext()) {
            System.out.println(date.next());
        }

        System.out.println("\nPROIEZIONI\n");

        for (Cartellone.Proiezione pr : cartellone){
            System.out.println(pr);
        }
    }
}
