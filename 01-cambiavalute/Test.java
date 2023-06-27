import java.math.BigDecimal;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        Importo i2 = new Importo(BigDecimal.valueOf(3), Valuta.Euro);
        Importo i = new Importo(BigDecimal.valueOf(55.30), Valuta.Dollaro);
        
        Importo i3 = new Importo(BigDecimal.valueOf(89.50), Valuta.Franco);;
        Cassa cas = new Cassa();
        cas.versa(i);
        cas.versa(i2);
        cas.versa(i3);

        Importo p = new Importo(BigDecimal.valueOf(1), Valuta.Euro);
        Importo s = new Importo(BigDecimal.valueOf(2), Valuta.Dollaro);
        
        Tasso t = new Tasso(p, s);

        Importo r = new Importo(BigDecimal.valueOf(1), Valuta.Dollaro);
        System.out.println(t.converti(r));
        
    }
}
