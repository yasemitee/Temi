import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Locomotore calimero = new Locomotore("Calimero",100,1000);
        Locomotore tigrotto = new Locomotore("Tigrotto",200,5000);
        Locomotore cammello = new Locomotore("Cammello",150,3000);
        Vagone passeggeri = new Vagone("Passeggeri", 200,Arrays.asList(new Dotazioni("posti",30),new Dotazioni("bagni",1),
                new Dotazioni("finestrini",25),new Dotazioni("posti",20),new Dotazioni("bagni",1)));
        Vagone biscarda = new Vagone("biscarda", 500,Arrays.asList(new Dotazioni("auto",20),new Dotazioni("furgoni",7),
                new Dotazioni("auto",5)));
        Vagone ristorante = new Vagone("ristorante", 150,Arrays.asList(new Dotazioni("tavoli",20),new Dotazioni("cucina",1),
                new Dotazioni("bagni",1)));
        Convoglio convoglio= new Convoglio("Friends",Arrays.asList(cammello,passeggeri,passeggeri,passeggeri,biscarda));
        System.out.println(calimero);
        System.out.println(passeggeri);
        System.out.println(biscarda);
        System.out.println(tigrotto);
        System.out.println(cammello);
        System.out.println(ristorante);
        System.out.println(convoglio);
    }
}
