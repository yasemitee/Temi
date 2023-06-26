import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classe concreta immutabile, utile a rappresentare una {@link Programmazione} singola, 
 * ovvero una programmazione che occorre in un singolo giorno
 */
public class ProgrammazioneSingola extends AbstractProgrammazione{

    // Costruttori

    ProgrammazioneSingola(int primoGiorno) {
        super(primoGiorno);
    }

    // Metodi
    
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>(){

            int next = primoGiorno();

            @Override
            public boolean hasNext() {
                return next != -1;
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                 int result = next;
                 next = -1;
                 return result;
            }
        };
    }
}