import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        final SistemaAstronomico sa = new SistemaAstronomico();

        try (final Scanner s = new Scanner(System.in)) {
            while (s.hasNext()) {
                if (s.next().charAt(0) == 'P')
                    sa.aggiungi(new Pianeta(s.next(), s.nextInt(), s.nextInt(), s.nextInt()));
                else
                    sa.aggiungi(new StellaFissa(s.next(), s.nextInt(), s.nextInt(), s.nextInt()));
            }
        }
        System.out.println(sa);
        sa.simula(1);
        System.out.println(sa);
        System.out.println("Energia totale: " + sa.energia());

    }
}
