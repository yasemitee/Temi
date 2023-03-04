import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        List<ElementoChimico> listaElementi = new ArrayList<>();
        int count = 1;
            Scanner scanner = new Scanner(new File("input.txt"));
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine().split("\t");
                ElementoChimico el = new ElementoChimico(count,line[0],line[1],Double.parseDouble(line[2]));
                listaElementi.add(el);
                count++;
            }
            scanner.close();
        TavolaPeriodica tv = new TavolaPeriodica(listaElementi);
        String eq = "2 HCl + 2 Na -> 2 NaCl + H2";
        EquazioneChimica equa = new EquazioneChimica(eq,tv);
        System.out.println(equa);
    }

}