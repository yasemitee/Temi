package Playfy.src;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Soluzione {
    public static void main(String[] args) {

        final List<Album> album = new ArrayList<>();
        Playlist fusa = new Playlist("Fusa");

        try (final Scanner s = new Scanner(System.in)) {
            while (s.hasNextLine()) {
                final String line = s.nextLine();

                if (line.startsWith("ALBUM")) {
                    final String titolo = line.substring(6);
                    final List<String> titoli = new ArrayList<>();
                    final List<Durata> durate = new ArrayList<>();
                    while (s.hasNextLine()) {
                        final String aLine = s.nextLine();
                        if (aLine.equals(".")) {
                            final Album a = new Album(titolo, titoli, durate);
                            album.add(a);
                            System.out.println(a);
                            break;
                        }
                        final String[] p = aLine.split("-", 2);
                        durate.add(Durata.valueOf(p[0].strip()));
                        titoli.add(p[1].strip());
                    }
                } else if (line.startsWith("PLAYLIST")) {
                    final Playlist pl = new Playlist(line.substring(9));
                    while (s.hasNextLine()) {
                        final String plLine = s.nextLine();
                        if (plLine.equals(".")) {
                            fusa = fusa.fondi("Fusa", pl);
                            System.out.println(pl);
                            break;
                        }
                        final String[] p = plLine.split(" ", 2);
                        final int aIdx = Integer.parseInt(p[0].strip()) - 1;
                        final int bIdx = Integer.parseInt(p[1].strip());
                        pl.aggiungiBrano(album.get(aIdx).getBranoInPosizione(bIdx));
                    }
                }
            }
        }

        System.out.println(fusa);
        final Iterator<Album> ait = fusa.album();
        while (ait.hasNext()) {
            final Album a = ait.next();
            System.out.println(a.nome);
            final Iterator<Album.Brano> bit = fusa.brani(a);
            while (bit.hasNext()) System.out.println("\t" + bit.next());
        }

    }
}
