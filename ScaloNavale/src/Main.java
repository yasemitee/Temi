import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int nSpostamenti = Integer.parseInt(args[0]);
        int partenza = Integer.parseInt(args[1]);
        int arrivo = Integer.parseInt(args[2]);
        File file = new File("input.txt");
        Scanner sc = new Scanner(file);
        int nMoli = sc.nextInt();
        ScaloNavale scalo = new ScaloNavale(nMoli);
        sc.nextLine();
        for (int i = 0; i < nMoli; i++) {
            String[] input = sc.nextLine().split(" ");
            List<String> nomiNavi = new ArrayList<>();
            List<Integer> pesiNavi = new ArrayList<>();
            for (int j = 0; j < input.length; j++) {
                if (j % 2 == 0) {
                    nomiNavi.add(input[j]);
                } else {
                    pesiNavi.add(Integer.parseInt(input[j]));
                }
            }
            for (int j = 0; j < nomiNavi.size(); j++) {
                var nave = new Molo.NaveCargo(nomiNavi.get(j), pesiNavi.get(j));
                scalo.attraccaNaveA(nave, i);
            }
        }
        System.out.print(scalo);
        scalo.spostamento(nSpostamenti,partenza,arrivo);
        System.out.println(nSpostamenti);
        System.out.println(scalo);
    }
}